package com.am.service.impl;

import com.am.models.ExperimentData;
import com.am.service.ExperimentDataService;
import com.am.service.LoadService;
import com.am.service.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperimentDataServiceImpl implements ExperimentDataService {
    private final SaveService saveService;
    private final LoadService loadService;

    @Override
    public void save(String path, ExperimentData data) {
        log.info("Save the experiment to file");
        saveService.save(path, data);
    }

    @Override
    public List<ExperimentData> load(String path) {
        log.info("Load the experiment file");
        return loadService.load(path);
    }
}