package io.github.puzzle.cosmic.api.item;

import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.APISide;
import io.github.puzzle.cosmic.util.annotation.Internal;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Item")
public interface IPuzzleItem {

    IPuzzleIdentifier pGetIdentifier();

    default boolean pUse(APISide side, IPuzzleItemSlot slot, IPuzzlePlayer player, IPuzzleBlockPosition targetPlaceBlockPos, IPuzzleBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        return false;
    }

    @Internal
    void pSetModded(boolean m);

    @Internal
    default boolean pIsModded() {
        return true;
    }

    default boolean pIsTool() {
        return false;
    }

    default boolean pCanBreakBlockWith(IPuzzleBlockState state) {
        return true;
    }

    default boolean pCanInteractWithBlock(IPuzzleBlockState state) {
        return true;
    }

    default boolean pCanInteractWithBlockEntity(IPuzzleBlockState state) {
        return true;
    }

    IDataPointManifest pGetPointManifest();
    void pSetPointManifest(IDataPointManifest manifest);

    enum ItemModelType {
        ITEM_MODEL_2D,
        ITEM_MODEL_3D
    }

}
