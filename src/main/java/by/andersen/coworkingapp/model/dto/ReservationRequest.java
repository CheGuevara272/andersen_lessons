package by.andersen.coworkingapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotBlank(message = "Coworking space name is required")
    @Size(min = 4, max = 30)
    private String spaceName;
}
