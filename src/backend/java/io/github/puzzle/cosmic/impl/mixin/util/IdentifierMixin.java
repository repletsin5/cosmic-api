package io.github.puzzle.cosmic.impl.mixin.util;

import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Identifier.class)
public class IdentifierMixin implements IIdentifier {

    @Unique
    private final transient Identifier puzzleLoader$id = IIdentifier.as(this);

    @Override
    public String pGetNamespace() {
        return puzzleLoader$id.getNamespace();
    }

    @Override
    public String pGetName() {
        return puzzleLoader$id.getName();
    }
}
