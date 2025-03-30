package io.github.puzzle.cosmic.api.entity.player;

import io.github.puzzle.cosmic.api.tmp.GameTexturePlayerSkin;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author CrabK1ng
 * @since 0.4.1
 */
@ApiGen("PlayerEntity")
public interface IPlayerEntity {

    /**
     * Gets the player of this playerEntity.
     * @return a {@link IPlayer}
     */
    IPlayer pGetPlayer();

    /**
     * Gets the playerSkin of the playerEntity.
     */
    GameTexturePlayerSkin pGetPlayerSkin();

    /**
     * Sets the playerSkin of the playerEntity.
     * @param playerSkin the playerSkin to set.
     * @see IPlayerEntity#pUpdateSkin() use pUpdateSkin to update the skin after setting.
     */
    void pSetPlayerSkin(GameTexturePlayerSkin playerSkin);

    /**
     * Checks if the player is local.
     */
    boolean pIsLocalPlayer();

    /**
     * Updates the playerSkin.
     */
    void pUpdateSkin();

    /**
     * Sets the nameTag of the PlayerEntity.
     * @param name the name to set.
     */
    void pSpawnNameTag(String name);

}
