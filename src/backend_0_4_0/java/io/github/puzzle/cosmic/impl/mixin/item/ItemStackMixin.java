package io.github.puzzle.cosmic.impl.mixin.item;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.IBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IItemStack {

    @Unique
    private final transient ItemStack puzzleLoader$stack = IItemStack.as(this);

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    @Override
    public IItemStack pCopy() {
        return IItemStack.as(puzzleLoader$stack.copy());
    }

    @Override
    public IItem pGetItem() {
        return IItem.as(puzzleLoader$stack.getItem());
    }

    @Override
    public void pSetItem(IItem IItem) {
        puzzleLoader$stack.setItem(IItem.as());
    }

    @Override
    public void pCycleSwapGroupItem() {
        puzzleLoader$stack.cycleSwapGroupItem();
    }

    @Override
    public IEntity pSpawnItemEntityAt(IZone IZone, Vector3 vector3) {
        return IEntity.as(puzzleLoader$stack.spawnItemEntityAt(IZone.as(), vector3));
    }

    @Override
    public void pSpawnItemEntityAt(IBlockPosition IBlockPosition) {
        puzzleLoader$stack.spawnItemEntityAt(IBlockPosition.as());
    }

    @Override
    public boolean pUseItem(IItemSlot IItemSlot, IPlayer IPlayer, IBlockPosition IBlockPosition) {
        return puzzleLoader$stack.useItem(IItemSlot.as(), IPlayer.as(), IBlockPosition.as());
    }

    @Override
    public int pGetDurability() {
        return puzzleLoader$stack.getDurability();
    }

    @Override
    public int pGetMaxDurability() {
        return puzzleLoader$stack.getMaxDurability();
    }

    @Override
    public boolean pHasDurability() {
        return puzzleLoader$stack.hasDurability();
    }

    @Override
    public void pDamage(int i) {
        puzzleLoader$stack.damage(i);
    }

    @Override
    public boolean pIsBroken() {
        return puzzleLoader$stack.isBroken();
    }

    @Override
    public boolean pCanTargetBlockForBreaking(IBlockState IBlockState) {
        return puzzleLoader$stack.canTargetBlockForBreaking(IBlockState.as());
    }

    @Override
    public boolean pIsEffectiveBreaking(IBlockState IBlockState) {
        return puzzleLoader$stack.isEffectiveBreaking(IBlockState.as());
    }

    @Override
    public float pGetEffectiveBreakingSpeed() {
        return puzzleLoader$stack.getEffectiveBreakingSpeed();
    }

    @Override
    public IItemStack pSetAmount(int i) {
        return IItemStack.as(puzzleLoader$stack.setAmount(i));
    }

    @Override
    public String pGetName() {
        return puzzleLoader$stack.getName();
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
