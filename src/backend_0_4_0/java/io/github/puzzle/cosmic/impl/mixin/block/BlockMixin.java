package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IBlock;
import io.github.puzzle.cosmic.api.block.IBlockState;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Block.class)
public class BlockMixin implements IBlock {

    @Unique
    private final transient Block puzzleLoader$block = IBlock.as(this);

    @Override
    public IBlockState pGetDefaultState() {
        return (IBlockState) puzzleLoader$block.getDefaultBlockState();
    }

    @Override
    public BlockStateMap pGetStates() {
        return key -> (IBlockState) puzzleLoader$block.blockStates.get(key);
    }

    @Override
    public IIdentifier pGetIdentifier() {
        return IIdentifier.as(Identifier.of(puzzleLoader$block.getStringId()));
    }

    @Override
    public String pGetName() {
        return puzzleLoader$block.getName();
    }

}
