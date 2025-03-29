package io.github.puzzle.cosmic.impl.mixin.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.core.loader.util.Reflection;
import com.github.puzzle.game.engine.items.model.ItemModelWrapper;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.rendering.entities.instances.ItemEntityModelInstance;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.IEntityUniqueId;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(Entity.class)
public class EntityMixin implements IEntity {

    @Unique
    private final transient Entity puzzleLoader$entity = IEntity.as(this);

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    @Override
    public Vector3 pGetPosition() {
        return puzzleLoader$entity.position;
    }

    @Override
    public Vector3 pGetViewDirection() {
        return puzzleLoader$entity.viewDirection;
    }

    @Override
    public IEntityUniqueId pGetUniqueId() {
        return IEntityUniqueId.as(puzzleLoader$entity.uniqueId);
    }

    @Override
    public IIdentifier pGetEntityId() {
        return IIdentifier.as(Identifier.of(puzzleLoader$entity.entityTypeId));
    }

    @Override
    public boolean pIsDead() {
        return puzzleLoader$entity.isDead();
    }

    @Shadow
    @Final
    protected static Matrix4 tmpModelMatrix;

    @Inject(method = "renderModelAfterMatrixSet", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/rendering/entities/IEntityModelInstance;render(Lfinalforeach/cosmicreach/entities/Entity;Lcom/badlogic/gdx/graphics/Camera;Lcom/badlogic/gdx/math/Matrix4;Z)V", shift = At.Shift.BEFORE), cancellable = true)
    private void render(Camera worldCamera, boolean shouldRender, CallbackInfo ci) {
        if (puzzleLoader$entity.modelInstance instanceof ItemEntityModelInstance) {
            if (Reflection.getFieldContents(puzzleLoader$entity.modelInstance, "model") instanceof ItemModelWrapper m) {
                ItemStack stack = null;
                try {
                    stack = Reflection.getFieldContents(this, "itemStack");
                } catch (Exception ignore) {}
                m.renderAsEntity(puzzleLoader$entity.position, stack, worldCamera, tmpModelMatrix);
                ci.cancel();
            }
        }
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
