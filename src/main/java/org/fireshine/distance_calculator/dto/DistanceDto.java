package org.fireshine.distance_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceDto {

    private String fromCity;
    private String toCity;
    private String distance;
    private String mode;
    private String errorMessage;

}
