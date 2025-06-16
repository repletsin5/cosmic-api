package io.github.puzzle.cosmic.api.event;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockEntity;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IBlockEntityUpdateEvent extends IBlockUpdateEvent {

    /**
     * Gets the sourceEntity of the event.
     * @return a {@link BlockEntity}
     */
    default BlockEntity getSourceEntity() {
        return getSourcePosition().getBlockEntity();
    }

}
