package io.github.puzzle.cosmic.impl.mixin.entity.player;

import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.entities.player.skins.GameTexturePlayerSkin;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.entity.player.IPlayerEntity;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Internal
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IPlayerEntity {

    @Shadow(remap = false) public abstract Player getPlayer();

    @Shadow(remap = false) private transient GameTexturePlayerSkin playerSkin;

    @Shadow(remap = false) protected abstract boolean isLocalPlayer();

    @Shadow(remap = false) protected abstract void updateSkin();

    @Shadow(remap = false) protected abstract void spawnNameTag(String name);

    @Override
    public IPlayer pGetPlayer() {
        return IPlayer.as(this.getPlayer());
    }

    @Override
    public GameTexturePlayerSkin pGetPlayerSkin() {
        return this.playerSkin;
    }

    @Override
    public void pSetPlayerSkin(GameTexturePlayerSkin PlayerSkin) {
        this.playerSkin = PlayerSkin;
    }

    @Override
    public boolean pIsLocalPlayer() {
        return this.isLocalPlayer();
    }

    @Override
    public void pUpdateSkin() {
        this.updateSkin();
    }

    @Override
    public void pSpawnNameTag(String name) {
        this.spawnNameTag(name);
    }
}
