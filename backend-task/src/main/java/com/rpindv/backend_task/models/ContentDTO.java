package com.rpindv.backend_task.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentDTO {
    private Long id;
    private String title;
    private String description;
    private Integer category;
}
