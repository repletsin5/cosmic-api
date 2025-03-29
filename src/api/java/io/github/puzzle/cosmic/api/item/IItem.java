package io.github.puzzle.cosmic.api.item;

import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.APISide;
import io.github.puzzle.cosmic.util.annotation.Internal;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Item")
public interface IItem {

    /**
     * Gets the id of the item.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetIdentifier();

    /**
     * Uses the item.
     * @param side the network side the item is use on.
     * @param itemSlot the slot the item is in.
     * @param player the player using the item.
     * @param targetPlaceBlockPos the block targeted for placing.
     * @param targetBreakBlockPos the block targeted for breaking.
     * @param isLeftClick is left click used.
     * @return {@code true} if successful.
     */
    default boolean pUse(APISide side, IItemSlot itemSlot, IPlayer player, IBlockPosition targetPlaceBlockPos, IBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        return false;
    }

    @Internal
    void pSetModded(boolean m);

    @Internal
    default boolean pIsModded() {
        return true;
    }

    /**
     * Checks if the item is a tool.
     */
    default boolean pIsTool() {
        return false;
    }

    /**
     * Checks if the item can break the blockState.
     * @param blockState blockState to break.
     * @return {@code true} if successful.
     */
    default boolean pCanBreakBlockWith(PBlockState blockState) {
        return true;
    }

    /**
     * Checks if the item can interact with the blockState.
     * @param blockState blockState to interact with.
     * @return {@code true} if successful.
     */
    default boolean pCanInteractWithBlock(PBlockState blockState) {
        return true;
    }

    /**
     * Checks if the item can break the blockState.
     * @param blockState blockState to break.
     * @return {@code true} if successful.
     */
    default boolean pCanInteractWithBlockEntity(PBlockState blockState) {
        return true;
    }

    /**
     * Gets the point manifest of the item.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest pGetPointManifest();

    /**
     * set the point manifest of the item.
     * @param manifest The new point manifest to be set.
     */
    void pSetPointManifest(IDataPointManifest manifest);

    enum ItemModelType {
        ITEM_MODEL_2D,
        ITEM_MODEL_3D
    }

}
