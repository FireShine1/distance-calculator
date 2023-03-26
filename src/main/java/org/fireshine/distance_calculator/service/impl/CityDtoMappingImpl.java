package org.fireshine.distance_calculator.service.impl;

import org.fireshine.distance_calculator.dto.CityDto;
import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.service.CityDtoMapping;
import org.springframework.stereotype.Service;

@Service
public class CityDtoMappingImpl implements CityDtoMapping {

    @Override
    public CityDto mapToCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    }

}
