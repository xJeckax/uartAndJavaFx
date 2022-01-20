package com.am.service.impl;

import com.am.models.ExperimentData;
import com.am.service.SaveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SaveToNotepad implements SaveService {
    @Override
    public void save(String path, ExperimentData data) {
        log.info("Save to file - {} by path - {}", data, path);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String experiment = objectMapper.writeValueAsString(data);

            StringBuilder sb = new StringBuilder(experiment);
            sb.append(System.lineSeparator());

            String fileName = "\\experiment.txt";
            File file = new File(path + fileName);
            if (file.createNewFile()) {
                log.info("File has been created");
            }
            OutputStream fileWriter = new FileOutputStream(file,true);
            fileWriter.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
