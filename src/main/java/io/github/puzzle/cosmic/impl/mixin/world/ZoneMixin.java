package io.github.puzzle.cosmic.impl.mixin.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.savelib.blocks.IBlockDataFactory;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.IEntityUniqueId;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IWorld;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Consumer;

@Internal
@Mixin(Zone.class)
public class ZoneMixin implements IZone {

    @Unique
    private final transient Zone puzzleLoader$zone = IZone.as(this);

    @Override
    public IChunkManager pGetChunkManager() {
        return puzzleLoader$chunkManager;
    }

    @Override
    public IEntityManager pGetEntityManager() {
        return puzzleLoader$entityManager;
    }

    @Override
    public IPlayerManager pGetPlayerManager() {
        return puzzleLoader$playerManager;
    }

    @Override
    public String pGetAbsolutePath() {
        return puzzleLoader$zone.getFullSaveFolder();
    }

    @Override
    public IWorld pGetWorld() {
        return IWorld.as(puzzleLoader$zone.getWorld());
    }

    @Unique
    private final transient IChunkManager puzzleLoader$chunkManager = new IChunkManager() {

        @Override
        public void put(IChunk IChunk, boolean b) {
            puzzleLoader$zone.addOrReplaceChunk(IChunk.as(), b);
        }

        @Override
        public void put(IChunk IChunk) {
            puzzleLoader$zone.addChunk(IChunk.as());
        }

        @Override
        public IChunk get(int i, int i1, int i2) {
            return IChunk.as(puzzleLoader$zone.getChunkAtChunkCoords(i, i1, i2));
        }

        @Override
        public IChunk getAtBlock(int i, int i1, int i2) {
            return IChunk.as(puzzleLoader$zone.getChunkAtBlock(i, i1, i2));
        }

        @Override
        public IChunk getAtVector(Vector3 vector3) {
            return IChunk.as(puzzleLoader$zone.getChunkAtPosition(vector3));
        }

        @Override
        public void remove(IChunk IChunk) {
            puzzleLoader$zone.removeChunk(IChunk.as());
        }

        @Override
        public int getNumberOfChunks() {
            return puzzleLoader$zone.getNumberOfChunks();
        }
    };

    @Unique
    private final transient IEntityManager puzzleLoader$entityManager = new IEntityManager() {

        @Override
        public void addEntity(IEntity IEntity) {
            puzzleLoader$zone.addEntity(IEntity.as());
        }

        @Override
        public IEntity getEntity(IEntityUniqueId IEntityUniqueId) {
            return IEntity.as(puzzleLoader$zone.getEntity(IEntityUniqueId.as()));
        }

        @Override
        public boolean hasEntity(IEntity IEntity) {
            return puzzleLoader$zone.hasEntity(IEntity.as());
        }

        @Override
        public void removeEntity(IEntityUniqueId IEntityUniqueId) {
            puzzleLoader$zone.removeEntity(IEntityUniqueId.as());
        }

        @Override
        public void removeEntity(IEntity IEntity) {
            puzzleLoader$zone.removeEntity(IEntity.as());
        }

        @Override
        public void despawnEntity(IEntity IEntity) {
            puzzleLoader$zone.despawnEntity(IEntity.as());
        }

        @Override
        public void forEachEntity(Consumer<IEntity> consumer) {
            puzzleLoader$zone.forEachEntity((c) -> consumer.accept(IEntity.as(c)));
        }

        @Override
        public Array<Entity> getAllEntities() {
            return puzzleLoader$zone.getAllEntities();
        }
    };

    @Unique
    private final transient IPlayerManager puzzleLoader$playerManager = new IPlayerManager() {

        @Override
        public void addPlayer(IPlayer IPlayer) {
            puzzleLoader$zone.addPlayer(IPlayer.as());
        }

        @Override
        public void removePlayer(IPlayer IPlayer) {
            puzzleLoader$zone.removePlayer(IPlayer.as());
        }

        @Override
        public void forEachPlayer(Consumer<IPlayer> consumer) {
            puzzleLoader$zone.forEachPlayer((c) -> consumer.accept(IPlayer.as(c)));
        }

        @Override
        public Array<Player> getPlayers() {
            return puzzleLoader$zone.getPlayers();
        }
    };

    @Override
    public PBlockState pGetBlockState(IBlockPosition IBlockPosition) {
        return IBlockPosition.pGetBlockState();
    }

    public PBlockState pGetBlockState(Vector3 position) {
        return PBlockState.as(puzzleLoader$zone.getBlockState(position));
    }

    public IBlockEntity pGetBlockEntity(int x, int y, int z) {
        return IBlockEntity.as(puzzleLoader$zone.getBlockEntity(x, y, z));
    }

    public IBlockEntity pGetBlockEntity(IChunk candidateChunk, int x, int y, int z) {
        return IBlockEntity.as(puzzleLoader$zone.getBlockEntity(candidateChunk.as(), x, y, z));
    }

    public PBlockState pGetBlockState(float x, float y, float z) {
        return PBlockState.as(puzzleLoader$zone.getBlockState(x, y, z));
    }

    public PBlockState pGetBlockState(int x, int y, int z) {
        return PBlockState.as(puzzleLoader$zone.getBlockState(x, y, z));
    }

    public PBlockState pGetBlockState(IChunk candidateChunk, int x, int y, int z) {
        return PBlockState.as(puzzleLoader$zone.getBlockState(candidateChunk.as(), x, y, z));
    }

    public PBlockState pGetBlockState(IChunk candidateChunk, IChunk candidateChunkB, int x, int y, int z) {
        return PBlockState.as(puzzleLoader$zone.getBlockState(candidateChunk.as(), candidateChunkB.as(), x, y, z));
    }

    @Override
    public short pGetBlockLight(IChunk IChunk, int i, int i1, int i2) {
        return puzzleLoader$zone.getBlockLight(IChunk.as(), i, i1, i2);
    }

    @Override
    public int pGetSkyLight(IChunk IChunk, int i, int i1, int i2) {
        return puzzleLoader$zone.getSkyLight(IChunk.as(), i, i1, i2);
    }

    @Override
    public void pSetBlockState(PBlockState state, int i, int i1, int i2, IBlockDataFactory<BlockState> iBlockDataFactory) {
        puzzleLoader$zone.setBlockState(state.as(), i, i1, i2, iBlockDataFactory);
    }

    @Override
    public void pSetBlockState(PBlockState state, int i, int i1, int i2) {
        puzzleLoader$zone.setBlockState(state.as(), i, i1, i2);
    }

    @Override
    public void pDispose() {
        puzzleLoader$zone.dispose();
    }

    @Override
    public boolean pCalculateSpawn() {
        return puzzleLoader$zone.calculateSpawn();
    }

    @Override
    public boolean pValidateSpawnPoint() {
        return puzzleLoader$zone.validateSpawnPoint();
    }

    @Override
    public IChunk pCreateBlankChunk(int i, int i1, int i2) {
        return IChunk.as(puzzleLoader$zone.createBlankChunk(i, i1, i2));
    }

    @Override
    public IChunk pCreateBlankChunkAtBlock(int i, int i1, int i2) {
        return IChunk.as(puzzleLoader$zone.createBlankChunkAtBlock(i, i1, i2));
    }

    @Override
    public void pUpdate(float v) {
        puzzleLoader$zone.update(v);
    }

    @Override
    public long pGetCurrentWorldTick() {
        return puzzleLoader$zone.getCurrentWorldTick();
    }

    @Override
    public void pSetSkyId(String s) {
        puzzleLoader$zone.setSkyId(s);
    }

    @Override
    public String pGetSkyId() {
        return puzzleLoader$zone.getSkyId();
    }

    @Override
    public String pGetDefaultSkyId() {
        return puzzleLoader$zone.getDefaultSkyId();
    }

    @Override
    public void pSetWorld(IWorld IWorld) {
        puzzleLoader$zone.setWorld(IWorld.as());
    }

    @Override
    public String pGetStringID() {
        return puzzleLoader$zone.zoneId;
    }

    @Override
    public IIdentifier pGetId() {
        return IIdentifier.as(Identifier.of(puzzleLoader$zone.zoneId));
    }
}
