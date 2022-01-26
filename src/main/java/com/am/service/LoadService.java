package com.am.service;

import com.am.models.ExperimentData;

import java.util.List;

public interface LoadService {
    List<ExperimentData> load(String path);
}
