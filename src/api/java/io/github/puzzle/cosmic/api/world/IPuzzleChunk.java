package io.github.puzzle.cosmic.api.world;

import io.github.puzzle.cosmic.api.block.IPuzzleBlockEntity;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.tmp.IChunkMeshGroupTmp;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Chunk")
public interface IPuzzleChunk {

    void pCompact();
    void pDispose();

    void pFill(IPuzzleBlockState state);
    void pFillLayer(IPuzzleBlockState state, int localY);

    short pGetBlockLight(int localX, int localY, int localZ);
    int pGetSkyLight(int localX, int localY, int localZ);
    IPuzzleBlockState pGetBlockState(int localX, int localY, int localZ);

    void pSetBlockLight(int r, int g, int b, int localX, int localY, int localZ);
    void pSetBlockState(IPuzzleBlockState state, int x, int y, int z);
    void pSetSkyLight(int skyLight, int localX, int localY, int localZ);

    boolean pIsEntirelyOpaque();
    boolean pIsEntirelyOneBlockSelfCulling();
    int pGetMaxNonEmptyBlockIdxYXZ();

    IMeshingController pGetMeshingController();
    IBlockEntityController pGetBlockEntityController();

    interface IMeshingController {

        void flagForRemeshing(boolean immediate);
        void flagHorizontalTouchingChunksForRemeshing(IPuzzleZone zone, boolean immediate);
        void flagTouchingChunksForRemeshing(IPuzzleZone zone, boolean immediate);
        void flagTouchingChunksForRemeshing(IPuzzleZone zone, int localX, int localY, int localZ, boolean immediate);

        void setMeshGroup(IChunkMeshGroupTmp<?> meshGroup);
        IChunkMeshGroupTmp<?> getMeshGroup();
    }

    interface IBlockEntityController {

        IPuzzleBlockEntity get(int localX, int localY, int localZ);
        IPuzzleBlockEntity put(IPuzzleBlockState state, int localX, int localY, int localZ);
        IPuzzleBlockEntity put(IPuzzleBlockState state, IPuzzleBlockEntity entity, int localX, int localY, int localZ);

        void foreach(Consumer<IPuzzleBlockEntity> entityConsumer);

        int getCount();
        boolean isEmpty();
    }

    int pGetBlockX();
    int pGetBlockY();
    int pGetBlockZ();

    int pGetChunkX();
    int pGetChunkY();
    int pGetChunkZ();

}
