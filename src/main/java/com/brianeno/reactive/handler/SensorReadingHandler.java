package com.brianeno.reactive.handler;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SensorReadingHandler {

    private final SensorService service;

    public SensorReadingHandler(final SensorService service) {
        this.service = service;
    }

    public Mono<ServerResponse> helloSpringWebFluxMono(ServerRequest request) {
        return this.service.findById(request.pathVariable("id"))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), Sensor.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> helloSpringWebFluxFlux(ServerRequest request) {
        return ServerResponse.ok().body(this.service.findAll(), Sensor.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}