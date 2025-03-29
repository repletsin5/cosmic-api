package io.github.puzzle.cosmic.api.item.container;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.Note;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */

@ApiGen("SlotContainer")
@Note("This interface only applies to SlotContainer and not ISlotContainer internally.")
public interface PSlotContainer {

    /**
     * Gets the slotContainer use if you have a ISlotContainer.
     * @return a {@link PSlotContainer}
     */
    PSlotContainer pGetBackingContainer();

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same item.
     * @param itemStack the itemStack to add.
     */
    boolean pAddItemStack(IItemStack itemStack);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup.
     * @param itemStack the itemStack to add.
     */
    boolean pAddItemStackWithSwapGroup(IItemStack itemStack);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup.
     * @param itemSlot the itemSlot to add or merge from.
     */
    boolean pAddOrMergeFrom(IItemSlot itemSlot);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup, and it matches the slotPredicate.
     * @param itemSlot the itemSlot to add or merge from.
     * @param itemSlotPredicate the slotPredicate to test.
     */
    boolean pAddOrMergeFrom(IItemSlot itemSlot, Predicate<IItemSlot> itemSlotPredicate);

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param x the X pos where the items will be dropped.
     * @param y the Y pos where the items will be dropped.
     * @param z the Z pos where the items will be dropped.
     */
    void pDropAllItems(IZone zone, float x, float y, float z);

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param position the position where the items will be dropped.
     */
    default void pDropAllItems(IZone zone, Vector3 position) {
        pDropAllItems(zone, position.x, position.y, position.z);
    }

    /**
     * Iterates over each item slot.
     * @param itemSlotConsumer action to be performed.
     */
    void pForEachSlot(Consumer<IItemSlot> itemSlotConsumer);

    /**
     * Gets the first empty itemSlot.
     * @return a {@link IItemSlot}
     */
    IItemSlot pGetFirstEmptyItemSlot();

    /**
     * Gets the first full itemSlot.
     * @return a {@link IItemSlot}
     */
    IItemSlot pGetFirstFullItemSlot();

    /**
     * Gets the number of itemSlot in the slotContainer.
     */
    int pGetSlotCount();

    /**
     * Gets the itemSlot at the specified index.
     * @param index the index of the itemSlot.
     * @return a {@link IItemSlot}
     */
    IItemSlot pGetSlot(int index);

    /**
     * Gets all the itemSlots in the slotContainer as a list.
     * @return a list of {@link IItemSlot}
     */
    List<IItemSlot> pGetSlots();

    /**
     * Gets all the input itemSlots in the slotContainer as a list.
     * @return a list of {@link IItemSlot}
     */
    List<IItemSlot>  pGetInputSlots();

    /**
     * Gets all the output itemSlots in the slotContainer as a list.
     * @return a list of {@link IItemSlot}
     */
    List<IItemSlot> pGetOutputSlots();

    /**
     * Checks if the slotContainer is empty.
     */
    boolean pIsEmpty();

    /**
     * Clears all itemSlots by removing their itemStack.
     */
    default void pClear() {
        pForEachSlot((p) -> {
            p.pSetItemStack(null);
        });
    }

}
