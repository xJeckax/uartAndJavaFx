package com.am.serialport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendDataToArduino {
    private final MySerialPort selectedPort;

    public void write(Map<Integer, Double> data) {
        byte[] transformDataToBytes = convertData(data);
        selectedPort.writeBytes(transformDataToBytes);
    }

    private byte[] convertData(Map<Integer, Double> data) {
        StringBuilder protocol = new StringBuilder();
        protocol.append("$");
        data.forEach((integer, aDouble) -> {
            protocol.append(aDouble);
            if (integer != 13) {
                protocol.append(" ");
            }
        });
        protocol.append(";");
        log.info("Send data to arduino by protocol " + protocol);
        return protocol.toString().getBytes();
    }
}
