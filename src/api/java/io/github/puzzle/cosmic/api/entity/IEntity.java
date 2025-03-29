package io.github.puzzle.cosmic.api.entity;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Entity")
public interface IEntity extends ICRBinSerializable {

    /**
     * Gets the position of the entity.
     */
    Vector3 pGetPosition();

    /**
     * Gets the view direction of the entity.
     */
    Vector3 pGetViewDirection();

    /**
     * Gets the entities uniqueId.
     * @return a {@link IEntityUniqueId}
     */
    IEntityUniqueId pGetUniqueId();

    /**
     * Gets the entities' ID.
     * @return a {@link IIdentifier}
     */
    IIdentifier pGetEntityId();

    /**
     * Checks if the entity is dead.
     */
    boolean pIsDead();

    /**
     * Gets the point manifest of the entity.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest pGetPointManifest();

    /**
     * Sets the point manifest of the entity.
     * @param manifest The new point manifest to be set.
     */
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);

}
