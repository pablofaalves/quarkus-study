package br.com.pfaa.quarkusstudy.rest.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class ProductCreationDto {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 50, message = "Name has more than 50 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=8, fraction=2)
    private BigDecimal price;
}
