package by.andersen.coworkingapp.model.dto;

import by.andersen.coworkingapp.model.enums.CoworkingType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoworkingSpaceRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String coworkingName;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price cannot be empty")
    private Double price;

    @NotNull(message = "Type must be selected")
    private CoworkingType coworkingType;
}
