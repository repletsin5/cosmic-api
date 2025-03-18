package io.github.puzzle.cosmic.api.block;

import io.github.puzzle.cosmic.api.constants.Direction;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
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

    int pGetGlobalX();
    int pGetGlobalY();
    int pGetGlobalZ();

    int pGetLocalX();
    int pGetLocalY();
    int pGetLocalZ();

    @Nullable
    IPuzzleChunk pGetChunk();

    @Nullable
    IPuzzleZone pGetZone();

    @Nullable
    IPuzzleBlockEntity pGetBlockEntity();
    IPuzzleBlockEntity pSetBlockEntity(IPuzzleBlockState state);

    default boolean pHasBlockEntity() {
        return pGetBlockEntity() != null;
    }

    IPuzzleBlockPosition pSet(IPuzzleChunk chunk, int localX, int localY, int localZ);

    void pConvertToLocal(IPuzzleZone zone);
    void pSetGlobal(IPuzzleZone zone, float x, float y, float z);

    IPuzzleBlockState pGetBlockState();
    void pSetBlockState(IPuzzleBlockState state);

    int pGetSkylight();

    void pUpdateNeighbors(IBlockUpdateEvent event);
    void pUpdateNeighborInDirection(IBlockUpdateEvent event, Direction direction);

    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone zone, int offsetX, int offsetY, int offsetZ);
    IPuzzleBlockPosition pGetOffsetBlockPos(int offsetX, int offsetY, int offsetZ);
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition destBlockPos, IPuzzleZone zone, int offsetX, int offsetY, int offsetZ);
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleZone zone, Direction d);
    IPuzzleBlockPosition pGetOffsetBlockPos(IPuzzleBlockPosition destBlockPos, IPuzzleZone zone, Direction d);
}
