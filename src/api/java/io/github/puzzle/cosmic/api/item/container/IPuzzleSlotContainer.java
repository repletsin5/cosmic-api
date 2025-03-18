package io.github.puzzle.cosmic.api.item.container;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.Note;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */

@ApiGen("SlotContainer")
@Note("This interface only applies to SlotContainer and not ISlotContainer internally.")
public interface IPuzzleSlotContainer {

    IPuzzleSlotContainer pGetBackingContainer();

    boolean pAddItemStack(IPuzzleItemStack stack);
    boolean pAddItemStackWithSwapGroup(IPuzzleItemStack stack);

    boolean pAddOrMergeFrom(IPuzzleItemSlot slot);
    boolean pAddOrMergeFrom(IPuzzleItemSlot slot, Predicate<IPuzzleItemSlot> slotPredicate);

    void pDropAllItems(IPuzzleZone zone, float x, float y, float z);
    default void pDropAllItems(IPuzzleZone zone, Vector3 position) {
        pDropAllItems(zone, position.x, position.y, position.z);
    }

    void pForEachSlot(Consumer<IPuzzleItemSlot> slotConsumer);

    IPuzzleItemSlot pGetFirstEmptyItemSlot();
    IPuzzleItemSlot pGetFirstFullItemSlot();
    int pGetSlotCount();

    IPuzzleItemSlot pGetSlot(int index);
    IPuzzleItemSlot pGetSlots(int index);
    boolean pIsEmpty();

    default void pClear() {
        pForEachSlot((p) -> {
            p.pSetItemStack(null);
        });
    }

}
