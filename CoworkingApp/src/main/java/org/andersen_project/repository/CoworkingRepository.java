package org.andersen_project.repository;

import org.andersen_project.entity.CoworkingSpace;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CoworkingRepository extends HibernateRepository<CoworkingSpace> implements Serializable {
    public CoworkingRepository(SessionFactory sessionFactory, Class<CoworkingSpace> entityClass) {
        super(sessionFactory, entityClass);
    }

    public Optional<CoworkingSpace> findByName(String name) {
        List<CoworkingSpace> spaces = this.findAll();
        return spaces.stream()
                .filter(coworkingSpace -> coworkingSpace.getCoworkingName().equals(name))
                .findAny();
    }
}
