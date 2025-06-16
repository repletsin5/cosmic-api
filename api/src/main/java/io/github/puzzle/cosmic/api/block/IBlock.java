package io.github.puzzle.cosmic.api.block;

import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Block")
public interface IBlock {

    /**
     * Gets the default blockState of the block.
     * @return The default {@link PBlockState} for this block.
     */
    PBlockState pGetDefaultState();

    /**
     * Gets all the blockState of the block.
     * @return a {@link BlockStateMap} of the BlockStates.
     */
    BlockStateMap pGetStates();


    /**
     * Gets the identifier of the block.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetIdentifier();

    /**
     * Gets the name of the block.
     */
    String pGetName();

    interface BlockStateMap {
        PBlockState get(String key);
    }
}
