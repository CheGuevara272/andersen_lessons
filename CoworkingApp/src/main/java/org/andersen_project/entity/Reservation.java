package org.andersen_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reservation_id")
    private Integer reservationId;

    @Setter
    @OneToOne
    private User user;

    @OneToOne
    private CoworkingSpace space;

    @Setter
    @Column(name = "startDate")
    private LocalDate reservationStartDate;

    @Setter
    @Column(name = "endDate")
    private LocalDate reservationEndDate;

}
