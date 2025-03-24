package io.github.puzzle.cosmic;

import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.PostModInitializer;
import com.github.puzzle.game.networking.packet.PacketInterceptor;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.item.ITickingPuzzleItem;
import io.github.puzzle.cosmic.impl.network.item.ItemUsePacket;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BlockWrench;
import io.github.puzzle.cosmic.item.CheckBoard;
import io.github.puzzle.cosmic.item.NullStick;

public class CosmicServerAPI implements PostModInitializer {

    @Override
    public void onPostInit() {
        AbstractCosmicItem.register(new CheckBoard());
        AbstractCosmicItem.register(new NullStick());
        AbstractCosmicItem.register(new BlockWrench());

        PacketInterceptor.registerReservedPacket("item-use", 9003, ItemUsePacket.class);
    }
}
