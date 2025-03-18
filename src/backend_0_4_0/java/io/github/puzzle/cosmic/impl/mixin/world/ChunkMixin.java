package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.rendering.IChunkMeshGroup;
import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockEntity;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
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
public class ChunkMixin implements IPuzzleChunk {

    @Unique
    private final transient Chunk puzzleLoader$chunk = IPuzzleChunk.as(this);

    @Unique
    private final transient IPuzzleBlockPosition puzzleLoader$tmp = IPuzzleBlockPosition.as(new BlockPosition(puzzleLoader$chunk, 0, 0, 0));

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
        public void flagHorizontalTouchingChunksForRemeshing(IPuzzleZone iPuzzleZone, boolean b) {
            puzzleLoader$chunk.flagHorizontalTouchingChunksForRemeshing(iPuzzleZone.as(), b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(IPuzzleZone iPuzzleZone, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(iPuzzleZone.as(), b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(IPuzzleZone iPuzzleZone, int i, int i1, int i2, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(iPuzzleZone.as(), i, i1, i2, b);
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
        public IPuzzleBlockEntity get(int i, int i1, int i2) {
            return IPuzzleBlockEntity.as(puzzleLoader$chunk.getBlockEntity(i, i1, i2));
        }

        @Override
        public IPuzzleBlockEntity put(IPuzzleBlockState iPuzzleBlockState, int i, int i1, int i2) {
            return IPuzzleBlockEntity.as(puzzleLoader$chunk.setBlockEntity(iPuzzleBlockState.as(), i, i1, i2));
        }

        @Override
        public IPuzzleBlockEntity put(IPuzzleBlockState iPuzzleBlockState, IPuzzleBlockEntity iPuzzleBlockEntity, int i, int i1, int i2) {
            puzzleLoader$chunk.setBlockEntityDirect(iPuzzleBlockState.as(), iPuzzleBlockEntity.as(), i, i1, i2);
            return iPuzzleBlockEntity;
        }

        @Override
        public void foreach(Consumer<IPuzzleBlockEntity> consumer) {
            puzzleLoader$chunk.forEachBlockEntity((c) -> consumer.accept(IPuzzleBlockEntity.as(c)));
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
    public void pFill(IPuzzleBlockState iPuzzleBlockState) {
        puzzleLoader$chunk.fill(iPuzzleBlockState.as());
    }

    @Override
    public void pFillLayer(IPuzzleBlockState iPuzzleBlockState, int i) {
        puzzleLoader$chunk.fillLayer(iPuzzleBlockState.as(), i);
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
    public IPuzzleBlockState pGetBlockState(int i, int i1, int i2) {
        return IPuzzleBlockState.as(puzzleLoader$chunk.getBlockState(i, i1, i2));
    }

    @Override
    public void pSetBlockLight(int i, int i1, int i2, int i3, int i4, int i5) {
        puzzleLoader$chunk.setBlockLight(i, i1, i2, i3, i4, i5);
    }

    @Override
    public void pSetBlockState(IPuzzleBlockState iPuzzleBlockState, int i, int i1, int i2) {
        puzzleLoader$chunk.setBlockState(iPuzzleBlockState.as(), i, i1, i2);
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
