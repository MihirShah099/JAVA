package com.example.eCommerce.prac1.utility.service;

import java.util.List;

public interface IBaseService<T, K> {
    T saveEntity(T model) throws Exception;

    T updateEntity(T model) throws Exception;

    void deleteEntity(T model) throws Exception;

    T getEntityById(K id) throws Exception;

    List<T> getAllEntities() throws Exception;
}
