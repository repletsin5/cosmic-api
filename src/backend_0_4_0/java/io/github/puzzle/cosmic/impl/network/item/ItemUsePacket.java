package io.github.puzzle.cosmic.impl.network.item;

import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.NetworkSide;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.APISide;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ItemUsePacket extends GamePacket {

    int slotId;
    IPuzzleBlockPosition targetPlaceBlockPos;
    IPuzzleBlockPosition targetBreakBlockPos;
    boolean usedLeftClick;

    public ItemUsePacket() {}

    public ItemUsePacket(int slotId, IPuzzleBlockPosition targetPlaceBlockPos, IPuzzleBlockPosition targetBreakBlockPos, boolean usedLeftClick) {
        this.slotId = slotId;
        this.targetPlaceBlockPos = targetPlaceBlockPos;
        this.targetBreakBlockPos = targetBreakBlockPos;
        this.usedLeftClick = usedLeftClick;
    }

    @Override
    public void receive(ByteBuf in) {
        this.slotId = this.readInt(in);
        this.targetPlaceBlockPos = IPuzzleBlockPosition.as(this.readBlockPositionZoneless(in));
        this.targetBreakBlockPos = IPuzzleBlockPosition.as(this.readBlockPositionZoneless(in));
        this.usedLeftClick = this.readBoolean(in);
    }

    @Override
    public void write() {
        this.writeInt(this.slotId);
        if (this.targetPlaceBlockPos != null) {
            this.writeBlockPosition(this.targetPlaceBlockPos.as());
        }
        if (this.targetBreakBlockPos != null) {
            this.writeBlockPosition(this.targetBreakBlockPos.as());
        }
        this.writeBoolean(this.usedLeftClick);
    }

    @Override
    public void handle(NetworkIdentity identity, ChannelHandlerContext channelHandlerContext) {
        if (identity.getSide() == NetworkSide.CLIENT) return;

        IPuzzlePlayer player = IPuzzlePlayer.as(identity.getPlayer());
        IPuzzleZone zone = IPuzzleZone.as(identity.getZone());

        ItemSlot slot = player.as().inventory.getSlot(slotId);
        IPuzzleItemStack stack = slot != null ? IPuzzleItemStack.as(slot.getItemStack()) : null;
        if (this.targetPlaceBlockPos != null) this.targetPlaceBlockPos._convertToLocal(zone);
        if (this.targetBreakBlockPos != null) this.targetBreakBlockPos._convertToLocal(zone);

        if (stack != null) {
            stack._getItem()._use(APISide.SERVER, IPuzzleItemSlot.as(slot), player, targetPlaceBlockPos, targetBreakBlockPos, usedLeftClick);
        }
    }
}
