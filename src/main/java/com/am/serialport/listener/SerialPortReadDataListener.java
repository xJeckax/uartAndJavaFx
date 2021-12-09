package com.am.serialport.listener;

import com.am.serialport.data.SerialPortData;
import com.am.serialport.parser.ParserDataFromArduino;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerialPortReadDataListener implements SerialPortDataListener {
    private final ParserDataFromArduino parserDataFromArduino;
    private final SerialPortData data;

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        StringBuilder readData =  new StringBuilder();
        byte[] received = serialPortEvent.getReceivedData();
        for (byte b : received) {
            System.out.print((char) b);
            readData.append(Character.toString(b));
        }
        data.addReadData(parserDataFromArduino.parseReadDataFromArduino(readData.toString()));
        readData.delete(0, readData.length());
    }
}
