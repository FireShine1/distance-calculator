package org.fireshine.distance_calculator.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DistancePK implements Serializable {

    private Long fromCity;
    private Long toCity;

}
