package com.am.serialport.parser;

import java.util.Map;

public interface CommonParser {

    Map<Integer,Double> parseReadDataFromArduino(String string);
}
