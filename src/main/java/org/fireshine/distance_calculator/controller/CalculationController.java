package org.fireshine.distance_calculator.controller;

import org.fireshine.distance_calculator.dto.CalculationRequestDto;
import org.fireshine.distance_calculator.service.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculationController {

    private final DistanceCalculator distanceCalculator;

    @Autowired
    public CalculationController(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @PostMapping("/calculator")
    public Page<Object> calculate(@RequestBody CalculationRequestDto request,
                                  Pageable pageable
    ) {
        List<Object> distances = distanceCalculator.getAll(request);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), distances.size());
        return new PageImpl<>(distances.subList(start, end),
                                        pageable, distances.size());
    }

}
