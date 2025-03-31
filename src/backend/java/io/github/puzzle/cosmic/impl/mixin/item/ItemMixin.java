package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;

@Internal
@Mixin(Item.class)
public interface ItemMixin extends IItem {

    @Override
    default IIdentifier pGetIdentifier() {
        Item item = IItem.as(this);

        return IIdentifier.as(Identifier.of(item.getID()));
    }
}
