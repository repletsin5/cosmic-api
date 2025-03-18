package io.github.puzzle.cosmic.api.item;

import io.github.puzzle.cosmic.api.item.container.IPuzzleSlotContainer;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("ItemSlot")
public interface IPuzzleItemSlot {

    void pSet(IPuzzleItemSlot src);
    void pSetItemStack(IPuzzleItemStack stack);

    boolean pMergeFrom(IPuzzleItemSlot slot);
    boolean pMerge(IPuzzleItemStack stack);
    boolean pMergeInto(IPuzzleItemSlot slot);

    int pGetSlotId();

    IPuzzleSlotContainer pGetContainer();
    void pSetContainer(IPuzzleSlotContainer container);

    void pAddAmount(int amount);
    void pOnItemSlotUpdate();

    void pSetOutputOnly(boolean readOnly);
    boolean pIsOutputOnly();

    boolean pIsEmpty();

    boolean pHasRoomFor(IPuzzleItem item);
    boolean pHasRoomFor(IPuzzleItemStack stack);
    boolean pHasRoomFor(IPuzzleItem item, int amount);

    boolean pAddItemStack(IPuzzleItem item, int amount);
    boolean pAddItemStack(IPuzzleItemStack stack);

}
