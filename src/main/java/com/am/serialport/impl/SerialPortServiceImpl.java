package com.am.serialport.impl;

import com.am.exception.ApplicationException;
import com.am.serialport.SerialPortService;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class SerialPortServiceImpl implements SerialPortService {

    @Override
    public List<SerialPort> getComputerSerialPorts() {
        return Arrays.asList(SerialPort.getCommPorts());
    }

    @Override
    public SerialPort getComputerSerialPort(String name) {
        try {
            return SerialPort.getCommPort(name);
        } catch (SerialPortInvalidPortException e) {
            throw new ApplicationException("К порту ничего не подключено или проверьте соединение");
        }
    }
}
