package io.github.puzzle.cosmic.api.block;

import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Block")
public interface IPuzzleBlock {

    /**
     * Gets the default blockState of the block.
     * @return The default {@link IPuzzleBlockState} for this block.
     */
    IPuzzleBlockState pGetDefaultState();

    /**
     * Gets all the blockState of the block.
     * @return a {@link BlockStateMap} of the BlockStates.
     */
    BlockStateMap pGetStates();


    /**
     * Gets the identifier of the block.
     * @return a {@link IPuzzleIdentifier}
     */
    IPuzzleIdentifier pGetIdentifier();

    /**
     * Gets the name of the block.
     */
    String pGetName();

    interface BlockStateMap {
        IPuzzleBlockState get(String key);
    }
}
