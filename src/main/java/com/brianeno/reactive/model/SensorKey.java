package com.brianeno.reactive.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Getter
@Setter
@EqualsAndHashCode
@PrimaryKeyClass
public class SensorKey implements Serializable {

    @PrimaryKeyColumn(name = "sensor_id", type = PARTITIONED)
    private String sensorId;

    @PrimaryKeyColumn(name = "reported_time", ordinal = 0)
    private LocalDateTime reportedTime;

    public SensorKey(String sensorId, LocalDateTime reportedTime) {
        this.sensorId = sensorId;
        this.reportedTime = reportedTime;
    }

    @Override
    public String toString() {
        return "SensorKey{" +
                "sensorId='" + sensorId + '\'' +
                ", reportTime=" + reportedTime +
                '}';
    }
}
