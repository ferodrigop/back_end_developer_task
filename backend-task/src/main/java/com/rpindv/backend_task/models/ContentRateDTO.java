package com.rpindv.backend_task.models;

import com.rpindv.backend_task.helpers.validators.custom_anotations.RatingsValue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class ContentRateDTO {
    @Range(min=1, message = "provide a valid rate id")
    private Long id;

    @Range(min=1, message = "provide a valid content id")
    private Long contentId;

    @Range(min=1, message = "provide a valid user id")
    private Integer userId;

    @RatingsValue (message = "Rate value must be between 1 and 5 with 0.5 or 0 as decimals. Ex: 1.5, 4.0")
    @NotNull (message = "A rate value is needed")
    private Double rate;
}
