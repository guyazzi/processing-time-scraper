package com.processingtime.scraper.service;

import com.processingtime.scraper.dto.ResultDto;

public interface IResultService {

    ResultDto getLatestResult();

    ResultDto addLatestResult(ResultDto resultDto);
}
