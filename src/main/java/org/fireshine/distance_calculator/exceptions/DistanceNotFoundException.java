package org.fireshine.distance_calculator.exceptions;

public class DistanceNotFoundException extends RuntimeException{

    public DistanceNotFoundException(String fromCity, String toCity) {
        super("Distance between " + fromCity + " and " + toCity + " not found.");
    }

}
