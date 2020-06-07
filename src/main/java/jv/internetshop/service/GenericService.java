package jv.internetshop.service;

import java.util.List;

public interface GenericService<T, V> {
    T create(T element);

    T getByUserId(V id);

    List<T> getAll();

    T update(T element);

    boolean delete(V id);
}
