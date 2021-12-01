package com.am.serialport;

import com.fazecast.jSerialComm.SerialPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Data
@RequiredArgsConstructor
public class WriteSerialPort implements Runnable {
    private final SerialPort serialPort;

    @Override
    public void run() {
        System.out.println("Поток отправки!");
        try (
                OutputStream outputStream = serialPort.getOutputStream();
        ) {
            String ss = "$1.0;";
            byte[] chars = ss.getBytes(StandardCharsets.UTF_8);
            outputStream.write(chars);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Поток на запись отработал");
        }

    }
}
