package com.cathay.holdings.myr.router;

import com.cathay.holdings.myr.dto.FirstTryDTO;
import com.cathay.holdings.myr.handler.FirstTryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FirstTryRouter {

    @Bean
    @RouterOperations(value = {
            @RouterOperation(path = "/MT/first/tries", method = RequestMethod.GET, beanClass = FirstTryHandler.class, beanMethod = "getAllFirstTries", operation = @Operation(operationId = "getAllFirstTries", responses = {
                    @ApiResponse(responseCode = "200", description = "getAllFirstTries", content = @Content(schema = @Schema(implementation = FirstTryDTO.class)))})),
            @RouterOperation(path = "/MT/first/tries/{id}", method = RequestMethod.GET, beanClass = FirstTryHandler.class, beanMethod = "getFirstTryById",
                    operation = @Operation(operationId = "getFirstTryById",
                    parameters ={@Parameter(in= ParameterIn.PATH, name="id")},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "getFirstTryById", content = @Content(schema = @Schema(implementation = FirstTryDTO.class)))})),

    })
    public RouterFunction<ServerResponse> routes(FirstTryHandler handler) {
        return route()
                .GET("/first/tries", handler::getAllFirstTries)
                .GET("/first/tries/{id}", handler::getFirstTryById)
                .build();
    }
}
