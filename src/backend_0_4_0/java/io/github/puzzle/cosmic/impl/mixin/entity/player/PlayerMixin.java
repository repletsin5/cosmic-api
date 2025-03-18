package io.github.puzzle.cosmic.impl.mixin.entity.player;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import io.github.puzzle.cosmic.api.account.IPuzzleAccount;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayerEntity;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleWorld;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Player.class)
public class PlayerMixin implements IPuzzlePlayer {

    @Unique
    private final transient Player puzzleLoader$player = IPuzzlePlayer.as(this);

    @Override
    public IPuzzleEntity pGetEntity() {
        return IPuzzleEntity.as(puzzleLoader$player.getEntity());
    }

    @Override
    public IPuzzlePlayerEntity pGetPlayerEntity() {
        return IPuzzlePlayerEntity.as((PlayerEntity) puzzleLoader$player.getEntity());
    }

    @Override
    public void pProneCheck(IPuzzleZone iPuzzleZone) {
        puzzleLoader$player.proneCheck(iPuzzleZone.as());
    }

    @Override
    public void pCrouchCheck(IPuzzleZone iPuzzleZone) {
        puzzleLoader$player.crouchCheck(iPuzzleZone.as());
    }

    @Override
    public void pRespawn(IPuzzleWorld iPuzzleWorld) {
        puzzleLoader$player.respawn(iPuzzleWorld.as());
    }

    @Override
    public void pRespawn(IPuzzleZone iPuzzleZone) {
        puzzleLoader$player.respawn(iPuzzleZone.as());
    }

    @Override
    public void pSetPosition(float x, float y, float z) {
        puzzleLoader$player.getEntity().setPosition(x, y, z);
    }

    @Override
    public IPuzzleZone pGetZone() {
        return IPuzzleZone.as(GameSingletons.world.getZoneCreateIfNull(puzzleLoader$player.zoneId));
    }

    @Override
    public IPuzzleChunk pGetChunk(IPuzzleWorld world) {
        return IPuzzleChunk.as(puzzleLoader$player.getChunk(world.as()));
    }

    @Override
    public short pGetBlockLight(IPuzzleWorld iPuzzleWorld) {
        return puzzleLoader$player.getBlockLight(iPuzzleWorld.as());
    }

    @Override
    public int pGetSkyLight(IPuzzleWorld iPuzzleWorld) {
        return puzzleLoader$player.getSkyLight(iPuzzleWorld.as());
    }

    @Override
    public void pSpawnDroppedItem(IPuzzleWorld iPuzzleWorld, IPuzzleItemStack iPuzzleItemStack) {
        puzzleLoader$player.spawnDroppedItem(iPuzzleWorld.as(), iPuzzleItemStack.as());
    }

    @Override
    public boolean pIsLoading() {
        return puzzleLoader$player.loading;
    }

    @Override
    public IPuzzleAccount pGetAccount() {
        return IPuzzleAccount.as(GameSingletons.getAccountFromPlayer(as()));
    }

    @Override
    public IPuzzleBlockPosition pGetBlockPosition() {
        return IPuzzleBlockPosition.as(BlockPosition.ofGlobal(
                puzzleLoader$player.getZone(),
                (int) puzzleLoader$player.getPosition().x,
                (int) puzzleLoader$player.getPosition().y,
                (int) puzzleLoader$player.getPosition().z
        ));
    }

    @Override
    public boolean pIsDead() {
        return puzzleLoader$player.isDead();
    }

    @Override
    public String pGetUsername() {
        return puzzleLoader$player.getUsername();
    }

    @Override
    public Vector3 pGetPosition() {
        return puzzleLoader$player.getPosition();
    }

    @Override
    public void pSetZone(String s) {
        puzzleLoader$player.setZone(s);
    }

    @Override
    public Vector3 pGetViewOffset() {
        Vector3 viewOffset = new Vector3();
        viewOffset.set(puzzleLoader$player.standingViewPositionOffset);
        if (puzzleLoader$player.isProne) viewOffset.set(puzzleLoader$player.proneViewPositionOffset);
        if (puzzleLoader$player.isSneakIntended) viewOffset.set(puzzleLoader$player.proneViewPositionOffset);
        return viewOffset;
    }
}
