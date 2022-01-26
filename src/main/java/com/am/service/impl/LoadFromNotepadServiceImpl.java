package com.am.service.impl;

import com.am.models.ExperimentData;
import com.am.service.LoadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoadFromNotepadServiceImpl implements LoadService {

    @Override
    public List<ExperimentData> load(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ExperimentData> data = new ArrayList<>();
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            bufferedReader.lines().forEach(s -> {
                try {
                    data.add(objectMapper.readValue(s, ExperimentData.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
