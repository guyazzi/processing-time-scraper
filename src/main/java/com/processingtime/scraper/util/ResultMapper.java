package com.processingtime.scraper.util;

import com.processingtime.scraper.dto.ResultDto;
import com.processingtime.scraper.repository.entity.Result;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResultMapper {

    Result toEntity (ResultDto resultDto);

    ResultDto toDto (Result result);
}
