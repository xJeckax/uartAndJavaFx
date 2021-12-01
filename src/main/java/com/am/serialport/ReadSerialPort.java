package com.am.serialport;

import com.fazecast.jSerialComm.SerialPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.InputStreamReader;

@Data
@RequiredArgsConstructor
public class ReadSerialPort implements Runnable {
    private final SerialPort serialPort;

    @Override
    public void run() {
        System.out.println("Поток с чтением!");
        while (true) {
            while (serialPort.bytesAvailable() > 0) {
                try (
                        InputStreamReader inputStreamReader = new InputStreamReader(serialPort.getInputStream());
                ) {
                    int count = serialPort.bytesAvailable();
                    char[] chars = new char[count];
                    inputStreamReader.read(chars, 0, count);
                    System.out.println(chars.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток на чтение отработал");
                }
            }
        }
    }
}
