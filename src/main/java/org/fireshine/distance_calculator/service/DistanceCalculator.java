package org.fireshine.distance_calculator.service;

import org.fireshine.distance_calculator.dto.CalculationRequestDto;
import org.fireshine.distance_calculator.dto.DistanceDto;

import java.util.List;

public interface DistanceCalculator {

    public Double getDistance(Double lat1, Double lat2, Double lon1, Double lon2);
    public List<DistanceDto> getAll(CalculationRequestDto request);

}
