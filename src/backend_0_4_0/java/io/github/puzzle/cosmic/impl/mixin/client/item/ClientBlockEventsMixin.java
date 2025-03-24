package io.github.puzzle.cosmic.impl.mixin.client.item;

import finalforeach.cosmicreach.ClientBlockEvents;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBlockEvents.class)
public class ClientBlockEventsMixin {

    @Inject(remap = false, method = "breakBlock", at = @At("HEAD"), cancellable = true)
    public void breakBlock(Zone zone, BlockPosition blockPos, double timeSinceLastInteract, CallbackInfo ci) {
        ItemSlot slot = UI.hotbar.getSelectedSlot();
        if (slot != null && blockPos != null && slot.getItemStack() != null && slot.getItemStack().getItem() instanceof IPuzzleItem modItem) {
            if (!modItem.pCanBreakBlockWith((IPuzzleBlockState) blockPos.getBlockState())){
                ci.cancel();
            }
        }
    }

    @Inject(remap = false, method = "interactWithBlock", at = @At("HEAD"), cancellable = true)
    public void interactWithBlock(Player player, Zone zone, BlockPosition blockPos, ItemStack heldItemStack, CallbackInfo ci) {
        if (blockPos != null && heldItemStack != null && heldItemStack.getItem() instanceof IPuzzleItem modItem) {
            if (!modItem.pCanInteractWithBlock((IPuzzleBlockState) blockPos.getBlockState())){
                ci.cancel();
            }
        }
    }

    @Inject(remap = false, method = "interactWithBlockIfBlockEntity", at = @At("HEAD"), cancellable = true)
    public void interactWithBlockIfBlockEntity(Player player, Zone zone, BlockPosition blockPos, CallbackInfoReturnable<Boolean> cir) {
        ItemSlot slot = UI.hotbar.getSelectedSlot();
        if (slot != null && blockPos != null && slot.getItemStack() != null && slot.getItemStack().getItem() instanceof IPuzzleItem modItem) {
            if (blockPos.getBlockEntity() != null){
                if (!modItem.pCanInteractWithBlockEntity((IPuzzleBlockState) blockPos.getBlockEntity())){
                    cir.cancel();
                }
            }
        }
    }
}
