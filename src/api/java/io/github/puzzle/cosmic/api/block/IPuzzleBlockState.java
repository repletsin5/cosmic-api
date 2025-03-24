package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import io.github.puzzle.cosmic.api.tmp.GameTagListTmpClass;
import io.github.puzzle.cosmic.api.tmp.GameTagTmpClass;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.Collection;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockState")
public interface IPuzzleBlockState extends IBlockState {

    IPuzzleBlock pGetBlock();
    IPuzzleItem pGetAsItem();

    void pSetTags(GameTagListTmpClass tags);
    void pAddTags(GameTagTmpClass... tags);
    void pAddTags(Collection<GameTagTmpClass> tags);
    void pRemoveTags(GameTagTmpClass... tags);
    void pRemoveTags(Collection<GameTagTmpClass>... tags);

    IPuzzleIdentifier pGetBlockID();
    String pGetSaveKey();

}
