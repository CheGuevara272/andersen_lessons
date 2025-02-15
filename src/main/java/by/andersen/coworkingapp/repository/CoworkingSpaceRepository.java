package by.andersen.coworkingapp.repository;

import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoworkingSpaceRepository extends JpaRepository<CoworkingSpace, Integer> {
    List<CoworkingSpace> findByCoworkingName(String coworkingName);

    List<CoworkingSpace> findByReservedFalse();
}
