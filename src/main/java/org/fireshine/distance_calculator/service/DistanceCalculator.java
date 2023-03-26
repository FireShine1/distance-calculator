package org.fireshine.distance_calculator.service;

import org.fireshine.distance_calculator.dto.CalculationRequestDto;

import java.util.List;

public interface DistanceCalculator {

    public Double getDistance(Double lat1, Double lat2, Double lon1, Double lon2);
    public List<Object> getAll(CalculationRequestDto request);

}
