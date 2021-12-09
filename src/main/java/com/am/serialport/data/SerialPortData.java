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
    private final Map<Integer, Double> readDataFromArduino = new ConcurrentHashMap<>();

    {
        for (int i = 0; i < 14; i++) {
            readDataFromArduino.put(i, 0.0);
        }
    }

    public synchronized void addReadData(Map<Integer, Double> data) {
        readDataFromArduino.clear();
        readDataFromArduino.putAll(data);
    }

    public synchronized Map<Integer, Double> getReadDataFromArduino() {
        return readDataFromArduino;
    }
}
