package io.github.puzzle.cosmic.api.block;

import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.tmp.Direction;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockPosition")
public interface IPuzzleBlockPosition {

    /**
     * Gets the Global X of the blockPosition.
     */
    int pGetGlobalX();

    /**
     * Gets the Global Y of the blockPosition.
     */
    int pGetGlobalY();

    /**
     * Gets the Global Z of the blockPosition.
     */
    int pGetGlobalZ();

    /**
     * Gets the Local X of the blockPosition.
     */
    int pGetLocalX();

    /**
     * Gets the Local Y of the blockPosition.
     */
    int pGetLocalY();

    /**
     * Gets the Local Z of the blockPosition.
     */
    int pGetLocalZ();

    /**
     * Gets the chunk of the blockPosition.
     * @return a {@link IPuzzleChunk}
     */
    @Nullable
    IPuzzleChunk pGetChunk();

    /**
     * Gets the zone of the blockPosition.
     * @return a {@link IPuzzleZone}
     */
    @Nullable
    IPuzzleZone pGetZone();

    /**
     * Gets the blockEntity of the blockPosition.
     * @return a {@link IPuzzleBlockEntity}
     */
    @Nullable
    IPuzzleBlockEntity pGetBlockEntity();

    /**
     * Indirectly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @return a {@link IPuzzleBlockEntity}
     * @see IPuzzleBlockPosition#pSetBlockEntityDirect(IPuzzleBlockState, IPuzzleBlockEntity)
     */
    IPuzzleBlockEntity pSetBlockEntity(IPuzzleBlockState blockState);

    /**
     * Directly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @param blockEntity the blockEntity to set.
     */
    void pSetBlockEntityDirect(IPuzzleBlockState blockState, IPuzzleBlockEntity blockEntity);

    /**
     * Checks if the blockPosition has a blockEntity.
     */
    default boolean pHasBlockEntity() {
        return pGetBlockEntity() != null;
    }

    /**
     * Sets the blockPosition.
     * @param chunk the new chunk.
     * @param localX the new local X.
     * @param localY the new local Y.
     * @param localZ the new local Z.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pSet(IPuzzleChunk chunk, int localX, int localY, int localZ);

    /**
     * Converts the blockPosition to local positions.
     * @param zone the zone of blockPosition.
     */
    void pConvertToLocal(IPuzzleZone zone);

    /**
     * Sets the global positions.
     * @param zone new zone.
     * @param x new X.
     * @param y new Y.
     * @param z new Z.
     */
    void pSetGlobal(IPuzzleZone zone, float x, float y, float z);

    /**
     * Gets the blockState of the blockPosition.
     * @return a {@link IPuzzleBlockState}
     */
    IPuzzleBlockState pGetBlockState();

    /**
     * Sets the blockState of the blockPosition.
     * @param blockState the blockState to set.
     */
    void pSetBlockState(IPuzzleBlockState blockState);

    /**
     * Gets the skylight.
     */
    int pGetSkylight();

    /**
     * Updates the neighbouring blockEntity.
     * @param event event to update the blockEntity with.
     * @see IBlockUpdateEvent
     */
    void pUpdateNeighbors(IBlockUpdateEvent event);

    /**
     * Updates the neighbouring blockEntity in a direction.
     * @param direction the direction to update in.
     * @param event event to update the blockEntity with.
     * @see Direction
     * @see IBlockUpdateEvent
     */
    void pUpdateNeighborInDirection(IBlockUpdateEvent event, Direction direction);

    /**
     * Gets the offset blockPosition.
     * @param zone zone of the blockPosition offset.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone zone, int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetOffsetBlockPos(int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param destBlockPos store the resulting position.
     * @param zone zone of the blockPosition offset.
     * @param offsetX X offset of the blockPosition.
     * @param offsetY Y offset of the blockPosition.
     * @param offsetZ Z offset of the blockPosition.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition destBlockPos, IPuzzleZone zone, int offsetX, int offsetY, int offsetZ);

    /**
     * Gets the offset blockPosition.
     * @param zone zone of the blockPosition offset.
     * @param d the direction to offset.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone zone, Direction d);

    /**
     * Gets the offset blockPosition.
     * @param destBlockPos store the resulting position.
     * @param zone zone of the blockPosition offset.
     * @param d the direction to offset.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition destBlockPos, IPuzzleZone zone, Direction d);
}
