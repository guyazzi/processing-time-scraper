package com.processingtime.scraper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDto {

    private Long id;
    private String processingTime;
    private String lastUpdated;
}
