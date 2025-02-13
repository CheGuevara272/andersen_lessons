package org.andersen_project.repository;

import org.andersen_project.entity.User;
import org.hibernate.SessionFactory;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends HibernateRepository<User> {
    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    public Optional<User> findByLogin(String login) {
        List<User> users = this.findAll();
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }
}
