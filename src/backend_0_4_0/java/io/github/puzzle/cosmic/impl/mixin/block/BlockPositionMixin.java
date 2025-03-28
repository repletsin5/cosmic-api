package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockEntity;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(BlockPosition.class)
public class BlockPositionMixin implements IPuzzleBlockPosition {

    @Unique
    private final transient BlockPosition puzzleLoader$blockPosition = IPuzzleBlockPosition.as(this);

    @Inject(method = "setBlockState", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(BlockState targetBlockState, CallbackInfo ci) {
        pUpdateNeighbors(new BlockUpdateEvent());
    }

    @Override
    public int pGetLocalX() {
        return puzzleLoader$blockPosition.localX();
    }

    @Override
    public int pGetLocalY() {
        return puzzleLoader$blockPosition.localY();
    }

    @Override
    public int pGetLocalZ() {
        return puzzleLoader$blockPosition.localZ();
    }

    @Override
    public int pGetGlobalX() {
        return puzzleLoader$blockPosition.getGlobalX();
    }

    @Override
    public int pGetGlobalY() {
        return puzzleLoader$blockPosition.getGlobalY();
    }

    @Override
    public int pGetGlobalZ() {
        return puzzleLoader$blockPosition.getGlobalZ();
    }

    @Override
    public IPuzzleChunk pGetChunk() {
        return IPuzzleChunk.as(puzzleLoader$blockPosition.chunk());
    }

    @Override
    public IPuzzleZone pGetZone() {
        return IPuzzleZone.as(puzzleLoader$blockPosition.getZone());
    }

    @Override
    public IPuzzleBlockEntity pGetBlockEntity() {
        return IPuzzleBlockEntity.as(puzzleLoader$blockPosition.getBlockEntity());
    }

    @Override
    public IPuzzleBlockEntity pSetBlockEntity(IPuzzleBlockState state) {
        return IPuzzleBlockEntity.as(puzzleLoader$blockPosition.setBlockEntity(state.as()));
    }

    @Override
    public void pSetBlockEntityDirect(IPuzzleBlockState state, IPuzzleBlockEntity blockEntity) {
        puzzleLoader$blockPosition.setBlockEntityDirect(state.as(), blockEntity.as());
    }

    @Override
    public IPuzzleBlockPosition pSet(IPuzzleChunk chunk, int localX, int localY, int localZ) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.set(chunk.as(), localX, localY, localZ));
    }

    @Override
    public void pConvertToLocal(IPuzzleZone zone) {
        if (puzzleLoader$blockPosition.chunk != null) {
            throw new RuntimeException("This block position is already in local coordinates!");
        } else {
            int globalX = puzzleLoader$blockPosition.localX;
            int globalY = puzzleLoader$blockPosition.localY;
            int globalZ = puzzleLoader$blockPosition.localZ;
            Chunk chunk = zone.as().getChunkAtBlock(globalX, globalY, globalZ);
            int chunkX = Math.floorDiv(globalX, 16);
            int chunkY = Math.floorDiv(globalY, 16);
            int chunkZ = Math.floorDiv(globalZ, 16);
            if (chunk == null) {
                for(Direction d : Direction.ALL_DIRECTIONS) {
                    Chunk n = zone.as().getChunkAtChunkCoords(chunkX + d.getXOffset(), chunkY + d.getYOffset(), chunkZ + d.getZOffset());
                    if (n != null) {
                        chunk = zone.as().createBlankChunk(chunkX, chunkY, chunkZ);
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
    public void pSetGlobal(IPuzzleZone zone, float x, float y, float z) {
        this.pSet(null, (int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
        this.pConvertToLocal(zone);
    }

    @Override
    public IPuzzleBlockState pGetBlockState() {
        return IPuzzleBlockState.as(puzzleLoader$blockPosition.getBlockState());
    }

    @Override
    public void pSetBlockState(IPuzzleBlockState state) {
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
            IPuzzleBlockPosition offs = pGetOffsetBlockPos(pGetZone(), direction);
            if (offs == null) continue;

            IPuzzleBlockEntity entity = offs.pGetBlockEntity();

            if (entity != null)
                entity.pOnNeighborUpdate(event);
        }
    }

    @Override
    public void pUpdateNeighborInDirection(IBlockUpdateEvent event, Direction direction) {
        event.setSourcePosition(this);

        IPuzzleBlockPosition offs = pGetOffsetBlockPos(pGetZone(), direction);
        if (offs == null) return;

        IPuzzleBlockEntity entity = offs.pGetBlockEntity();

        if (entity != null)
            entity.pOnNeighborUpdate(event);
    }

    @Override
    public IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone iPuzzleZone, int x, int y, int z) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(iPuzzleZone.as(), x, y, z));
    }

    @Override
    public IPuzzleBlockPosition pGetOffsetBlockPos(int i, int i1, int i2) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(i, i1, i2));
    }

    @Override
    public IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition iPuzzleBlockPosition, IPuzzleZone iPuzzleZone, int x, int y, int z) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(iPuzzleBlockPosition.as(), iPuzzleZone.as(), x, y, z));
    }

    @Override
    public IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone iPuzzleZone, Direction direction) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(iPuzzleZone.as(), direction));
    }

    @Override
    public IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition iPuzzleBlockPosition, IPuzzleZone iPuzzleZone, Direction direction) {
        return IPuzzleBlockPosition.as(puzzleLoader$blockPosition.getOffsetBlockPos(iPuzzleBlockPosition.as(), iPuzzleZone.as(), direction));
    }
}
