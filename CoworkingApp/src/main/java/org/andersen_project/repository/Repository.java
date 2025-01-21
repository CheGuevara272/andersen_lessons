package org.andersen_project.repository;

import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface Repository<T extends Serializable> {
    boolean update(T item);
    List<T> findAll();
    T findById(UUID id) throws InputException;
    T findByName(String name) throws InputException;
    boolean deleteById(UUID id);
    boolean deleteByName(String name);
}
