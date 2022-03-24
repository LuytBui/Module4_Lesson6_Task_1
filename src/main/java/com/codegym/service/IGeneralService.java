package com.codegym.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T findOne(Long id);

    T save(T t);

    T remove(T t);

    T remove(Long id);

}