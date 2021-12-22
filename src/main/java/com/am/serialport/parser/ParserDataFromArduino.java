package com.am.serialport.parser;

import com.am.exception.ApplicationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class ParserDataFromArduino implements CommonParser {
    @Override
    public Map<Integer, Double> parseDataFromArduino(String string) {
        try {
            Map<Integer, Double> resultParse = new HashMap<>();
            int count = 0;
            String[] sensorValues = string.substring(3).split(" ");
            for (String s : sensorValues) {
                resultParse.put(count, Double.parseDouble(s));
                count++;
            }

            return resultParse;
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyMap();
    }
}
