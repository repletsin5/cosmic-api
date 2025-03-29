package io.github.puzzle.cosmic.api.entity.player;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.account.IAccount;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IWorld;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Player")
public interface IPlayer {

    /**
     * Gets the entity of the player.
     * @return a {@link IEntity}
     */
    IEntity pGetEntity();

    /**
     * Gets the playerEntity of the Player.
     * @return a {@link IPlayerEntity}
     */
    IPlayerEntity pGetPlayerEntity();

    /**
     * Gets the view offset of the player.
     */
    Vector3 pGetViewOffset();

    /**
     * Checks if the player should be prone.
     * @param zone the zone of the player.
     */
    void pProneCheck(IZone zone);

    /**
     * Checks if the player should be crouched.
     * @param zone the zone of the player.
     */
    void pCrouchCheck(IZone zone);

    /**
     * Respawns the player.
     * @param world the world the player is in.
     */
    void pRespawn(IWorld world);

    /**
     * Respawns the player.
     * @param zone the zone the player is in.
     */
    void pRespawn(IZone zone);

    /**
     * Sets the player position.
     * @param x the new x.
     * @param y the new Y.
     * @param z the new Z.
     */
    void pSetPosition(float x, float y, float z);

    /**
     * Gets the zone the player is in.
     * @return a {@link IZone}
     */
    IZone pGetZone();

    /**
     * Gets the chunk the player is in.
     * @param world the world the player is in.
     * @return a {@link IChunk}
     */
    IChunk pGetChunk(IWorld world);

    /**
     * Gets the light of the block at the player position.
     * @param world the world the player is in.
     */
    short pGetBlockLight(IWorld world);

    /**
     * Gets the skylight of the block at the player position.
     * @param world the world the player is in.
     */
    int pGetSkyLight(IWorld world);

    /**
     * Drops an item from the player.
     * @param world the world the player is in.
     * @param itemStack the item to be dropped.
     */
    void pSpawnDroppedItem(IWorld world, IItemStack itemStack);

    /**
     * Gets the player position as a blockPosition.
     * @return a {@link IBlockPosition}
     */
    IBlockPosition pGetBlockPosition();

    /**
     * Gets the player position.
     */
    Vector3 pGetPosition();

    /**
     * Checks if the player is loading.
     */
    boolean pIsLoading();

    /**
     * Sets the players zone.
     * @param zone the zone to set.
     */
    default void pSetZone(IZone zone) {
        pSetZone(zone.pGetId());
    }

    /**
     * Sets the players zone.
     * @param zoneId the zoneId of a zone to set.
     */
    default void pSetZone(IIdentifier zoneId) {
        pSetZone(zoneId.asString());
    }

    /**
     * Sets the players zone.
     * @param zoneId the zoneId of a zone to set.
     */
    void pSetZone(String zoneId);

    /**
     * Checks if the player is dead.
     */
    boolean pIsDead();

    /**
     * Gets the account of the player.
     * @return a {@link IAccount}
     */
    IAccount pGetAccount();

    /**
     * Gets the username of the player.
     */
    String pGetUsername();

}
