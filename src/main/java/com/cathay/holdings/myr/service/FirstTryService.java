package com.cathay.holdings.myr.service;

import com.cathay.holdings.myr.dao.entities.FirstTry;
import com.cathay.holdings.myr.dao.persistence.FirstTryRepository;
import com.cathay.holdings.myr.dto.FirstTryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FirstTryService {

    private final FirstTryRepository firstTryRepository;
    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    public FirstTryService(FirstTryRepository firstTryRepository, ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
        this.firstTryRepository = firstTryRepository;
    }
    public Mono<FirstTryDTO> getFirstTryById(Long id) {
        log.debug("getFirstTryById is called, id:{}", id);
        return reactiveRedisTemplate.opsForValue().get("FIRST:TRY:" + id)
                .switchIfEmpty(firstTryRepository.findById(id)
                        .map(this::convertToFirstTryDTO)
                        .flatMap(dto ->
                                reactiveRedisTemplate.opsForValue().
                                        set("FIRST:TRY:"+ id, dto).thenReturn(dto)))
                .map(dto -> (FirstTryDTO) dto);
    }

    public Flux<FirstTryDTO> getAllFirstTries() {
        return firstTryRepository
                .findAll()
                .onBackpressureBuffer()
                .map(this::convertToFirstTryDTO);
    }

    private FirstTryDTO convertToFirstTryDTO(FirstTry firstTry) {
        return new FirstTryDTO(firstTry.getDesc());
    }
}
