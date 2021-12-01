package com.am.serialport;

import com.fazecast.jSerialComm.SerialPort;

import java.util.List;

public interface SerialPortService {

    List<SerialPort> getComputerSerialPorts();
    SerialPort getComputerSerialPort(String name);
}
