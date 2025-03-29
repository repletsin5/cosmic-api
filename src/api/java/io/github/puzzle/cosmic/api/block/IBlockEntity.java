package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.tmp.Direction;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;


/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockEntity")
public interface IBlockEntity extends ICRBinSerializable {

    /**
     * Gets the Global X of the blockEntity.
     */
    int pGetGlobalX();

    /**
     * Gets the Global Y of the blockEntity.
     */
    int pGetGlobalY();

    /**
     * Gets the Global Z of the blockEntity.
     */
    int pGetGlobalZ();

    /**
     * Gets the Local X of the blockEntity.
     */
    default int pGetLocalX() {
        int chunkX = Math.floorDiv(pGetGlobalX(), 16);
        return pGetGlobalX() - chunkX * 16;
    }

    /**
     * Gets the Local X of the blockEntity.
     */
    default int pGetLocalY() {
        int chunkY = Math.floorDiv(pGetGlobalY(), 16);
        return pGetGlobalY() - chunkY * 16;
    }

    /**
     * Gets the Local X of the blockEntity.
     */
    default int pGetLocalZ() {
        int chunkZ = Math.floorDiv(pGetGlobalZ(), 16);
        return pGetGlobalZ() - chunkZ * 16;
    }

    /**
     * Gets the blockPosition of the blockEntity.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetBlockPosition();

    /**
     * Gets the zone of the blockEntity.
     * @return a {@link IZone}
     */
    IZone pGetZone();

    /**
     * Gets the chunk of the blockEntity.
     * @return a {@link IChunk}
     */
    IChunk pGetChunk();

    /**
     * Gets the identifier of the blockEntity.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetIdentifier();

    /**
     * Triggered when the blockEntity is created.
     * @param blockState The blockState of the blockEntity.
     */
    void pOnCreate(PBlockState blockState);

    /**
     * Triggered when the blockEntity is loaded.
     */
    void pOnLoad();

    /**
     * Triggered when the blockEntity is unloaded.
     */
    void pOnUnload();

    /**
     * Sets ticking for this blockEntity.
     */
    void pSetTicking(boolean ticking);

    /**
     * Triggered if the blockEntity is set to ticking every tick.
     * @see IBlockEntity#pSetTicking(boolean) pSetTicking for setting ticking.
     */
    void pOnTick();

    /**
     * Checks if the blockEntity is ticking.
     */
    boolean pIsTicking();

    /**
     * Triggered when a player interact with the blockEntity.
     * @param player the player that interact with the blockEntity.
     * @param zone the zone of the player.
     */
    void pOnInteract(IPlayer player, IZone zone);

    /**
     * Triggered when the blockState of the blockEntity is set.
     * @param blockState the blockState the blockEntity is set to.
     */
    void pOnSetBlockState(PBlockState blockState);

    /**
     * Sets the zone of the blockEntity.
     * @param zone The zone to be set.
     */
    void pSetZone(IZone zone);

    /**
     * Gets the blockState of the blockEntity.
     * @return a {@link PBlockState}
     */
    PBlockState pGetBlockState();

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
    void pUpdateNeighborInDirection(Direction direction, IBlockUpdateEvent event);

    /**
     * Triggered when the blockEntity is updated.
     * @param event the event the blockEntity is updated with.
     * @see IBlockUpdateEvent
     */
    void pOnNeighborUpdate(IBlockUpdateEvent event);

    /**
     * Gets the point manifest of the blockEntity.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest pGetPointManifest();

    /**
     * Sets the point manifest of the blockEntity.
     * @param manifest The new point manifest to be set.
     */
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer deserial);

    @Override
    void write(CRBinSerializer serial);

}
