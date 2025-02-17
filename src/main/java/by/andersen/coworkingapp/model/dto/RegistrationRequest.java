package by.andersen.coworkingapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotBlank(message = "Login is required")
    @Size(min = 4, max = 20)
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;

}
