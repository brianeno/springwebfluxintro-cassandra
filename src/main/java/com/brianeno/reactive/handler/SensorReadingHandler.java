package com.brianeno.reactive.handler;

import com.brianeno.reactive.model.Sensor;
import com.brianeno.reactive.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SensorReadingHandler {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private final SensorService service;

    public SensorReadingHandler(final SensorService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return this.service.findById(request.pathVariable("id"))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), Sensor.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().body(this.service.findAll(), Sensor.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getByIAndReportedTime(ServerRequest request) {
        String id = request.pathVariable("id");
        String timeStr = request.pathVariable("reportedTime");
        LocalDateTime time = LocalDateTime.parse(timeStr, format);
        Flux<Sensor> val = this.service.findByIdAndReportedTime(id, time);
        return ServerResponse.ok().body(val, Sensor.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}