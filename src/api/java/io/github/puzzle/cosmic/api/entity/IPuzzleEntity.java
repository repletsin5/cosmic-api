package io.github.puzzle.cosmic.api.entity;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Entity")
public interface IPuzzleEntity extends ICRBinSerializable {

    Vector3 pGetPosition();
    Vector3 pGetViewDirection();

    IPuzzleEntityUniqueId pGetUniqueId();
    IPuzzleIdentifier pGetEntityId();

    boolean pIsDead();

    IDataPointManifest pGetPointManifest();
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);

}
