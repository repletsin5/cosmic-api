package io.github.puzzle.cosmic.impl.mixin.item.container;

import finalforeach.cosmicreach.items.containers.SlotContainer;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.item.container.IPuzzleSlotContainer;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Internal
@Mixin(SlotContainer.class)
public class SlotContainerMixin implements IPuzzleSlotContainer {

    @Unique
    SlotContainer puzzleLoader$container = IPuzzleSlotContainer.as(this);

    @Override
    public IPuzzleSlotContainer pGetBackingContainer() {
        return IPuzzleSlotContainer.as(puzzleLoader$container.getBackingContainer());
    }

    @Override
    public boolean pAddItemStack(IPuzzleItemStack stack) {
        return puzzleLoader$container.addItemStack(stack.as());
    }

    @Override
    public boolean pAddItemStackWithSwapGroup(IPuzzleItemStack stack) {
        return puzzleLoader$container.addItemStackWithSwapGroup(stack.as());
    }

    @Override
    public boolean pAddOrMergeFrom(IPuzzleItemSlot slot) {
        return puzzleLoader$container.addOrMergeFrom(slot.as());
    }

    @Override
    public boolean pAddOrMergeFrom(IPuzzleItemSlot slot, Predicate<IPuzzleItemSlot> slotPredicate) {
        return puzzleLoader$container.addOrMergeFrom(slot.as(), (s) -> slotPredicate.test(IPuzzleItemSlot.as(s)));
    }

    @Override
    public void pDropAllItems(IPuzzleZone zone, float x, float y, float z) {
        puzzleLoader$container.dropAllItems(zone.as(), x, y, z);
    }

    @Override
    public void pForEachSlot(Consumer<IPuzzleItemSlot> consumer) {
        puzzleLoader$container.forEachSlot((c) -> consumer.accept(IPuzzleItemSlot.as(c)));
    }

    @Override
    public IPuzzleItemSlot pGetFirstEmptyItemSlot() {
        return IPuzzleItemSlot.as(puzzleLoader$container.getFirstEmptyItemSlot());
    }

    @Override
    public IPuzzleItemSlot pGetFirstFullItemSlot() {
        AtomicReference<IPuzzleItemSlot> fullItemSlot = new AtomicReference<>(null);
        pForEachSlot(iPuzzleItemSlot -> {
            if (!iPuzzleItemSlot.pIsEmpty()) {
                fullItemSlot.set(iPuzzleItemSlot);
            }
        });

        return fullItemSlot.get();
    }


    @Override
    public int pGetSlotCount() {
        return puzzleLoader$container.getNumSlots();
    }

    @Override
    public IPuzzleItemSlot pGetSlot(int i) {
        return IPuzzleItemSlot.as(puzzleLoader$container.getSlot(i));
    }

    @Override
    public boolean pIsEmpty() {
        return puzzleLoader$container.isEmpty();
    }
}
