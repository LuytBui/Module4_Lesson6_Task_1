package com.codegym.dao;

import java.util.List;

public interface IGeneralDao<T> {
    List<T> findAll();

    T findOne(Long id);

    T save(T t);

    T remove(T t);

    T remove(Long id);
}
