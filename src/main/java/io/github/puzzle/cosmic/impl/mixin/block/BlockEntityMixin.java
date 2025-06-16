package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.IBlockEntityWithContainer;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.event.IBlockUpdateEvent;
import io.github.puzzle.cosmic.api.item.container.PSlotContainer;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements IBlockEntity {

    @Shadow public abstract Zone getZone();

    @Shadow public abstract int getGlobalX();

    @Shadow public abstract int getGlobalY();

    @Shadow public abstract int getGlobalZ();

    @Unique
    private final transient BlockEntity puzzleLoader$entity = (BlockEntity)(Object)this;

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    @Override
    public int getLocalX() {
        return BlockPosition.ofGlobal(puzzleLoader$entity.getZone(), puzzleLoader$entity.getGlobalX(), puzzleLoader$entity.getGlobalY(), puzzleLoader$entity.getGlobalZ()).localX();
    }

    @Override
    public int getLocalY() {
        return BlockPosition.ofGlobal(puzzleLoader$entity.getZone(), puzzleLoader$entity.getGlobalX(), puzzleLoader$entity.getGlobalY(), puzzleLoader$entity.getGlobalZ()).localY();
    }

    @Override
    public int getLocalZ() {
        return BlockPosition.ofGlobal(puzzleLoader$entity.getZone(), puzzleLoader$entity.getGlobalX(), puzzleLoader$entity.getGlobalY(), puzzleLoader$entity.getGlobalZ()).localZ();
    }


    @Override
    public BlockPosition getBlockPosition() {

        return new BlockPosition(pGetChunk(), pGetLocalX(), pGetLocalY(), pGetLocalZ());
    }


    @Override
    public Chunk gGetChunk() {
        return puzzleLoader$entity.getZone().getChunkAtBlock(
                getGlobalX(),
                getGlobalY(),
                getGlobalZ()
        );
    }

    @Override
    public IIdentifier pGetIdentifier() {
        return (IIdentifier) Identifier.of(puzzleLoader$entity.getBlockEntityId());
    }

    @Override
    public void pOnCreate(PBlockState IBlockState) {
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
    public void pOnSetBlockState(PBlockState IBlockState) {
        puzzleLoader$entity.onSetBlockState(IBlockState.as());
    }

    @Override
    public void pSetZone(IZone IZone) {
        puzzleLoader$entity.setZone(IZone.as());
    }

    @Override
    public PBlockState pGetBlockState() {
        return PBlockState.as(puzzleLoader$entity.getBlockState());
    }

    @Override
    public void pOnNeighborUpdate(IBlockUpdateEvent iBlockUpdateEvent) {
        // Implemented to prevent crash, can be overridden.
    }

    @Override
    public PSlotContainer pGetSlotContainer() {
        if (puzzleLoader$entity instanceof IBlockEntityWithContainer blockEntity){
            return PSlotContainer.as(blockEntity.getSlotContainer());
        } else {
            return null;
        }
    }

    @Override
    public void pUpdateNeighbors(BlockUpdateEvent event) {
        ((IBlockEntity)getBlockPosition()).pUpdateNeighbors(event);
    }

    @Override
    public void pUpdateNeighborInDirection(Direction direction, BlockUpdateEvent event) {
        ((IBlockEntity)getBlockPosition()).pUpdateNeighborInDirection(event, direction);
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
