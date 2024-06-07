package com.rpindv.backend_task.models;

import com.rpindv.backend_task.helpers.validators.custom_anotations.NotNullOrBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class ContentDTO {
    @Range(min=1, message = "provide a valid id")
    private Long id;

    @NotNullOrBlank(message = "Please provide a Title")
    @Size(max = 200, message = "Title should not exceed 200 characters")
    private String title;

    @NotNullOrBlank(message = "Please provide a Description")
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String description;

    @Range(min=1, message = "provide a valid category id")
    @NotNull(message = "category id is required")
    private Integer category;
}
