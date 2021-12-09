package com.am.serialport.listener;

import com.am.serialport.data.SerialPortData;
import com.am.serialport.parser.CommonParser;
import com.am.serialport.parser.ParserDataFromArduino;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerialPortReadMessageListener implements SerialPortMessageListenerWithExceptions {
    private final ParserDataFromArduino parser;
    private final SerialPortData data;

    /*public String byteToHex(byte num)
    {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }*/


    @Override
    public void catchException(Exception e) {
        System.out.println(e.getMessage());
    }

    @Override
    public byte[] getMessageDelimiter() {
        return  new byte[]{Character.getDirectionality('$')};
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        byte[] message = serialPortEvent.getReceivedData();
        /*for (byte b : message) {

            System.out.println((char) b);
        }*/
       System.out.print(message);
    }
}
