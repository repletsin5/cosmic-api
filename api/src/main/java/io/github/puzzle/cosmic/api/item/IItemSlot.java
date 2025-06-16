package io.github.puzzle.cosmic.api.item;

import io.github.puzzle.cosmic.api.item.container.PuzzleSlotContainer;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("ItemSlot")
public interface IItemSlot {

    /**
     * Sets the itemSlot.
     * @param src the new itemSlot to set.
     */
    void pSet(IItemSlot src);

    /**
     * Sets the itemStack of the itemSlot.
     * @param itemStack the new itemStack to set.
     */
    void pSetItemStack(IItemStack itemStack);

    /**
     * Attempts to merge the itemStack from the given itemSlot into this itemStack.
     * @param itemSlot the itemSlot to merge form.
     * @return {@code true} if successful.
     */
    boolean pMergeFrom(IItemSlot itemSlot);

    /**
     * Attempts to merge the given itemStack with this ItemStack.
     * @param itemStack the itemSlot to merge.
     * @return {@code true} if successful.
     */
    boolean pMerge(IItemStack itemStack);

    /**
     * Attempts to merge this itemSlot into the given itemSlot.
     * @param itemSlot the itemSlot to mergeInto.
     * @return {@code true} if successful.
     */
    boolean pMergeInto(IItemSlot itemSlot);

    /**
     * Gets the slot ID.
     */
    int pGetSlotId();

    /**
     * Gets the slotContainer this itemSlot is in.
     * @return a {@link PuzzleSlotContainer}
     */
    PuzzleSlotContainer pGetContainer();

    /**
     * Sets the slotContainer this itemSlot is in.
     * @param slotContainer the slotContainer to set.
     */
    void pSetContainer(PuzzleSlotContainer slotContainer);

    /**
     * Adds this amount to the itemStack in this itemSlot.
     * @param amount amount to add.
     */
    void pAddAmount(int amount);

    /**
     * Triggered when this itemSlot is update.
     */
    void pOnItemSlotUpdate();

    /**
     * Sets whether this itemSlot is output only.
     * @param readOnly {@code true} to make this slot output only.
     */
    void pSetOutputOnly(boolean readOnly);

    /**
     * Checks if the itemSlot is output only.
     */
    boolean pIsOutputOnly();

    /**
     * Checks if the itemSlot is empty.
     */
    boolean pIsEmpty();

    /**
     * Checks if the itemSlot has room.
     * @param item the item to check if it has room for.
     * @return {@code true} if it has room.
     */
    boolean pHasRoomFor(IItem item);

    /**
     * Checks if the itemSlot has room.
     * @param itemStack the itemStack to check if it has room for.
     * @return {@code true} if it has room.
     */
    boolean pHasRoomFor(IItemStack itemStack);

    /**
     * Checks if the itemSlot has room.
     * @param item the item to check if it has room for.
     * @param amount the amount to check if it has room for.
     * @return {@code true} if it has room.
     */
    boolean pHasRoomFor(IItem item, int amount);

    /**
     * Adds a itemStack.
     * @param item item to be added.
     * @param amount amount of the item to be added.
     * @return {@code true} if successful.
     */
    boolean pAddItemStack(IItem item, int amount);

    /**
     * Adds a itemStack.
     * @param itemStack item to be added.
     * @return {@code true} if successful.
     */
    boolean pAddItemStack(IItemStack itemStack);

}
