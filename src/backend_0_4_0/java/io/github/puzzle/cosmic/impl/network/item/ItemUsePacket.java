package io.github.puzzle.cosmic.impl.network.item;

import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.NetworkSide;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.APISide;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ItemUsePacket extends GamePacket {

    int slotId;
    IBlockPosition targetPlaceBlockPos;
    IBlockPosition targetBreakBlockPos;
    boolean usedLeftClick;

    public ItemUsePacket() {}

    public ItemUsePacket(int slotId, IBlockPosition targetPlaceBlockPos, IBlockPosition targetBreakBlockPos, boolean usedLeftClick) {
        this.slotId = slotId;
        this.targetPlaceBlockPos = targetPlaceBlockPos;
        this.targetBreakBlockPos = targetBreakBlockPos;
        this.usedLeftClick = usedLeftClick;
    }

    @Override
    public void receive(ByteBuf in) {
        this.slotId = this.readInt(in);
        this.targetPlaceBlockPos = IBlockPosition.as(this.readBlockPositionZoneless(in));
        this.targetBreakBlockPos = IBlockPosition.as(this.readBlockPositionZoneless(in));
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

        IPlayer player = IPlayer.as(identity.getPlayer());
        IZone zone = IZone.as(identity.getZone());

        ItemSlot slot = player.as().inventory.getSlot(slotId);
        IItemStack stack = slot != null ? IItemStack.as(slot.getItemStack()) : null;
        if (this.targetPlaceBlockPos != null) this.targetPlaceBlockPos.pConvertToLocal(zone);
        if (this.targetBreakBlockPos != null) this.targetBreakBlockPos.pConvertToLocal(zone);

        if (stack != null) {
            stack.pGetItem().pUse(APISide.SERVER, IItemSlot.as(slot), player, targetPlaceBlockPos, targetBreakBlockPos, usedLeftClick);
        }
    }
}
