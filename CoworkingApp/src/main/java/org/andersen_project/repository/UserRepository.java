package org.andersen_project.repository;

import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository<T extends Serializable> implements Repository<User> {
    private final List<User> userList = new ArrayList<>();

    public UserRepository(List<User> userList) {
        this.userList.addAll(userList);
    }

    @Override
    public boolean update(User user) {
        return userList.add(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>(userList);
        return users;
    }

    @Override
    public User findById(UUID id) throws InputException {
        return userList.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new InputException("User not found"));
    }

    @Override
    public User findByName(String name) throws InputException {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new InputException("User not found"));
    }

    @Override
    public boolean deleteById(UUID id) {
        return userList.removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public boolean deleteByName(String name) {
        return userList.removeIf(user -> user.getName().equals(name));
    }
}
