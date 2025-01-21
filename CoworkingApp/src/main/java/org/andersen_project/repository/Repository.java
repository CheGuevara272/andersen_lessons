package org.andersen_project.repository;

import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {
    boolean update(T item);

    List<T> findAll();

    T findById(Integer id) throws InputException;

    //    T findByName(String name) throws InputException;
    boolean deleteById(Integer id);
//    boolean deleteByName(String name);
}
