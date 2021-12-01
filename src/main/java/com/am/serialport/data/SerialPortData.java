package com.am.serialport.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Scope("singleton")
@NoArgsConstructor
public class SerialPortData {
    private final Map<Integer,Double> readDataFromArduino = new HashMap<>();
    private final Map<Integer,Float> writeDataToOut = new HashMap<>();

    public void addReadData(Map<Integer,Double> data){
        readDataFromArduino.clear();
        readDataFromArduino.putAll(data);
    }
}
