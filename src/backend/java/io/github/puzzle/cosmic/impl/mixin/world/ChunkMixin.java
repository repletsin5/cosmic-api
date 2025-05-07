package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.rendering.IChunkMeshGroup;
import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Internal
@Mixin(Chunk.class)
public class ChunkMixin implements IChunk {

    @Unique
    private final transient Chunk puzzleLoader$chunk = IChunk.as(this);

    @Unique
    private final transient IBlockPosition puzzleLoader$tmp = IBlockPosition.as(new BlockPosition(puzzleLoader$chunk, 0, 0, 0));

    @Inject(method = "setBlockState(Lfinalforeach/cosmicreach/blocks/BlockState;III)V", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(BlockState blockState, int x, int y, int z, CallbackInfo ci) {
        puzzleLoader$tmp.pSet(this, x, y, z).pUpdateNeighbors(new BlockUpdateEvent());
    }

    @Inject(method = "setBlockState(Lfinalforeach/cosmicreach/savelib/blocks/IBlockState;III)V", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(IBlockState blockState, int x, int y, int z, CallbackInfo ci) {
        puzzleLoader$tmp.pSet(this, x, y, z).pUpdateNeighbors(new BlockUpdateEvent());
    }

    @Unique
    private final transient IMeshingController puzzleLoader$meshingController = new IMeshingController() {

        @Override
        public void flagForRemeshing(boolean b) {
            puzzleLoader$chunk.flagForRemeshing(b);
        }

        @Override
        public void flagHorizontalTouchingChunksForRemeshing(IZone IZone, boolean b) {
            puzzleLoader$chunk.flagHorizontalTouchingChunksForRemeshing(IZone.as(), b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(IZone IZone, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(IZone.as(), b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(IZone IZone, int i, int i1, int i2, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(IZone.as(), i, i1, i2, b);
        }

        @Override
        public void setMeshGroup(IChunkMeshGroup<?> iChunkMeshGroup) {
            puzzleLoader$chunk.setMeshGroup(iChunkMeshGroup);
        }

        @Override
        public IChunkMeshGroup<?> getMeshGroup() {
            return puzzleLoader$chunk.getMeshGroup();
        }
    };

    @Unique
    private final transient IBlockEntityController puzzleLoader$blockEntityController = new IBlockEntityController() {

        @Override
        public IBlockEntity get(int i, int i1, int i2) {
            return IBlockEntity.as(puzzleLoader$chunk.getBlockEntity(i, i1, i2));
        }

        @Override
        public IBlockEntity put(PBlockState state, int i, int i1, int i2) {
            return IBlockEntity.as(puzzleLoader$chunk.setBlockEntity(state.as(), i, i1, i2));
        }

        @Override
        public IBlockEntity put(PBlockState state, IBlockEntity IBlockEntity, int i, int i1, int i2, boolean b) {
            puzzleLoader$chunk.setBlockEntityDirect(state.as(), IBlockEntity.as(), i, i1, i2, b);
            return IBlockEntity;
        }

        @Override
        public void foreach(Consumer<IBlockEntity> consumer) {
            puzzleLoader$chunk.forEachBlockEntity((c) -> consumer.accept(IBlockEntity.as(c)));
        }

        @Override
        public int getCount() {
            return puzzleLoader$chunk.getNumberOfBlockEntities();
        }

        @Override
        public boolean isEmpty() {
            return puzzleLoader$chunk.hasBlockEntities();
        }
    };

    @Override
    public void pCompact() {
        puzzleLoader$chunk.compactChunkData();
    }

    @Override
    public void pDispose() {
        puzzleLoader$chunk.dispose();
    }

    @Override
    public void pFill(PBlockState state) {
        puzzleLoader$chunk.fill(state.as());
    }

    @Override
    public void pFillLayer(PBlockState state, int i) {
        puzzleLoader$chunk.fillLayer(state.as(), i);
    }

    @Override
    public short pGetBlockLight(int i, int i1, int i2) {
        return puzzleLoader$chunk.getBlockLight(i, i1, i2);
    }

    @Override
    public int pGetSkyLight(int i, int i1, int i2) {
        return puzzleLoader$chunk.getSkyLight(i, i1, i2);
    }

    @Override
    public PBlockState pGetBlockState(int i, int i1, int i2) {
        return PBlockState.as(puzzleLoader$chunk.getBlockState(i, i1, i2));
    }

    @Override
    public void pSetBlockLight(int i, int i1, int i2, int i3, int i4, int i5) {
        puzzleLoader$chunk.setBlockLight(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void pSetBlockState(PBlockState blockState, int i, int i1, int i2) {
        puzzleLoader$chunk.setBlockState(blockState.as(), i, i1, i2);
    }

    @Override
    public void pSetSkyLight(int i, int i1, int i2, int i3) {
        puzzleLoader$chunk.setSkyLight(i, i1, i2, i3);
    }

    @Override
    public boolean pIsEntirelyOpaque() {
        return puzzleLoader$chunk.isEntirelyOpaque();
    }

    @Override
    public boolean pIsEntirelyOneBlockSelfCulling() {
        return puzzleLoader$chunk.isEntirelyOneBlockSelfCulling();
    }

    @Override
    public int pGetMaxNonEmptyBlockIdxYXZ() {
        return puzzleLoader$chunk.getMaxNonEmptyBlockIdxYXZ();
    }

    @Override
    public IMeshingController pGetMeshingController() {
        return puzzleLoader$meshingController;
    }

    @Override
    public IBlockEntityController pGetBlockEntityController() {
        return puzzleLoader$blockEntityController;
    }

    @Override
    public int pGetChunkX() {
        return puzzleLoader$chunk.getChunkX();
    }

    @Override
    public int pGetChunkY() {
        return puzzleLoader$chunk.getChunkY();
    }

    @Override
    public int pGetChunkZ() {
        return puzzleLoader$chunk.getChunkZ();
    }

    @Override
    public int pGetBlockX() {
        return puzzleLoader$chunk.getBlockX();
    }

    @Override
    public int pGetBlockY() {
        return puzzleLoader$chunk.getBlockY();
    }

    @Override
    public int pGetBlockZ() {
        return puzzleLoader$chunk.getBlockZ();
    }
}
