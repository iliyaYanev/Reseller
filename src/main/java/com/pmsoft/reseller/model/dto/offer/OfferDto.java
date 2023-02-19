package com.pmsoft.reseller.model.dto.offer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class OfferDto {

    private Long id;

    private String username;

    @NotNull(message = "Please provide a description!")
    @Length(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull(message = "Please provide a price!")
    @Positive(message = "Price must be a positive number!")
    private BigDecimal price;

    @NotBlank(message = "You must select a condition!")
    private String conditionName;
}
