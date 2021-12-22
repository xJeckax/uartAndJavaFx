package com.am.serialport.listener;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.springframework.stereotype.Component;

@Component
public class SerialPortWriteListener implements SerialPortDataListener {
    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_WRITTEN;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        System.out.println("Wrote");
    }
}
