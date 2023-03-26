package org.fireshine.distance_calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "distance")
@IdClass(DistancePK.class)
public class Distance {

    @Id
    @OneToOne
    @JoinColumn(name = "from_city", referencedColumnName = "id")
    private City fromCity;

    @Id
    @OneToOne
    @JoinColumn(name = "to_city", referencedColumnName = "id")
    private City toCity;
    private Double distance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Distance distance = (Distance) o;
        return fromCity != null && Objects.equals(fromCity, distance.fromCity)
                && toCity != null && Objects.equals(toCity, distance.toCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromCity, toCity);
    }
}
