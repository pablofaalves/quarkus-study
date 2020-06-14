package br.com.pfaa.quarkusstudy.rest.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class ProductCreationDto {
    @NotEmpty(message = "Name cannot be empty")
    @Max(value = 50, message = "Name is too long")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal price;
}
