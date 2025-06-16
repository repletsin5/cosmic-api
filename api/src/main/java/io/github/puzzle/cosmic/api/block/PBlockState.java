package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.Collection;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockState")
public interface PBlockState extends IBlockState {

    /**
     * Gets the block of the blockState.
     * @return a {@link IBlock}
     */
    IBlock pGetBlock();

    /**
     * Gets the blockState as an item.
     * @return a {@link IItem}
     */
    IItem pGetAsItem();

    /**
     * Sets the tags of the blockState.
     * @param tags a GameTagList of all the tags.
     */
    void pSetTags(GameTagList tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void pAddTags(GameTag... tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void pAddTags(Collection<GameTag> tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void pRemoveTags(GameTag... tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void pRemoveTags(Collection<GameTag>... tags);

    /**
     * Gets the blockId of the blockState.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetBlockID();

    /**
     * Gets the saveKey of the blockState.
     */
    String pGetSaveKey();

}
