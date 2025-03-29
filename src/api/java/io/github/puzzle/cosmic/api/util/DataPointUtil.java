package io.github.puzzle.cosmic.api.util;

import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.tmp.ItemStackTmpClass;

public class DataPointUtil {

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(ItemStackTmpClass itemStack) {
        return ((IItemStack) itemStack).pGetPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param manifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     */
    public static ItemStackTmpClass setManifestOnStack(IDataPointManifest manifest, ItemStackTmpClass itemStack) {
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
