package com.am.serialport;

import com.fazecast.jSerialComm.SerialPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@Scope("singleton")
public class MySerialPort {
    private SerialPort port = null;

    public void setPort(SerialPort port) {
        if (this.port == null) {
            this.port = port;
        }
    }

    public void writeBytes(byte... bytes) {
        if (port != null && port.isOpen()) {
            int check = port.writeBytes(bytes, bytes.length);
            if (check == -1) {
                log.info("Write error");
            }
        } else {
            log.info("Port doesn't open");
        }
    }

    @PreDestroy
    private void destroy() {
        port.removeDataListener();
        if (port != null) {
            port.closePort();
        }
    }
}
