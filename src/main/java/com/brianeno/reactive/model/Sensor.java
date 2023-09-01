package com.brianeno.reactive.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("sensor")
public class Sensor {

    @PrimaryKey
    private SensorKey key;

    @Column("sensor_value")
    private String value;

    @Column("created_time")
    private LocalDateTime createdTime;

    public Sensor(SensorKey key, String value, LocalDateTime createdTime) {
        this.key = key;
        this.value = value;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
