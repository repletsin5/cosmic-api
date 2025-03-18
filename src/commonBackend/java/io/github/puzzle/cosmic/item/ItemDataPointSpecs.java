package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import io.github.puzzle.cosmic.impl.data.point.TaggedDataPointSpec;
import io.github.puzzle.cosmic.impl.data.point.array.ListDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.EnumDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.IdentifierDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.IntegerDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.PairDataPoint;

import java.util.List;

public class ItemDataPointSpecs {

    public static final TaggedDataPointSpec<List<PairDataPoint<EnumDataPoint<IPuzzleItem.ItemModelType>, IdentifierDataPoint>>> TEXTURE_DICT;
    public static final TaggedDataPointSpec<Integer> TEXTURE_INDEX;
    public static final TaggedDataPointSpec<IPuzzleItem.ItemModelType> MODEL_TYPE;
    public static final TaggedDataPointSpec<Identifier> TEXTURE_LOCATION;

    static {
        TEXTURE_DICT = new TaggedDataPointSpec<>("texture_dict", ListDataPoint::new);
        TEXTURE_INDEX = new TaggedDataPointSpec<>("texture_index", IntegerDataPoint::new);
        TEXTURE_LOCATION = new TaggedDataPointSpec<>("texture_location", IdentifierDataPoint::new);
        MODEL_TYPE = new TaggedDataPointSpec<>("model_type", () -> new EnumDataPoint<>(IPuzzleItem.ItemModelType.class));

        IPuzzleItem.ItemModelType.class.getEnumConstants();
    }

}
