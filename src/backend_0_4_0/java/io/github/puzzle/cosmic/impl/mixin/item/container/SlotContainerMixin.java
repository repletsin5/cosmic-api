package io.github.puzzle.cosmic.impl.mixin.item.container;

import finalforeach.cosmicreach.items.containers.SlotContainer;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.container.PSlotContainer;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Internal
@Mixin(SlotContainer.class)
public abstract class SlotContainerMixin implements PSlotContainer {

    @Unique
    SlotContainer puzzleLoader$container = PSlotContainer.as(this);

    @Override
    public PSlotContainer pGetBackingContainer() {
        return PSlotContainer.as(puzzleLoader$container.getBackingContainer());
    }

    @Override
    public boolean pAddItemStack(IItemStack stack) {
        return puzzleLoader$container.addItemStack(stack.as());
    }

    @Override
    public boolean pAddItemStackWithSwapGroup(IItemStack stack) {
        return puzzleLoader$container.addItemStackWithSwapGroup(stack.as());
    }

    @Override
    public boolean pAddOrMergeFrom(IItemSlot slot) {
        return puzzleLoader$container.addOrMergeFrom(slot.as());
    }

    @Override
    public boolean pAddOrMergeFrom(IItemSlot slot, Predicate<IItemSlot> slotPredicate) {
        return puzzleLoader$container.addOrMergeFrom(slot.as(), (s) -> slotPredicate.test(IItemSlot.as(s)));
    }

    @Override
    public void pDropAllItems(IZone zone, float x, float y, float z) {
        puzzleLoader$container.dropAllItems(zone.as(), x, y, z);
    }

    @Override
    public void pForEachSlot(Consumer<IItemSlot> consumer) {
        puzzleLoader$container.forEachSlot((c) -> consumer.accept(IItemSlot.as(c)));
    }

    @Override
    public IItemSlot pGetFirstEmptyItemSlot() {
        return IItemSlot.as(puzzleLoader$container.getFirstEmptyItemSlot());
    }

    @Override
    public IItemSlot pGetFirstFullItemSlot() {
        AtomicReference<IItemSlot> fullItemSlot = new AtomicReference<>(null);
        pForEachSlot(IItemSlot -> {
            if (!IItemSlot.pIsEmpty()) {
                fullItemSlot.set(IItemSlot);
            }
        });

        return fullItemSlot.get();
    }


    @Override
    public int pGetSlotCount() {
        return puzzleLoader$container.getNumSlots();
    }

    @Override
    public IItemSlot pGetSlot(int i) {
        return IItemSlot.as(puzzleLoader$container.getSlot(i));
    }

    @Override
    public List<IItemSlot> pGetSlots() {
        List<IItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlot -> itemSlotList.add(IItemSlot.as(itemSlot)));
        return itemSlotList;
    }

    @Override
    public List<IItemSlot> pGetInputSlots() {
        List<IItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlot -> {
            if (!itemSlot.isOutputOnly()) itemSlotList.add(IItemSlot.as(itemSlot));
        });
        return itemSlotList;
    }

    @Override
    public List<IItemSlot> pGetOutputSlots() {
        List<IItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlot -> {
            if (itemSlot.isOutputOnly()) itemSlotList.add(IItemSlot.as(itemSlot));
        });
        return itemSlotList;
    }

    @Override
    public boolean pIsEmpty() {
        return puzzleLoader$container.isEmpty();
    }
}
