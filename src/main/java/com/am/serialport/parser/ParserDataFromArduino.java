package com.am.serialport.parser;

import com.am.exception.ApplicationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class ParserDataFromArduino implements CommonParser{
    @Override
    public Map<Integer, Double> parseReadDataFromArduino(String string) {
        try{
            Map<Integer,Double> resultParse = new HashMap<>();
            String[] strings = string.split(",");
            for (String s : strings) {
                String[] intAndFloat = s.split(":");
                resultParse.put(Integer.parseInt(intAndFloat[0]), Double.parseDouble((intAndFloat[1])));
            }

            return resultParse;
        }catch (ApplicationException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyMap();
    }
}
