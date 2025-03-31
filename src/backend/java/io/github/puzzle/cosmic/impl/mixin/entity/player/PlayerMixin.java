package io.github.puzzle.cosmic.impl.mixin.entity.player;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import io.github.puzzle.cosmic.api.account.IAccount;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.entity.player.IPlayerEntity;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IWorld;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Player.class)
public class PlayerMixin implements IPlayer {

    @Unique
    private final transient Player puzzleLoader$player = IPlayer.as(this);

    @Override
    public IEntity pGetEntity() {
        return IEntity.as(puzzleLoader$player.getEntity());
    }

    @Override
    public IPlayerEntity pGetPlayerEntity() {
        return IPlayerEntity.as((PlayerEntity) puzzleLoader$player.getEntity());
    }

    @Override
    public void pProneCheck(IZone IZone) {
        puzzleLoader$player.proneCheck(IZone.as());
    }

    @Override
    public void pCrouchCheck(IZone IZone) {
        puzzleLoader$player.crouchCheck(IZone.as());
    }

    @Override
    public void pRespawn(IWorld IWorld) {
        puzzleLoader$player.respawn(IWorld.as());
    }

    @Override
    public void pRespawn(IZone IZone) {
        puzzleLoader$player.respawn(IZone.as());
    }

    @Override
    public void pSetPosition(float x, float y, float z) {
        puzzleLoader$player.getEntity().setPosition(x, y, z);
    }

    @Override
    public IZone pGetZone() {
        return IZone.as(GameSingletons.world.getZoneCreateIfNull(puzzleLoader$player.zoneId));
    }

    @Override
    public IChunk pGetChunk(IWorld world) {
        return IChunk.as(puzzleLoader$player.getChunk(world.as()));
    }

    @Override
    public short pGetBlockLight(IWorld IWorld) {
        return puzzleLoader$player.getBlockLight(IWorld.as());
    }

    @Override
    public int pGetSkyLight(IWorld IWorld) {
        return puzzleLoader$player.getSkyLight(IWorld.as());
    }

    @Override
    public void pSpawnDroppedItem(IWorld IWorld, IItemStack IItemStack) {
        puzzleLoader$player.spawnDroppedItem(IWorld.as(), IItemStack.as());
    }

    @Override
    public boolean pIsLoading() {
        return puzzleLoader$player.loading;
    }

    @Override
    public IAccount pGetAccount() {
        return IAccount.as(GameSingletons.getAccountFromPlayer(as()));
    }

    @Override
    public IBlockPosition pGetBlockPosition() {
        return IBlockPosition.as(BlockPosition.ofGlobal(
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
