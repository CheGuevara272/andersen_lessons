package by.andersen.coworkingapp.model.dto;

import by.andersen.coworkingapp.model.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Login is required")
    @Size(min = 4, max = 20)
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;

    @NotNull(message = "Role is required")
    private UserRole userRole;
}
