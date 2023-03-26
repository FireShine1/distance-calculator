package org.fireshine.distance_calculator.repository;

import org.fireshine.distance_calculator.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}