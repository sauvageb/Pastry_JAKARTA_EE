package com.training.pastries.dao.base;

import java.util.List;
import java.util.Optional;

public interface BaseDao<ID, T> {

    Optional<T> findOne(ID id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(ID entityId);
}
