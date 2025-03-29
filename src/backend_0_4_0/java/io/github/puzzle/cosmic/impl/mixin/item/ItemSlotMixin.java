package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemSlot;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.container.ISlotContainer;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(ItemSlot.class)
public class ItemSlotMixin implements IItemSlot {

    @Unique
    private final transient ItemSlot puzzleLoader$slot = IItemSlot.as(this);

    @Override
    public void pSet(IItemSlot IItemSlot) {
        puzzleLoader$slot.set(IItemSlot.as());
    }

    @Override
    public void pSetItemStack(IItemStack IItemStack) {
        puzzleLoader$slot.setItemStack(IItemStack.as());
    }

    @Override
    public boolean pMergeFrom(IItemSlot IItemSlot) {
        return puzzleLoader$slot.mergeFrom(IItemSlot.as());
    }

    @Override
    public boolean pMerge(IItemStack IItemStack) {
        return puzzleLoader$slot.merge(IItemStack.as());
    }

    @Override
    public boolean pMergeInto(IItemSlot IItemSlot) {
        return puzzleLoader$slot.mergeInto(IItemSlot.as());
    }

    @Override
    public int pGetSlotId() {
        return puzzleLoader$slot.getSlotId();
    }

    @Override
    public ISlotContainer pGetContainer() {
        return ISlotContainer.as(puzzleLoader$slot.getContainer());
    }

    @Override
    public void pSetContainer(ISlotContainer ISlotContainer) {
        puzzleLoader$slot.setContainer(ISlotContainer.as());
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
    public boolean pHasRoomFor(IItem IItem) {
        return puzzleLoader$slot.hasRoomFor(IItem.as());
    }

    @Override
    public boolean pHasRoomFor(IItemStack IItemStack) {
        return puzzleLoader$slot.hasRoomFor(IItemStack.as());
    }

    @Override
    public boolean pHasRoomFor(IItem IItem, int i) {
        return puzzleLoader$slot.hasRoomFor(IItem.as());
    }

    @Override
    public boolean pAddItemStack(IItem IItem, int i) {
        return puzzleLoader$slot.addItemStack(IItem.as(), i);
    }

    @Override
    public boolean pAddItemStack(IItemStack IItemStack) {
        return puzzleLoader$slot.addItemStack(IItemStack.as());
    }
}
