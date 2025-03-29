package io.github.puzzle.cosmic.api.block;

import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.tmp.Direction;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockPosition")
public interface IBlockPosition {

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
     * @return a {@link IChunk}
     */
    @Nullable
    IChunk pGetChunk();

    /**
     * Gets the zone of the blockPosition.
     * @return a {@link IZone}
     */
    @Nullable
    IZone pGetZone();

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
    default boolean pHasBlockEntity() {
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
     * Converts the blockPosition to local positions.
     * @param zone the zone of blockPosition.
     */
    void pConvertToLocal(IZone zone);

    /**
     * Sets the global positions.
     * @param zone new zone.
     * @param x new X.
     * @param y new Y.
     * @param z new Z.
     */
    void pSetGlobal(IZone zone, float x, float y, float z);

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
