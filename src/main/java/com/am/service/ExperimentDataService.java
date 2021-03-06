package com.am.service;

import com.am.models.ExperimentData;

import java.util.List;

public interface ExperimentDataService {
    void save(String path, ExperimentData data);
    List<ExperimentData> load(String path);
}
