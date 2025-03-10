package io.github.puzzle.cosmic.impl.mixin.entity;

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

    @Shadow protected abstract void spawnNameTag(String name);

    @Shadow protected abstract void updateSkin();

    @Shadow protected abstract boolean isLocalPlayer();

    @Shadow
    transient GameTexturePlayerSkin playerSkin;
    @Shadow private transient Player player;

    @Override
    public IPuzzlePlayer _getPlayer() {
        return (IPuzzlePlayer) player;
    }

    @Override
    public GameTexturePlayerSkin _getPlayerSkin() {
        return playerSkin;
    }

    @Override
    public void _setPlayerSkin(GameTexturePlayerSkin var1) {
        this.playerSkin = var1;
    }

    @Override
    public boolean _isLocalPlayer() {
        return isLocalPlayer();
    }

    @Override
    public void _updateSkin() {
        updateSkin();
    }

    @Override
    public void _spawnNameTag(String var1) {
        spawnNameTag(var1);
    }

}
