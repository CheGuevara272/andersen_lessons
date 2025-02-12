package by.andersen.coworkingapp.model.entity;

import by.andersen.coworkingapp.model.enums.CoworkingType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "coworkings")
@Check(constraints = "coworking_type IN ('Open space', 'Private workspace', 'Minimal', 'Full-service')")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CoworkingSpace implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @Column(name = "coworking_id", nullable = false)
    private Integer coworkingId;

    @Column(name = "coworking_name")
    private String coworkingName;

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "coworking_type", columnDefinition = "VARCHAR(20)")
    private CoworkingType coworkingType;

    @Setter
    @Column(name = "reserved")
    private boolean reserved;

    @OneToMany
    private Set<Reservation> reservations;

}
