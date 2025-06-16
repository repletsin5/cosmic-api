package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IBlockPosition {

      /**
     * Gets the chunk of the blockPosition.
     * @return a {@link Chunk}
     */
    @Nullable
    Chunk getChunk();


    /**
     * Gets the blockEntity of the blockPosition.
     * @return a {@link IBlockEntity}
     */
    @Nullable
    IBlockEntity pGetBlockEntity();

    /**
     * Indirectly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @return a {@link IBlockEntity}
     * @see IBlockPosition#pSetBlockEntityDirect(PBlockState, IBlockEntity)
     */
    IBlockEntity pSetBlockEntity(PBlockState blockState);

    /**
     * Directly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @param blockEntity the blockEntity to set.
     */
    void pSetBlockEntityDirect(PBlockState blockState, IBlockEntity blockEntity);

    /**
     * Checks if the blockPosition has a blockEntity.
     */
    default boolean hasBlockEntity() {
        return pGetBlockEntity() != null;
    }

    /**
     * Sets the blockPosition.
     * @param chunk the new chunk.
     * @param localX the new local X.
     * @param localY the new local Y.
     * @param localZ the new local Z.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pSet(IChunk chunk, int localX, int localY, int localZ);

    /**
     * Sets the global positions.
     * @param zone new zone.
     * @param x new X.
     * @param y new Y.
     * @param z new Z.
     */
    void setGlobal(Zone zone, float x, float y, float z);

    /**
     * Gets the blockState of the blockPosition.
     * @return a {@link PBlockState}
     */
    PBlockState pGetBlockState();

    /**
     * Sets the blockState of the blockPosition.
     * @param blockState the blockState to set.
     */
    void pSetBlockState(PBlockState blockState);

    /**
     * Gets the skylight.
     */
    int pGetSkylight();

    /**
     * Updates the neighbouring blockEntity.
     * @param event event to update the blockEntity with.
     * @see BlockUpdateEvent
     */
    void updateNeighbors(BlockUpdateEvent event);

    /**
     * Updates the neighbouring blockEntity in a direction.
     * @param direction the direction to update in.
     * @param event event to update the blockEntity with.
     * @see Direction
     * @see BlockUpdateEvent
     */
    void updateNeighborInDirection(BlockUpdateEvent event, Direction direction);

    /**
     * Gets the offset blockPosition.
     * @param zone zone of the blockPosition offset.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetOffsetBlockPos(IZone zone, int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetOffsetBlockPos(int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param destBlockPos store the resulting position.
     * @param zone zone of the blockPosition offset.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetOffsetBlockPos(IBlockPosition destBlockPos, IZone zone, int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param zone zone of the blockPosition offset.
     * @param d the direction to offset.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetOffsetBlockPos(IZone zone, Direction d);

    /**
     * Gets the offset blockPosition.
     * @param destBlockPos store the resulting position.
     * @param zone zone of the blockPosition offset.
     * @param d the direction to offset.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetOffsetBlockPos(IBlockPosition destBlockPos, IZone zone, Direction d);
}
