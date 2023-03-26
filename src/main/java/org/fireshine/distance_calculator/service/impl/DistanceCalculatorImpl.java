package org.fireshine.distance_calculator.service.impl;

import org.fireshine.distance_calculator.dto.CalculationMode;
import org.fireshine.distance_calculator.dto.CalculationRequestDto;
import org.fireshine.distance_calculator.dto.DistanceDto;
import org.fireshine.distance_calculator.exceptions.DistanceNotFoundException;
import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.model.Distance;
import org.fireshine.distance_calculator.service.DistanceCalculator;
import org.fireshine.distance_calculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistanceCalculatorImpl implements DistanceCalculator {

    private final DistanceService distanceService;

    @Autowired
    public DistanceCalculatorImpl(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Override
    public Double getDistance(Double lat1, Double lat2, Double lon1, Double lon2) {
        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    @Override
    public List<Object> getAll(CalculationRequestDto request) {
        List<Object> result = new ArrayList<>();
        switch (request.getMode()) {
            case CROWFLIGHT:
                result.addAll(calculateDistances(request.getFromCities(),
                                                 request.getToCities())
                );
                break;
            case DISTANCE_MATRIX:
                result.addAll(findDistances(request.getFromCities(),
                                            request.getToCities())
                );
                break;
            case ALL:
                result.addAll(calculateDistances(request.getFromCities(),
                        request.getToCities())
                );
                result.addAll(findDistances(request.getFromCities(),
                        request.getToCities())
                );
                break;
        }
        return result;
    }

    private List<DistanceDto> calculateDistances(List<City> fromCities,
                                                 List<City> toCities
    ) {
        List<DistanceDto> result = new ArrayList<>();
        for (City fromCity : fromCities) {
            for (City toCity : toCities) {
                Double length = getDistance(fromCity.getLatitude(), toCity.getLatitude(),
                        fromCity.getLongitude(), toCity.getLongitude());
                String lengthDto = String.format("%.2f km", length);
                result.add(new DistanceDto(fromCity.getName(), toCity.getName(),
                        lengthDto, CalculationMode.CROWFLIGHT.name()));
            }
        }
        return result;
    }

    //I'm not sure that this is the bet way to do it
    //But I'm sure, that if, for example, user requested 100 distances, and 1 or 2 weren't found -
    // it is a bad idea to return single exception instead of whole response
    private List<Object> findDistances(List<City> fromCities,
                                       List<City> toCities
    ) {
        List<Object> result = new ArrayList<>();
        for (City fromCity : fromCities) {
            for (City toCity : toCities) {
                try {
                    Distance distance = distanceService
                                            .findByFromCityByToCity(fromCity, toCity);
                    String lengthDto = String.format("%.2f km", distance.getDistance());
                    result.add(new DistanceDto(fromCity.getName(), toCity.getName(),
                            lengthDto, CalculationMode.DISTANCE_MATRIX.name()));
                } catch (DistanceNotFoundException e) {
                    result.add(e.getMessage());
                }
            }
        }
        return result;
    }

}
