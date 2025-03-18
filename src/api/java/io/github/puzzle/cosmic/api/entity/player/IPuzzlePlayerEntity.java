package io.github.puzzle.cosmic.api.entity.player;

import io.github.puzzle.cosmic.api.constants.PlayerSkinTmpClass;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author CrabK1ng
 * @since 0.4.1
 */
@ApiGen("PlayerEntity")
public interface IPuzzlePlayerEntity {

    IPuzzlePlayer pGetPlayer();

    PlayerSkinTmpClass pGetPlayerSkin();

    void pSetPlayerSkin(PlayerSkinTmpClass playerSkin);

    boolean pIsLocalPlayer();

    void pUpdateSkin();

    void pSpawnNameTag(String name);

}
