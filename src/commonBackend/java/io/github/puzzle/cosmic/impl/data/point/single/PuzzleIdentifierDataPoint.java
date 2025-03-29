package io.github.puzzle.cosmic.impl.data.point.single;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class PuzzleIdentifierDataPoint extends AbstractDataPoint<IIdentifier> {

    public PuzzleIdentifierDataPoint() {
        super(IIdentifier.class);
    }

    public PuzzleIdentifierDataPoint(IIdentifier value) {
        super(IIdentifier.class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        setValue(IIdentifier.as(Identifier.of(deserializer.readString("v"))));
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeString("v", value.asString());
    }
}
