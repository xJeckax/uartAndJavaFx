package com.am.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExperimentData implements Serializable {
    private Map<Integer, Double> sensorsData = new HashMap<>();
    private Map<Integer, Double> restrictions = new HashMap<>();
    private Date timestamp;
}
