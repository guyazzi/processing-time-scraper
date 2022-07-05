package com.processingtime.scraper.repository;

import com.processingtime.scraper.repository.entity.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
}
