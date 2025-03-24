package io.github.puzzle.cosmic.api.util;

import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.tmp.ItemStackTmpClass;

public class DataPointUtil {

    public static IDataPointManifest getManifestFromStack(ItemStackTmpClass stack) {
        return ((IPuzzleItemStack) stack).pGetPointManifest();
    }

    public static ItemStackTmpClass setManifestOnStack(IDataPointManifest manifest, ItemStackTmpClass stack) {
        ((IPuzzleItemStack) stack).pSetPointManifest(manifest);
        return stack;
    }

    public static IDataPointManifest getManifestFromStack(IPuzzleItemStack stack) {
        return stack.pGetPointManifest();
    }

    public static IPuzzleItemStack setManifestOnStack(IDataPointManifest tagManifest, IPuzzleItemStack stack) {
        stack.pSetPointManifest(tagManifest);
        return stack;
    }

}
