package com.am.serialport.parser;

import com.am.exception.ApplicationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@NoArgsConstructor
public class ParserDataFromArduino implements CommonParser {
    @Override
    public Map<Integer, Double> parseDataFromArduino(String string) {
        try {
            Map<Integer, Double> parseResult = new HashMap<>();
            Pattern pattern = Pattern.compile("[\\d][.][\\d{2}]");

            int count = 0;
            String[] sensorValues = string.substring(3).split(" ");
            for (String s : sensorValues) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    parseResult.put(count, Double.parseDouble(s.substring(matcher.start(),matcher.end())));
                }
                count++;
            }

            return parseResult;
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyMap();
    }
}
