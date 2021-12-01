package com.am.serialport;

import com.fazecast.jSerialComm.SerialPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@Scope("singleton")
public class MySerialPort {
   private SerialPort port = null;
   
   public void setPort(SerialPort port){
      if(this.port == null){
         this.port = port;
      }
   }
}
