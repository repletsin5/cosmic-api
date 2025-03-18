package io.github.puzzle.cosmic.api.data.point;

import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IDataPointManifest extends ICRBinSerializable {

    <T> ITaggedDataPoint<T> put(ITaggedDataPoint<T> point);
    <T> IDataPoint<T> put(String name, IDataPoint<T> point);
    <T> IDataPoint<T> get(String name, Class<T> type);
    IDataPoint<?> get(String name);
    <T> ITaggedDataPoint<T> get(ITaggedDataPointSpec<T> spec);

    boolean has(String name);
    boolean has(String name, Class<?> type);
    boolean has(ITaggedDataPointSpec<?> spec);

    void remove(String name);

}
