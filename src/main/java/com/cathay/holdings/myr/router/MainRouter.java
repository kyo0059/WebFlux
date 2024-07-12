package com.cathay.holdings.myr.router;

import com.cathay.holdings.myr.handler.FirstTryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class MainRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(FirstTryRouter firstTryRouter, FirstTryHandler handler) {
        return nest(path("/MT"),
                firstTryRouter.routes(handler)
        );
    }
}