package io.github.puzzle.cosmic.api.util;

import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Identifier")
public interface IPuzzleIdentifier {

    /**
     * Gets the namespace of the Identifier.
     */
    String pGetNamespace();

    /**
     * Gets the name of the Identifier.
     */
    String pGetName();

    default String asString() {
        return pGetNamespace() + ":" + pGetName();
    }
}
