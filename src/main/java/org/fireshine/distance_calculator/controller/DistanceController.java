package org.fireshine.distance_calculator.controller;

import org.fireshine.distance_calculator.model.Distance;
import org.fireshine.distance_calculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DistanceController {

    private final DistanceService distanceService;

    @Autowired
    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/distances")
    public Page<Distance> getAll(Pageable pageable) {
        return distanceService.findAll(pageable);
    }

    @PostMapping("/distances")
    public Distance create(@RequestBody Distance distance) {
        return distanceService.save(distance);
    }

}
