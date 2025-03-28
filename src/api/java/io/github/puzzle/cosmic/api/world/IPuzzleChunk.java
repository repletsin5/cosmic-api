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

    /**
     * Compacts all the chunk data.
     */
    void pCompact();

    /**
     * Disposes the mesh of the chunk.
     */
    void pDispose();

    /**
     * Fills the chuck with a blockState.
     * @param blockState the blockState to fill with.
     */
    void pFill(IPuzzleBlockState blockState);

    /**
     * Fills a layer of the chunk with a blockState.
     * @param blockState the blockState to fill with.
     * @param localY the layer to fill.
     */
    void pFillLayer(IPuzzleBlockState blockState, int localY);

    /**
     * Gets the block light of a block.
     * @param localX the local X of the block.
     * @param localY the local Y of the block.
     * @param localZ the local Z of the block.
     */
    short pGetBlockLight(int localX, int localY, int localZ);

    /**
     * Sets the block light of a block.
     * @param r the red component of the light color.
     * @param g the green component of the light color.
     * @param b the blue component of the light color.
     * @param localX the local X of the block.
     * @param localY the local Y of the block.
     * @param localZ the local Z of the block.
     */
    void pSetBlockLight(int r, int g, int b, int localX, int localY, int localZ);

    /**
     * Gets the Skylight of a block.
     * @param localX the local X of the block.
     * @param localY the local Y of the block.
     * @param localZ the local Z of the block.
     */
    int pGetSkyLight(int localX, int localY, int localZ);

    /**
     * Sets the Skylight of a block.
     * @param skyLight the light level to set.
     * @param localX the local X of the block.
     * @param localY the local Y of the block.
     * @param localZ the local Z of the block.
     */
    void pSetSkyLight(int skyLight, int localX, int localY, int localZ);

    /**
     * Gets the blockState of a Block in the chunk.
     * @param localX the local X of the block.
     * @param localY the local Y of the block.
     * @param localZ the local Z of the block.
     * @return a {@link IPuzzleBlockState}
     */
    IPuzzleBlockState pGetBlockState(int localX, int localY, int localZ);

    /**
     * Sets the blockState of a block.
     * @param blockState the new blockState.
     * @param x the local X of the block.
     * @param y the local Y of the block.
     * @param z the local Z of the block.
     */
    void pSetBlockState(IPuzzleBlockState blockState, int x, int y, int z);

    /**
     * Checks if the chunk is entirely opaque.
     */
    boolean pIsEntirelyOpaque();

    /**
     * Checks if the chunk is entirely one block self culling.
     */
    boolean pIsEntirelyOneBlockSelfCulling();

    /**
     * Gets the highest non-empty block index in the chunk.
     */
    int pGetMaxNonEmptyBlockIdxYXZ();

    /**
     * Gets the meshing controller of the chunk.
     * @return a {@link IMeshingController}
     */
    IMeshingController pGetMeshingController();

    /**
     * Gets the blockEntity controller of the chunk.
     * @return a {@link IBlockEntityController}
     */
    IBlockEntityController pGetBlockEntityController();

    interface IMeshingController {

        /**
         * Flags the chunk for remeshing.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagForRemeshing(boolean immediate);

        /**
         * Flags the horizontal touching chunk for remeshing.
         * @param zone the zone the chunk is in.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagHorizontalTouchingChunksForRemeshing(IPuzzleZone zone, boolean immediate);

        /**
         * Flags the touching chunk for remeshing.
         * @param zone the zone the chunk is in.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagTouchingChunksForRemeshing(IPuzzleZone zone, boolean immediate);

        /**
         * Flags the current chunk and its touching chunks for remeshing if necessary.
         * @param zone the zone the chuk is in.
         * @param localX the local X pos to start at.
         * @param localY the local Y pos to start at.
         * @param localZ the local Z pos to start at.
         * @param immediate if {@code true}, the chunk will be remeshed immediately, otherwise it will be queued for later processing.
         */
        void flagTouchingChunksForRemeshing(IPuzzleZone zone, int localX, int localY, int localZ, boolean immediate);

        /**
         * Sets the mesh group of the chunk.
         * @param meshGroup the new mesh group to set.
         */
        void setMeshGroup(IChunkMeshGroupTmp<?> meshGroup);

        /**
         * Gets the mesh group of the chunk.
         */
        IChunkMeshGroupTmp<?> getMeshGroup();
    }

    interface IBlockEntityController {

        /**
         * Gets the blockEntity at the position.
         * @param localX the local X of the blockEntity.
         * @param localY the local Y of the blockEntity.
         * @param localZ the local Z of the blockEntity.
         * @return a {@link IPuzzleBlockEntity}
         */
        IPuzzleBlockEntity get(int localX, int localY, int localZ);

        /**
         * Puts the blockEntity of the blockState.
         * @param blockState the blockState of the blockEntity.
         * @param localX the local X of where to put the blockEntity.
         * @param localY the local Y of where to put the blockEntity.
         * @param localZ the local Z of where to put the blockEntity.
         * @return a {@link IPuzzleBlockEntity}
         */
        IPuzzleBlockEntity put(IPuzzleBlockState blockState, int localX, int localY, int localZ);

        /**
         * Puts the blockEntity as the blockState.
         * @param blockState the blockState to set the BlockEntity as.
         * @param entity the blockEntity to set the blockState as.
         * @param localX the local X of where to put the blockEntity.
         * @param localY the local Y of where to put the blockEntity.
         * @param localZ the local Z of where to put the blockEntity.
         * @return a {@link IPuzzleBlockEntity}
         */
        IPuzzleBlockEntity put(IPuzzleBlockState blockState, IPuzzleBlockEntity entity, int localX, int localY, int localZ);

        /**
         * Iterates over each blockEntity in the chunk.
         * @param entityConsumer action to be performed.
         */
        void foreach(Consumer<IPuzzleBlockEntity> entityConsumer);

        /**
         * Gets the number of blockEntity in the chunk.
         */
        int getCount();

        /**
         * Checks if the chunk has any BlockEntity in it.
         */
        boolean isEmpty();
    }

    /**
     * Gets the chunk X position in block coordinates.
     */
    int pGetBlockX();

    /**
     * Gets the chunk Y position in block coordinates.
     */
    int pGetBlockY();

    /**
     * Gets the chunk Z position in block coordinates.
     */
    int pGetBlockZ();

    /**
     * Gets the chunk X position.
     */
    int pGetChunkX();

    /**
     * Gets the chunk Y position.
     */
    int pGetChunkY();

    /**
     * Gets the chunk Z position.
     */
    int pGetChunkZ();

}
