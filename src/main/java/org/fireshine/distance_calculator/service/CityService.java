package org.fireshine.distance_calculator.service;

import org.fireshine.distance_calculator.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {

    public Page<City> findAll(Pageable pageable);
    public Optional<City> findById(Long id);
    public City save(City city);
    public void delete(Long id);

}
