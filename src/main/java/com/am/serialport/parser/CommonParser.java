package com.am.serialport.parser;

import java.util.Map;

public interface CommonParser {

    Map<Integer,Double> parseDataFromArduino(String string);
}
