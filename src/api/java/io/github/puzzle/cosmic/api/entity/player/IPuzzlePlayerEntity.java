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

    IPuzzlePlayer _getPlayer();

    PlayerSkinTmpClass _getPlayerSkin();

    void _setPlayerSkin(PlayerSkinTmpClass playerSkin);

    boolean _isLocalPlayer();

    void _updateSkin();

    void _spawnNameTag(String name);

}
