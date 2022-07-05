package com.processingtime.scraper.service;

import com.processingtime.scraper.dto.ResultDto;
import com.processingtime.scraper.repository.entity.Result;
import com.processingtime.scraper.repository.ResultRepository;
import com.processingtime.scraper.util.ResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ResultService implements IResultService {

    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;


    @Override
    public ResultDto getLatestResult() {
        Optional<Result> resultOptional = resultRepository.findById(1L);
        return resultOptional.map(resultMapper::toDto).orElse(null);
    }

    @Override
    public ResultDto addLatestResult(ResultDto resultDto) {

        ResultDto resultDto1 = getLatestResult();
        Result resultToUpdate;

        if (resultDto1 == null) {
            resultToUpdate = resultMapper.toEntity(resultDto);
        } else {
            resultToUpdate = resultMapper.toEntity(resultDto1);
        }

        resultToUpdate.setLastUpdated(resultDto.getLastUpdated());
        resultToUpdate.setProcessingTime(resultDto.getProcessingTime());

        return resultMapper.toDto(resultRepository.save(resultToUpdate));
    }


}
