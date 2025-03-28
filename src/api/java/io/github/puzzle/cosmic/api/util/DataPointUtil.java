package io.github.puzzle.cosmic.api.util;

import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.tmp.ItemStackTmpClass;

public class DataPointUtil {

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(ItemStackTmpClass itemStack) {
        return ((IPuzzleItemStack) itemStack).pGetPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param manifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     */
    public static ItemStackTmpClass setManifestOnStack(IDataPointManifest manifest, ItemStackTmpClass itemStack) {
        ((IPuzzleItemStack) itemStack).pSetPointManifest(manifest);
        return itemStack;
    }

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(IPuzzleItemStack itemStack) {
        return itemStack.pGetPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param tagManifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     * @return a {@link IPuzzleItemStack}.
     */
    public static IPuzzleItemStack setManifestOnStack(IDataPointManifest tagManifest, IPuzzleItemStack itemStack) {
        itemStack.pSetPointManifest(tagManifest);
        return itemStack;
    }

}
