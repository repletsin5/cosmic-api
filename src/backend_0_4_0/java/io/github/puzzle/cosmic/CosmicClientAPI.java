package io.github.puzzle.cosmic;

import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientPostModInitializer;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.ITickingPuzzleItem;

public class CosmicClientAPI implements ClientPostModInitializer {

    @Override
    public void onPostInit() {
        GameSingletons.updateObservers.add(fixedUpdateTimeStep -> {
            if (InGame.getLocalPlayer() != null && UI.hotbar.getContainer() != null) {
                for (int i = 0; i < UI.hotbar.getContainer().getNumSlots(); i++) {
                    ItemSlot slot = UI.hotbar.getContainer().getSlot(i);

                    if (slot != null) {
                        if (slot.getItemStack() != null && slot.getItemStack().getItem() instanceof ITickingPuzzleItem item) {
                            item.tickStack(fixedUpdateTimeStep, (IItemStack) slot.getItemStack(), UI.hotbar.getSelectedSlot() == slot);
                        }
                    }
                }

                for (int ic = 0; ic < UI.openContainers.size; ic++) {
                    for (int i = 0; i < UI.openContainers.get(ic).getNumSlots(); i++) {
                        ItemSlot slot = UI.openContainers.get(ic).getSlot(i);

                        if (slot != null && slot.getItemStack() != null) {
                            if (slot.getItemStack().getItem() instanceof ITickingPuzzleItem item) {
                                item.tickStack(fixedUpdateTimeStep, (IItemStack) slot.getItemStack(), false);
                            }
                        }
                    }
                }
            }
        });
    }
}
