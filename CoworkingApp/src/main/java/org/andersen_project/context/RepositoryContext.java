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

    public <T extends Serializable> Repository<? extends Serializable> getRepository(Class<?> clazz) {
        return repositoryMap.get(clazz);
    }
}
