package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.util.constants.Direction;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.IBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(BlockEntity.class)
public class BlockEntityMixin implements IBlockEntity {

    @Unique
    private final transient BlockEntity puzzleLoader$entity = IBlockEntity.as(this);

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    @Override
    public int pGetGlobalX() {
        return puzzleLoader$entity.getGlobalX();
    }

    @Override
    public int pGetGlobalY() {
        return puzzleLoader$entity.getGlobalY();
    }

    @Override
    public int pGetGlobalZ() {
        return puzzleLoader$entity.getGlobalZ();
    }

    @Override
    public IBlockPosition pGetBlockPosition() {
        return IBlockPosition.as(new BlockPosition(pGetChunk().as(), pGetLocalX(), pGetLocalY(), pGetLocalZ()));
    }

    @Override
    public IZone pGetZone() {
        return IZone.as(puzzleLoader$entity.getZone());
    }

    @Override
    public IChunk pGetChunk() {
        return IChunk.as(puzzleLoader$entity.getZone().getChunkAtBlock(
                pGetGlobalX(),
                pGetGlobalY(),
                pGetGlobalZ()
        ));
    }

    @Override
    public IIdentifier pGetIdentifier() {
        return (IIdentifier) Identifier.of(puzzleLoader$entity.getBlockEntityId());
    }

    @Override
    public void pOnCreate(IBlockState IBlockState) {
        puzzleLoader$entity.onCreate(IBlockState.as());
    }

    @Override
    public void pOnLoad() {
        puzzleLoader$entity.onLoad();
    }

    @Override
    public void pOnUnload() {
        puzzleLoader$entity.onUnload();
    }

    @Override
    public void pSetTicking(boolean b) {
        puzzleLoader$entity.setTicking(b);
    }

    @Override
    public void pOnTick() {
        puzzleLoader$entity.onTick();
    }

    @Override
    public boolean pIsTicking() {
        return puzzleLoader$entity.isTicking();
    }

    @Override
    public void pOnInteract(IPlayer IPlayer, IZone IZone) {
        puzzleLoader$entity.onInteract(IPlayer.as(), IZone.as());
    }

    @Override
    public void pOnSetBlockState(IBlockState IBlockState) {
        puzzleLoader$entity.onSetBlockState(IBlockState.as());
    }

    @Override
    public void pSetZone(IZone IZone) {
        puzzleLoader$entity.setZone(IZone.as());
    }

    @Override
    public IBlockState pGetBlockState() {
        return IBlockState.as(puzzleLoader$entity.getBlockState());
    }

    @Override
    public void pOnNeighborUpdate(IBlockUpdateEvent iBlockUpdateEvent) {
        // Implemented to prevent crash, can be overridden.
    }

    @Override
    public void pUpdateNeighbors(IBlockUpdateEvent event) {
        pGetBlockPosition().pUpdateNeighbors(event);
    }

    @Override
    public void pUpdateNeighborInDirection(Direction direction, IBlockUpdateEvent event) {
        pGetBlockPosition().pUpdateNeighborInDirection(event, direction);
    }

    @Override
    public BlockEntity as() {
        return puzzleLoader$entity;
    }

    @Inject(method = "read", at = @At("TAIL"), remap = false)
    private void write(CRBinDeserializer crbd, CallbackInfo ci) {
        IDataPointManifest manifest = crbd.readObj("point_manifest", DataPointManifest.class);
        if (manifest != null) pSetPointManifest(manifest);
    }

    @Inject(method = "write", at = @At("TAIL"), remap = false)
    private void write(CRBinSerializer crbs, CallbackInfo ci) {
        crbs.writeObj("point_manifest", puzzleLoader$manifest);
    }

    @Override
    public IDataPointManifest pGetPointManifest() {
        return puzzleLoader$manifest;
    }

    @Override
    public void pSetPointManifest(IDataPointManifest iDataPointManifest) {
        puzzleLoader$manifest = iDataPointManifest;
    }
}
