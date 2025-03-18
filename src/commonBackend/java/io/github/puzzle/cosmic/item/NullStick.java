package io.github.puzzle.cosmic.item;

import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.data.DataTag;
import com.github.puzzle.game.items.data.DataTagManifest;
import com.github.puzzle.game.items.data.attributes.IntDataAttribute;
import com.github.puzzle.game.util.BlockSelectionUtil;
import com.github.puzzle.game.util.DataTagUtil;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.item.ITickingPuzzleItem;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.APISide;

import static com.github.puzzle.core.Constants.MOD_ID;

public class NullStick extends AbstractCosmicItem implements ITickingPuzzleItem {

    int texture_count = 0;

    public NullStick() {
        super(Identifier.of("puzzle-loader", "null_stick"));

        addTexture(
            ItemModelType.ITEM_MODEL_3D,
            Identifier.of(MOD_ID, "null_stick.png"),
            Identifier.of("base", "axe_stone.png"),
            Identifier.of("base", "pickaxe_stone.png"),
            Identifier.of("base", "shovel_stone.png"),
            Identifier.of("base", "medkit.png"),
            Identifier.of(MOD_ID, "block_wrench.png"),
            Identifier.of(MOD_ID, "checker_board.png"),
            Identifier.of(MOD_ID, "checker_board1.png"),
            Identifier.of(MOD_ID, "checker_board2.png")
        );

        addTexture(
                ItemModelType.ITEM_MODEL_2D,
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of("base", "axe_stone.png"),
                Identifier.of("base", "pickaxe_stone.png"),
                Identifier.of("base", "shovel_stone.png"),
                Identifier.of("base", "medkit.png"),
                Identifier.of(MOD_ID, "block_wrench.png"),
                Identifier.of(MOD_ID, "checker_board.png"),
                Identifier.of(MOD_ID, "checker_board1.png"),
                Identifier.of(MOD_ID, "checker_board2.png")
        );

        texture_count = pGetPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT).getValue().size() - 1;
    }

    @Override
    public boolean pUse(APISide side, IPuzzleItemSlot slot, IPuzzlePlayer player, IPuzzleBlockPosition targetPlaceBlockPos, IPuzzleBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.REMOTE_CLIENT || side == APISide.SINGLE_PLAYER_CLIENT) && !isLeftClick) {
            BlockState state = BlockSelectionUtil.getBlockLookingAt();
            BlockPosition position = BlockSelectionUtil.getBlockPositionLookingAt();
            if (state == null) return false;
            if (position == null) return false;

//        DataTagManifest manifest = DataTagUtil.getManifestFromStack(slot.itemStack);
//        if (!manifest.hasTag("currentEntry")) manifest.addTag(new DataTag<>("currentEntry", new IntDataAttribute(0)));
//
//        Integer currentEntry = manifest.getTag("currentEntry").getTagAsType(Integer.class).getValue();
//        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
//        manifest.addTag(new DataTag<>("currentEntry", new IntDataAttribute(currentEntry)));
//        DataTagUtil.setManifestOnStack(manifest, slot.itemStack);

//        OrderedMap<String, BlockState> blockStates = state.getBlock().blockStates;
//        SlotContainer c = new SlotContainer(blockStates.size);
//        UI.addOpenContainer(c);
//        for (int i = 0; i < c.numberOfSlots; i++) {
//            c.addItemStack(new ItemStack(this));
//        }

//        BlockUtil.setBlockAt(position.getZone(), ((ItemBlock) state.getItem().getNextSwapGroupItem()).getBlockState(), position);
        }

        return false;
    }

    @Override
    public boolean pIsTool() {
        return true;
    }

    @Override
    public int getDefaultStackLimit() {
        return 1;
    }

    @Override
    public void tickEntity(IPuzzleZone zone, double deltaTime, IPuzzleEntity entity, IPuzzleItemStack stack) {
        int currentEntry = getCurrentEntry(stack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentEntry(stack, currentEntry);
    }

    @Override
    public void tickStack(float fixedUpdateTimeStep, IPuzzleItemStack stack, boolean isBeingHeld) {
        int currentEntry = getCurrentEntry(stack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentEntry(stack, currentEntry);
    }

    @Override
    public String getName() {
        return "Debug | Null Stick";
    }
}
