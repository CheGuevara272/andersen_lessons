package by.andersen.coworkingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;

    private String password;
}
