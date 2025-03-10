package io.github.puzzle.cosmic.impl.mixin.entity.player;

import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.entities.player.skins.GameTexturePlayerSkin;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayerEntity;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Internal
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IPuzzlePlayerEntity {

    @Shadow public abstract Player getPlayer();

    @Shadow private transient GameTexturePlayerSkin playerSkin;

    @Shadow protected abstract boolean isLocalPlayer();

    @Shadow protected abstract void updateSkin();

    @Shadow protected abstract void spawnNameTag(String name);

    @Override
    public IPuzzlePlayer _getPlayer() {
        return IPuzzlePlayer.as(this.getPlayer());
    }

    @Override
    public GameTexturePlayerSkin _getPlayerSkin() {
        return this.playerSkin;
    }

    @Override
    public void _setPlayerSkin(GameTexturePlayerSkin PlayerSkin) {
        this.playerSkin = PlayerSkin;
    }

    @Override
    public boolean _isLocalPlayer() {
        return this.isLocalPlayer();
    }

    @Override
    public void _updateSkin() {
        this.updateSkin();
    }

    @Override
    public void _spawnNameTag(String name) {
        this.spawnNameTag(name);
    }
}
