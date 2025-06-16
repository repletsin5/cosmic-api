package io.github.puzzle.cosmic.api.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.savelib.blocks.IBlockDataFactory;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PuzzleBlockState;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.IEntityUniqueId;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Zone")
public interface IZone {

    /**
     * Gets the absolute path of the zone's folder.
     */
    String pGetAbsolutePath();

    /**
     * Gets the world.
     * @return a {@link IWorld}
     */
    IWorld pGetWorld();

    /**
     * Gets the chunk manager of the zone.
     * @return a {@link IChunkManager}
     */
    IChunkManager pGetChunkManager();

    /**
     * Gets the player manager of the zone.
     * @return a {@link IPlayerManager}
     */
    IPlayerManager pGetPlayerManager();

    /**
     * Gets the entity manager of the zone.
     * @return a {@link IEntityManager}
     */
    IEntityManager pGetEntityManager();

    interface IChunkManager {

        /**
         * Adds or replaces the chunk.
         * @param chunk  the chunk to add.
         * @param immediate {@code true} if the chunk should remeshing immediate.
         */
        void put(IChunk chunk, boolean immediate);

        /**
         * Adds a new chunk.
         * @param chunk the chunk to add.
         */
        void put(IChunk chunk);

        /**
         * Gets the chunk at the position.
         * @param cx the chunks X.
         * @param cy the chunks Y.
         * @param cz the chunks Z.
         * @return a {@link IChunk}
         */
        IChunk get(int cx, int cy, int cz);

        /**
         * Gets the chunk at the block position.
         * @param x the x block position.
         * @param y the Y block position.
         * @param z the Z block position.
         * @return a {@link IChunk}
         */
        IChunk getAtBlock(int x, int y, int z);

        /**
         * Gets the chunk at the block position.
         * @param position the block position.
         * @return a {@link IChunk}
         */
        IChunk getAtVector(Vector3 position);

        /**
         * Removes the chunk.
         * @param chunk the chunk to remove.
         */
        void remove(IChunk chunk);

        /**
         * Gets the number of chunks in the zone.
         */
        int getNumberOfChunks();
    }

    interface IPlayerManager {
        /**
         * Adds the player to the zone.
         * @param player the player to add.
         */
        void addPlayer(IPlayer player);

        /**
         * Removes the player.
         * @param player the player to remove.
         */
        void removePlayer(IPlayer player);

        /**
         * Iterates over each player.
         * @param playerConsumer action to be performed.
         */
        void forEachPlayer(Consumer<IPlayer> playerConsumer);

        /**
         * Gets all the players in the zone.
         * @return a {@link Array< Player >} of players.
         */
        Array<Player> getPlayers();
    }

    interface IEntityManager {

        /**
         * Adds the entity to the zone.
         * @param entity the entity to add.
         */
        void addEntity(IEntity entity);

        /**
         * Gets the entity from its EntityUniqueId.
         * @param id the EntityUniqueId of the entity.
         * @return a {@link IEntity}
         */
        IEntity getEntity(IEntityUniqueId id);

        /**
         * Checks if the zone has the entity.
         * @param entity the entity to check.
         */
        boolean hasEntity(IEntity entity);

        /**
         * Removes the entity.
         * @param id the EntityUniqueId of the entity to remove.
         */
        void removeEntity(IEntityUniqueId id);

        /**
         * Removes the entity.
         * @param entity the entity to be removed.
         */
        void removeEntity(IEntity entity);

        /**
         * Despawns the entity.
         * @param entity the entity to despawn.
         */
        void despawnEntity(IEntity entity);

        /**
         * Iterates over each entity.
         * @param consumer  action to be performed.
         */
        void forEachEntity(Consumer<IEntity> consumer);

        /**
         * Gets all the entity in the zone.
         * @return a {@link Array<  Entity  >} of entities.
         */
        Array<Entity> getAllEntities();
    }

    /**
     * Gets the blockEntity at position.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link IBlockEntity}
     */
    IBlockEntity pGetBlockEntity(int x, int y, int z);

    /**
     * Gets the blockEntity at position.
     * @param candidateChunk the chunk the blockEntity might be in.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link IBlockEntity}
     */
    IBlockEntity pGetBlockEntity(IChunk candidateChunk, int x, int y, int z);

    /**
     * Gets the blockState of the position.
     * @param position the position to get.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(Vector3 position);

    /**
     * Gets the blockState of the position.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(float x, float y, float z);

    /**
     * Gets the blockState of the position.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(int x, int y, int z);

    /**
     * Gets the blockState of the blockPosition.
     * @param blockPosition the blockPosition.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(IBlockPosition blockPosition);

    /**
     * Gets the blockState of the position.
     * @param candidateChunk the chunk the block might be in.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(IChunk candidateChunk, int x, int y, int z);

    /**
     * Gets the blockState of the position.
     * @param candidateChunk the chunk the block might be in.
     * @param candidateChunkB the second chunk the block might be in.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @return a {@link PuzzleBlockState}
     */
    PuzzleBlockState pGetBlockState(IChunk candidateChunk, IChunk candidateChunkB, int x, int y, int z);

    /**
     * Sets the blockState at the position.
     * @param blockState the blockState to set.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     */
    void pSetBlockState(PuzzleBlockState blockState, int x, int y, int z);

    /**
     * Sets the blockState at the position.
     * @param blockState the blockState to set.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     * @param chunkDataFactory the IBlockDataFactory.
     */
    void pSetBlockState(PuzzleBlockState blockState, int x, int y, int z, IBlockDataFactory<BlockState> chunkDataFactory);

    /**
     * Gets the block light of the position.
     * @param candidateChunk the chunk the position might be in.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     */
    short pGetBlockLight(IChunk candidateChunk, int x, int y, int z);

    /**
     * Gets the skylight of the position.
     * @param candidateChunk the chunk the position might be in.
     * @param x the X position.
     * @param y the Y position.
     * @param z the Z position.
     */
    int pGetSkyLight(IChunk candidateChunk, int x, int y, int z);


    void pDispose();

    /**
     * Calculates the spawn for the zone.
     */
    boolean pCalculateSpawn();

    /**
     * Checks if the zone haves a validate spawn point.
     */
    boolean pValidateSpawnPoint();

    /**
     * Creates a blank chunk.
     * @param cx the chunk X position.
     * @param cy the chunk Y position.
     * @param cz the chunk Z position.
     * @return a {@link IChunk}
     */
    IChunk pCreateBlankChunk(int cx, int cy, int cz);

    /**
     * Creates a blank chunk at a block.
     * @param bx the block X position.
     * @param by the block Y position.
     * @param bz the block Z position.
     * @return a {@link IChunk}
     */
    IChunk pCreateBlankChunkAtBlock(int bx, int by, int bz);

    /**
     * Updates the zone.
     * @param deltaTime the deltaTime.
     */
    void pUpdate(float deltaTime);

    /**
     * Gets the current world tick.
     */
    long pGetCurrentWorldTick();

    /**
     * Sets the skyID.
     * @param skyId the new skyID.
     */
    void pSetSkyId(String skyId);

    /**
     * Gets the skyID.
     */
    String pGetSkyId();

    /**
     * Gets the defaultSkyID.
     */
    String pGetDefaultSkyId();

    /**
     * Sets th world for the zone.
     * @param world the world to set.
     */
    void pSetWorld(IWorld world);

    /**
     * Gets the zoneID as a string.
     */
    String pGetStringID();

    /**
     * Gets the zoneID.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetId();

}
