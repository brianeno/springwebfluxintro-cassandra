package com.brianeno.reactive.controller;

import com.brianeno.reactive.ReactiveApplication;
import com.brianeno.reactive.model.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ReactiveApplication.class)
public class SensorReadingControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllUsers() {
        webTestClient.get()
                .uri("/v2/readings")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }

    @Test
    void getUserById() {
        webTestClient.get()
                .uri("/v2/readings/1234")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Sensor.class)
                .value(reading -> reading.getKey().equals(1));
    }
}