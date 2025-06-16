package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(BlockPosition.class)
public abstract class BlockPositionMixin implements IBlockPosition {

    @Shadow public abstract void convertToLocal(Zone zone);

    @Unique
    private final transient BlockPosition puzzleLoader$blockPosition = (BlockPosition)(Object)this;

    @Inject(method = "setBlockState", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(BlockState targetBlockState, CallbackInfo ci) {
        pUpdateNeighbors(new BlockUpdateEvent());
    }


    @Override
    public Chunk GetChunk() {
        return puzzleLoader$blockPosition.chunk();
    }




    @Override
    public void ConvertToLocal(Zone zone) {
        if (puzzleLoader$blockPosition.chunk != null) {
            throw new RuntimeException("This block position is already in local coordinates!");
        } else {
            int globalX = puzzleLoader$blockPosition.localX;
            int globalY = puzzleLoader$blockPosition.localY;
            int globalZ = puzzleLoader$blockPosition.localZ;
            Chunk chunk = zone.getChunkAtBlock(globalX, globalY, globalZ);
            int chunkX = Math.floorDiv(globalX, 16);
            int chunkY = Math.floorDiv(globalY, 16);
            int chunkZ = Math.floorDiv(globalZ, 16);
            if (chunk == null) {
                for(Direction d : Direction.ALL_DIRECTIONS) {
                    Chunk n = zone.getChunkAtChunkCoords(chunkX + d.getXOffset(), chunkY + d.getYOffset(), chunkZ + d.getZOffset());
                    if (n != null) {
                        chunk = zone.createBlankChunk(chunkX, chunkY, chunkZ);
                        break;
                    }
                }
            }

            puzzleLoader$blockPosition.chunk = chunk;
            puzzleLoader$blockPosition.localX = globalX - chunkX * 16;
            puzzleLoader$blockPosition.localY = globalY - chunkY * 16;
            puzzleLoader$blockPosition.localZ = globalZ - chunkZ * 16;
        }
    }

    @Override
    public void setGlobal(Zone zone, float x, float y, float z) {
        this.pSet(null, (int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
        this.convertToLocal(zone);
    }

    @Override
    public PBlockState pGetBlockState() {
        return PBlockState.as(puzzleLoader$blockPosition.getBlockState());
    }

    @Override
    public void pSetBlockState(PBlockState state) {
        puzzleLoader$blockPosition.setBlockState((BlockState) state);
    }

    @Override
    public int pGetSkylight() {
        return puzzleLoader$blockPosition.getSkyLight();
    }

    @Override
    public boolean pHasBlockEntity() {
        return pGetBlockEntity() != null;
    }

    @Override
    public void pUpdateNeighbors(IBlockUpdateEvent event) {
        event.setSourcePosition(this);

        for (Direction direction : Direction.values()) {
            IBlockPosition offs = pGetOffsetBlockPos(pGetZone(), direction);
            if (offs == null) continue;

            IBlockEntity entity = offs.pGetBlockEntity();

            if (entity != null)
                entity.pOnNeighborUpdate(event);
        }
    }

    @Override
    public void pUpdateNeighborInDirection(IBlockUpdateEvent event, Direction direction) {
        event.setSourcePosition(this);

        IBlockPosition offs = pGetOffsetBlockPos(pGetZone(), direction);
        if (offs == null) return;

        IBlockEntity entity = offs.pGetBlockEntity();

        if (entity != null)
            entity.pOnNeighborUpdate(event);
    }

    @Override
    public IBlockPosition pGetOffsetBlockPos(IZone IZone, int x, int y, int z) {
        return IBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(IZone.as(), x, y, z));
    }

    @Override
    public IBlockPosition pGetOffsetBlockPos(int i, int i1, int i2) {
        return IBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(i, i1, i2));
    }

    @Override
    public IBlockPosition pGetOffsetBlockPos(IBlockPosition blockPosition, IZone IZone, int x, int y, int z) {
        return IBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(blockPosition.as(), IZone.as(), x, y, z));
    }

    @Override
    public IBlockPosition pGetOffsetBlockPos(IZone IZone, Direction direction) {
        return IBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(IZone.as(), direction));
    }

    @Override
    public IBlockPosition pGetOffsetBlockPos(IBlockPosition blockPosition, IZone IZone, Direction direction) {
        return IBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(blockPosition.as(), IZone.as(), direction));
    }
}
