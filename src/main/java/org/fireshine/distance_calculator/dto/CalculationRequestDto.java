package org.fireshine.distance_calculator.dto;

import lombok.Data;
import org.fireshine.distance_calculator.model.City;

import java.util.List;

@Data
public class CalculationRequestDto {

    private List<City> fromCities;
    private List<City> toCities;
    private CalculationMode mode;

}
