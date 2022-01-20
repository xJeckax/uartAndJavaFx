package com.am.serialport.data;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton")
@NoArgsConstructor
public class SerialPortData {
    private final Map<Integer, Double> readDataSensors = new ConcurrentHashMap<>();
    private final Map<Integer, Double> readDataRestrictions = new ConcurrentHashMap<>();

    public synchronized void addReadDataSensors(Map<Integer, Double> data) {
        readDataSensors.clear();
        readDataSensors.putAll(data);
    }

    public synchronized Map<Integer, Double> getReadDataSensors() {
        return readDataSensors;
    }

    public synchronized void addReadDataRestrictions(Map<Integer, Double> data) {
        readDataRestrictions.clear();
        readDataRestrictions.putAll(data);
    }

    public synchronized Map<Integer, Double> getReadDataRestrictions() {
        return readDataRestrictions;
    }
}
