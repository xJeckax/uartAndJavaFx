package com.am.serialport.listener;

import com.am.serialport.data.SerialPortData;
import com.am.serialport.parser.ParserDataFromArduino;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SerialPortReadMessageListener implements SerialPortMessageListenerWithExceptions {
    private final ParserDataFromArduino parseDataFromArduino;
    private final SerialPortData serialPortData;


    @Override
    public byte[] getMessageDelimiter() {
        return new byte[]{(byte) 0x3b, (byte) 0x24};
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return false;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] uartMessage = serialPortEvent.getReceivedData();
        String messageFromArduino = new String(uartMessage);
        Map<Integer, Double> sensorToValue = parseDataFromArduino.parseDataFromArduino(messageFromArduino);
        serialPortData.addReadData(sensorToValue);
    }

    @Override
    public void catchException(Exception e) {
        e.printStackTrace();
    }
}
