package com.am.service.impl;

import com.am.models.ExperimentData;
import com.am.service.ExperimentDataService;
import com.am.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperimentDataServiceImpl implements ExperimentDataService {
    private final SaveService saveService;

    @Override
    public void save(String path, ExperimentData data) {
        saveService.save(path, data);
    }
}