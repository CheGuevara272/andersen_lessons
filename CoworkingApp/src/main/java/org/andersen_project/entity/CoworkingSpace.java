package org.andersen_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CoworkingSpace implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @Column(name = "COWORKING_ID", nullable = false)
    private Integer coworkingId;
    @Column(name = "COWORKINGNAME")
    private String coworkingName;
    private double price;
    private CoworkingType coworkingType;
    @Setter
    private boolean reserved;

    @OneToMany
    private Set<Reservation> reservations;

}
