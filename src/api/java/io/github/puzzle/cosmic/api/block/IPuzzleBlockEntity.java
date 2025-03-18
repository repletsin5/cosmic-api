package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.constants.Direction;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;


/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockEntity")
public interface IPuzzleBlockEntity extends ICRBinSerializable {

    int pGetGlobalX();
    int pGetGlobalY();
    int pGetGlobalZ();

    default int pGetLocalX() {
        int chunkX = Math.floorDiv(pGetGlobalX(), 16);
        return pGetGlobalX() - chunkX * 16;
    }

    default int pGetLocalY() {
        int chunkY = Math.floorDiv(pGetGlobalY(), 16);
        return pGetGlobalY() - chunkY * 16;
    }

    default int pGetLocalZ() {
        int chunkZ = Math.floorDiv(pGetGlobalZ(), 16);
        return pGetGlobalZ() - chunkZ * 16;
    }

    IPuzzleBlockPosition pGetBlockPosition();

    IPuzzleZone pGetZone();
    IPuzzleChunk pGetChunk();

    IPuzzleIdentifier pGetIdentifier();

    void pOnCreate(IPuzzleBlockState state);

    void pOnLoad();
    void pOnUnload();

    void pSetTicking(boolean ticking);
    void pOnTick();

    boolean pIsTicking();

    void pOnInteract(IPuzzlePlayer player, IPuzzleZone zone);
    void pOnSetBlockState(IPuzzleBlockState state);
    void pSetZone(IPuzzleZone zone);

    IPuzzleBlockState pGetBlockState();

    void pUpdateNeighbors(IBlockUpdateEvent event);
    void pUpdateNeighborInDirection(Direction direction, IBlockUpdateEvent event);

    void pOnNeighborUpdate(IBlockUpdateEvent event);

    IDataPointManifest pGetPointManifest();
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer deserial);

    @Override
    void write(CRBinSerializer serial);

}
