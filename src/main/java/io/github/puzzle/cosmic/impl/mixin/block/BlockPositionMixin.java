package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blockentities.BlockEntity;
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

    @Shadow public abstract Zone getZone();

    @Shadow public abstract BlockPosition getOffsetBlockPos(Zone zone, Direction d);

    @Shadow public abstract BlockEntity getBlockEntity();

    @Shadow public abstract BlockPosition set(Chunk chunk, int localX, int localY, int localZ);

    @Unique
    private final transient BlockPosition puzzleLoader$blockPosition = (BlockPosition)(Object)this;

    @Inject(method = "setBlockState", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(BlockState targetBlockState, CallbackInfo ci) {
        updateNeighbors(new BlockUpdateEvent());
    }


    @Override
    public Chunk getChunk() {
        return puzzleLoader$blockPosition.chunk();
    }


    @Override
    public void setGlobal(Zone zone, float x, float y, float z) {
        this.set(null, (int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
        this.convertToLocal(zone);
    }

    @Override
    public boolean hasBlockEntity() {
        return getBlockEntity() != null;
    }

    @Override
    public void updateNeighbors(BlockUpdateEvent event) {
        event.setSourcePosition(this);

        for (Direction direction : Direction.values()) {
            BlockPosition offs = getOffsetBlockPos(this.getZone(), direction);
            if (offs == null) continue;

            IBlockEntity entity = offs.getBlockEntity();

            if (entity != null)
                entity.updateNeighbors(event);
        }
    }

    @Override
    public void updateNeighborInDirection(BlockUpdateEvent event, Direction direction) {
        event.setSourcePosition(this);

        BlockPosition offs = getOffsetBlockPos(this.getZone(), direction);
        if (offs == null) return;

        IBlockEntity entity = offs.getBlockEntity();

        if (entity != null)
            entity.updateNeighbors(event);
    }

}
