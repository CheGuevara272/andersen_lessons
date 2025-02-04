package org.andersen_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer reservationId;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private CoworkingSpace space;

    @Setter
    private LocalDate reservationStartDate;
    @Setter
    private LocalDate reservationEndDate;

}
