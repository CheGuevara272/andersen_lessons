package org.andersen_project.repository;

import org.andersen_project.entity.User;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserRepository extends HibernateRepository<User> {
    public UserRepository(SessionFactory sessionFactory, Class<User> userClass) {
        super(sessionFactory, userClass);
    }

    public Optional<User> findByLogin(String login) {
        List<User> users = this.findAll();
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }
}
