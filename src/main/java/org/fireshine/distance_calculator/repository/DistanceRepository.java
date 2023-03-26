package org.fireshine.distance_calculator.repository;

import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.model.Distance;
import org.fireshine.distance_calculator.model.DistancePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistanceRepository extends JpaRepository<Distance, DistancePK> {

    public Optional<Distance> findByFromCityAndToCity(City fromCity, City toCity);

}