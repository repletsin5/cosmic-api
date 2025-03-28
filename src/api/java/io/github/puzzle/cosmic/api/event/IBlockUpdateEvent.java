package io.github.puzzle.cosmic.api.event;

import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IBlockUpdateEvent {

    // Will get overridden
    /**
     * Gets the source position of the event.
     * @return a {@link IPuzzleBlockPosition}
     */
    @NotNull IPuzzleBlockPosition getSourcePosition();

    /**
     * Gets the attached object of the event.
     */
    @Nullable Object getAttachedObject();

    /**
     * Sets the source position of the event.
     * @param position the position to set.
     */
    void setSourcePosition(@NotNull IPuzzleBlockPosition position);

    /**
     * Set the attached object of the event.
     * @param object the object to set.
     */
    void setAttachedObject(@Nullable Object object);

}
