package com.rpindv.backend_task.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentRateDTO {
    private Long id;
    private Long contentId;
    private Integer userId;
    private Double rate;
}
