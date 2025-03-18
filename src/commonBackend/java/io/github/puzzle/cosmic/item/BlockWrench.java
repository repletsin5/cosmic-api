package io.github.puzzle.cosmic.item;

import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemBlock;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.util.APISide;

import static io.github.puzzle.cosmic.CosmicAPI.MOD_ID;

public class BlockWrench extends AbstractCosmicItem {

    public BlockWrench() {
        super(Identifier.of("puzzle-loader", "block_wrench"));
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(MOD_ID, "block_wrench.png")));
    }

    @Override
    public boolean pUse(APISide side, IPuzzleItemSlot slot, IPuzzlePlayer player, IPuzzleBlockPosition targetPlaceBlockPos, IPuzzleBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.REMOTE_CLIENT || side == APISide.SINGLE_PLAYER_CLIENT) && !isLeftClick) {
            if (targetBreakBlockPos == null) return false;
            BlockState state = targetBreakBlockPos.as().getBlockState();
            if (state == null) return false;
            BlockUtil.setBlockAt(targetBreakBlockPos.as().getZone(), ((ItemBlock) state.getItem().getNextSwapGroupItem()).getBlockState(), targetBreakBlockPos.as());
        }
        return false;
    }

    @Override
    public boolean pIsTool() {
        return true;
    }

    @Override
    public int getDefaultStackLimit() {
        return 1;
    }

    @Override
    public String getName() {
        return "State Wrench";
    }

}
