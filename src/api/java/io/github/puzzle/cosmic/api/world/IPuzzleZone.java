package io.github.puzzle.cosmic.api.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.savelib.blocks.IBlockDataFactory;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockEntity;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.tmp.BlockStateTmpClass;
import io.github.puzzle.cosmic.api.tmp.EntityTmpClass;
import io.github.puzzle.cosmic.api.tmp.PlayerTmpClass;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntityUniqueId;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Zone")
public interface IPuzzleZone {

    String pGetAbsolutePath();
    IPuzzleWorld pGetWorld();

    IChunkManager pGetChunkManager();
    IPlayerManager pGetPlayerManager();
    IEntityManager pGetEntityManager();

    interface IChunkManager {

        void put(IPuzzleChunk chunk, boolean immediate);
        void put(IPuzzleChunk chunk);

        IPuzzleChunk get(int cx, int cy, int cz);
        IPuzzleChunk getAtBlock(int x, int y, int z);
        IPuzzleChunk getAtVector(Vector3 position);

        void remove(IPuzzleChunk chunk);

        int getNumberOfChunks();
    }

    interface IPlayerManager {
        void addPlayer(IPuzzlePlayer player);
        void removePlayer(IPuzzlePlayer player);

        void forEachPlayer(Consumer<IPuzzlePlayer> playerConsumer);

        Array<PlayerTmpClass> getPlayers();
    }

    interface IEntityManager {

        void addEntity(IPuzzleEntity entity);

        IPuzzleEntity getEntity(IPuzzleEntityUniqueId id);
        boolean hasEntity(IPuzzleEntity e);

        void removeEntity(IPuzzleEntityUniqueId id);
        void removeEntity(IPuzzleEntity e);

        void despawnEntity(IPuzzleEntity entity);
        void forEachEntity(Consumer<IPuzzleEntity> consumer);

        Array<EntityTmpClass> getAllEntities();
    }

    IPuzzleBlockState pGetBlockState(Vector3 position);
    IPuzzleBlockEntity pGetBlockEntity(int x, int y, int z);

    IPuzzleBlockEntity pGetBlockEntity(IPuzzleChunk candidateChunk, int x, int y, int z);
    IPuzzleBlockState pGetBlockState(float x, float y, float z);
    IPuzzleBlockState pGetBlockState(int x, int y, int z);
    IPuzzleBlockState pGetBlockState(IPuzzleBlockPosition bp);

    IPuzzleBlockState pGetBlockState(IPuzzleChunk candidateChunk, int x, int y, int z);
    IPuzzleBlockState pGetBlockState(IPuzzleChunk candidateChunk, IPuzzleChunk candidateChunkB, int x, int y, int z);

    short pGetBlockLight(IPuzzleChunk candidateChunk, int x, int y, int z);
    int pGetSkyLight(IPuzzleChunk candidateChunk, int x, int y, int z);

    void pSetBlockState(IPuzzleBlockState block, int x, int y, int z);
    void pSetBlockState(IPuzzleBlockState block, int x, int y, int z, IBlockDataFactory<BlockStateTmpClass> chunkDataFactory);

    void pDispose();
    boolean pCalculateSpawn();
    boolean pValidateSpawnPoint();

    IPuzzleChunk pCreateBlankChunk(int cx, int cy, int cz);
    IPuzzleChunk pCreateBlankChunkAtBlock(int bx, int by, int bz);
    void pUpdate(float deltaTime);
    long pGetCurrentWorldTick();

    void pSetSkyId(String skyId);
    String pGetSkyId();
    String pGetDefaultSkyId();

    void pSetWorld(IPuzzleWorld world);

    String pGetStringID();
    IPuzzleIdentifier pGetId();

}
