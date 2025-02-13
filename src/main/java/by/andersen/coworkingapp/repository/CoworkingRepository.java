package by.andersen.coworkingapp.repository;


import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class CoworkingRepository extends HibernateRepository<CoworkingSpace> implements Serializable {
    @Autowired
    public CoworkingRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CoworkingSpace.class);
    }

    public Optional<CoworkingSpace> findByName(String name) {
        List<CoworkingSpace> spaces = this.findAll();
        return spaces.stream()
                .filter(coworkingSpace -> coworkingSpace.getCoworkingName().equals(name))
                .findAny();
    }
}
