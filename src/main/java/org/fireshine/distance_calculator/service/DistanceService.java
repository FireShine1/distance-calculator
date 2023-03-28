package org.fireshine.distance_calculator.service;

import org.fireshine.distance_calculator.exceptions.DistanceNotFoundException;
import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.model.Distance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DistanceService {

    public Page<Distance> findAll(Pageable pageable);
    public Distance findByFromCityByToCity(City fromCity, City toCity) throws DistanceNotFoundException;
    public Distance save(Distance distance);
    public void saveAll(List<Distance> distances);

}
