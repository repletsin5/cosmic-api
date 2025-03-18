package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.item.container.IPuzzleSlotContainer;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(ItemSlot.class)
public class ItemSlotMixin implements IPuzzleItemSlot {

    @Unique
    private final transient ItemSlot puzzleLoader$slot = IPuzzleItemSlot.as(this);

    @Override
    public void pSet(IPuzzleItemSlot iPuzzleItemSlot) {
        puzzleLoader$slot.set(iPuzzleItemSlot.as());
    }

    @Override
    public void pSetItemStack(IPuzzleItemStack iPuzzleItemStack) {
        puzzleLoader$slot.setItemStack(iPuzzleItemStack.as());
    }

    @Override
    public boolean pMergeFrom(IPuzzleItemSlot iPuzzleItemSlot) {
        return puzzleLoader$slot.mergeFrom(iPuzzleItemSlot.as());
    }

    @Override
    public boolean pMerge(IPuzzleItemStack iPuzzleItemStack) {
        return puzzleLoader$slot.merge(iPuzzleItemStack.as());
    }

    @Override
    public boolean pMergeInto(IPuzzleItemSlot iPuzzleItemSlot) {
        return puzzleLoader$slot.mergeInto(iPuzzleItemSlot.as());
    }

    @Override
    public int pGetSlotId() {
        return puzzleLoader$slot.getSlotId();
    }

    @Override
    public IPuzzleSlotContainer pGetContainer() {
        return IPuzzleSlotContainer.as(puzzleLoader$slot.getContainer());
    }

    @Override
    public void pSetContainer(IPuzzleSlotContainer iPuzzleSlotContainer) {
        puzzleLoader$slot.setContainer(iPuzzleSlotContainer.as());
    }

    @Override
    public void pAddAmount(int i) {
        puzzleLoader$slot.addAmount(i);
    }

    @Override
    public void pOnItemSlotUpdate() {
        puzzleLoader$slot.onItemSlotUpdate();
    }

    @Override
    public void pSetOutputOnly(boolean b) {
        puzzleLoader$slot.setOutputOnly(b);
    }

    @Override
    public boolean pIsOutputOnly() {
        return puzzleLoader$slot.isOutputOnly();
    }

    @Override
    public boolean pIsEmpty() {
        return puzzleLoader$slot.isEmpty();
    }

    @Override
    public boolean pHasRoomFor(IPuzzleItem iPuzzleItem) {
        return puzzleLoader$slot.hasRoomFor(iPuzzleItem.as());
    }

    @Override
    public boolean pHasRoomFor(IPuzzleItemStack iPuzzleItemStack) {
        return puzzleLoader$slot.hasRoomFor(iPuzzleItemStack.as());
    }

    @Override
    public boolean pHasRoomFor(IPuzzleItem iPuzzleItem, int i) {
        return puzzleLoader$slot.hasRoomFor(iPuzzleItem.as());
    }

    @Override
    public boolean pAddItemStack(IPuzzleItem iPuzzleItem, int i) {
        return puzzleLoader$slot.addItemStack(iPuzzleItem.as(), i);
    }

    @Override
    public boolean pAddItemStack(IPuzzleItemStack iPuzzleItemStack) {
        return puzzleLoader$slot.addItemStack(iPuzzleItemStack.as());
    }
}
