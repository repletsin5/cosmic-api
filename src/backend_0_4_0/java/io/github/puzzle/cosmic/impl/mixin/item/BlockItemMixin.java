package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemBlock;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemBlock.class)
public class BlockItemMixin implements IPuzzleItem {

    @Override
    public boolean pIsModded() {
        return false;
    }

    @Override
    public void pSetModded(boolean m) {
        throw new RuntimeException("Nuh uh");
    }
}
