package by.andersen.coworkingapp.model.entity;

import by.andersen.coworkingapp.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Check(constraints = "user_role IN ('ADMIN', 'CUSTOMER')")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", columnDefinition = "VARCHAR(20)")
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Reservation> reservationSet;

    @Column(name = "enabled")
    private boolean enabled = true;
    @Column(name = "locked")
    private boolean locked = false;
    @Column(name = "account_expired")
    private boolean accountExpired = false;
    @Column(name = "credentials_expired")
    private boolean credentialsExpired = false;
}
