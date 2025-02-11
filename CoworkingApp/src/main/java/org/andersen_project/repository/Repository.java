package org.andersen_project.repository;

import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Serializable> {
    void update(T item);

    List<T> findAll();

    Optional<T> findById(Integer id) throws InputException;

    void deleteById(Integer id);
}
