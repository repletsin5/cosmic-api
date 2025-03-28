package io.github.puzzle.cosmic.api.entity;

import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("EntityUniqueId")
public interface IPuzzleEntityUniqueId {

    /**
     * Gets the timestamp of the entity's unique ID.
     */
    long pGetTime();

    /**
     * Gets the random of the entity's unique ID.
     */
    int pGetRand();

    /**
     * Gets the number of the entity's unique ID.
     */
    int pGetNumber();

}
