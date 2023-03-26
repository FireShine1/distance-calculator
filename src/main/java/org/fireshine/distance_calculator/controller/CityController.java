package org.fireshine.distance_calculator.controller;

import org.fireshine.distance_calculator.dto.CityDto;
import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.service.CityDtoMapping;
import org.fireshine.distance_calculator.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;
    private final CityDtoMapping cityDtoMapping;

    @Autowired
    public CityController(CityService cityService, CityDtoMapping cityDtoMapping) {
        this.cityService = cityService;
        this.cityDtoMapping = cityDtoMapping;
    }

    @GetMapping("/cities")
    public Page<CityDto> getAll(Pageable pageable) {
        return cityService.findAll(pageable).map(cityDtoMapping::mapToCityDto);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            CityDto cityDTO = cityDtoMapping.mapToCityDto(
                    cityService.findById(id).get()
            );
            return ResponseEntity.ok(cityDTO);
        }
    }

    @PostMapping("/cities")
    public City create(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> update(@RequestBody City city,
                                       @PathVariable Long id
    ) {
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            City newCity = cityService.save(city);
            return ResponseEntity.ok(newCity);
        }
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            cityService.delete(id);
            return ResponseEntity.ok().build();
        }
    }

}
