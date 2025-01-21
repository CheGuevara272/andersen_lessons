package org.andersen_project.context;

import org.andersen_project.repository.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RepositoryContext {
    private final Map<Class<?>, Repository<? extends Serializable>> repositoryMap = new HashMap<>();

    public <T extends Serializable> void putRepository(Repository<T> repository) {
        repositoryMap.put(repository.getClass(), repository);
    }

    public <T extends Serializable> Repository<T> getRepository(Class<?> clazz) {
        return (Repository<T>) repositoryMap.get(clazz);
    }
}
