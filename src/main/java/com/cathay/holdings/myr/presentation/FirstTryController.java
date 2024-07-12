package com.cathay.holdings.myr.presentation;

import com.cathay.holdings.myr.dto.FirstTryDTO;
import com.cathay.holdings.myr.service.FirstTryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller/first/tries")
public class FirstTryController {

    private final FirstTryService firstTryService;

    FirstTryController(FirstTryService firstTryService) {
        this.firstTryService = firstTryService;
    }

    @GetMapping("/{id}")
    public Mono<FirstTryDTO> getFirstTryById(@PathVariable Long id) {
        return firstTryService.getFirstTryById(id);
    }

    @GetMapping
    public Flux<FirstTryDTO> getAllFirstTry() {
        return firstTryService.getAllFirstTries();
    }

}
