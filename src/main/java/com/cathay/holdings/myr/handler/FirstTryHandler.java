package com.cathay.holdings.myr.handler;

import com.cathay.holdings.myr.dto.FirstTryDTO;
import com.cathay.holdings.myr.service.FirstTryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Slf4j
public class FirstTryHandler {
    private final FirstTryService firstTryService;

    public FirstTryHandler(FirstTryService firstTryService) {
        this.firstTryService = firstTryService;
    }

    @Operation(summary = "Get all FirstTry entities")
    public Mono<ServerResponse> getAllFirstTries(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(firstTryService.getAllFirstTries(), FirstTryDTO.class)
                .onErrorResume(this::handleException);
    }

    @Operation(summary = "Get one FirstTry entities")
    public Mono<ServerResponse> getFirstTryById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return firstTryService.getFirstTryById(id)
                .flatMap(firstTryDTO -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(firstTryDTO)))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(this::handleException);
    }

    private Mono<ServerResponse> handleException(Throwable e) {
        log.error("error occur: " + e.getLocalizedMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return ServerResponse.status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ErrorResponse(500, e.getMessage()));
    }

    private static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
