package org.fireshine.distance_calculator.service;

import org.fireshine.distance_calculator.dto.CityDto;
import org.fireshine.distance_calculator.model.City;

public interface CityDtoMapping {

    public CityDto mapToCityDto(City city);

}
