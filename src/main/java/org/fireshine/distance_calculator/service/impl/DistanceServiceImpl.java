package org.fireshine.distance_calculator.service.impl;

import org.fireshine.distance_calculator.exceptions.DistanceNotFoundException;
import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.model.Distance;
import org.fireshine.distance_calculator.repository.DistanceRepository;
import org.fireshine.distance_calculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DistanceServiceImpl implements DistanceService {

    private final DistanceRepository distanceRepository;

    @Autowired
    public DistanceServiceImpl(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    @Override
    public Page<Distance> findAll(Pageable pageable) {
        return distanceRepository.findAll(pageable);
    }

    //Since distance between A and B equals to distance from B to A - we are searching both variants
    //Currently database allows to store both variants, so we can have 2 entries for the same distance
    //I don't think that this is perfect, but I'm not sure, how to improve this.
    @Override
    public Distance findByFromCityByToCity(City fromCity, City toCity)
            throws  DistanceNotFoundException {
        Optional<Distance> distance = distanceRepository
                                        .findByFromCityAndToCity(fromCity, toCity);
        if (distance.isPresent()) {
            return distance.get();
        } else {
            distance = distanceRepository
                    .findByFromCityAndToCity(toCity, fromCity);
            if (distance.isPresent()) {
                return distance.get();
            } else {
                throw new DistanceNotFoundException(fromCity.getName(), toCity.getName());
            }
        }
    }

    @Override
    public Distance save(Distance distance) {
        return  distanceRepository.save(distance);
    }
}
