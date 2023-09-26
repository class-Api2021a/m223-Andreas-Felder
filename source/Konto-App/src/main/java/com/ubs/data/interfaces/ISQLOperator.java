package com.ubs.data.interfaces;

import java.util.List;

public interface ISQLOperator<T> {
    int create(T t);
    T read(T t);
    int update(T t, T old_t);
    int delete(T t);
    List<T> getAll();

}
