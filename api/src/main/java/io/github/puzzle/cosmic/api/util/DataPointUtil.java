package io.github.puzzle.cosmic.api.util;

import finalforeach.cosmicreach.items.ItemStack;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IItemStack;

public class DataPointUtil {

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(ItemStack itemStack) {
        return ((IItemStack) itemStack).pGetPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param manifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     */
    public static ItemStack setManifestOnStack(IDataPointManifest manifest, ItemStack itemStack) {
        ((IItemStack) itemStack).pSetPointManifest(manifest);
        return itemStack;
    }

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(IItemStack itemStack) {
        return itemStack.pGetPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param tagManifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     * @return a {@link IItemStack}.
     */
    public static IItemStack setManifestOnStack(IDataPointManifest tagManifest, IItemStack itemStack) {
        itemStack.pSetPointManifest(tagManifest);
        return itemStack;
    }

}
