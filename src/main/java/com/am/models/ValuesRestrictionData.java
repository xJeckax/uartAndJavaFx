package com.am.models;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton")
@NoArgsConstructor
public class ValuesRestrictionData {
    private final Map<Integer, Double> valuesRestriction = new ConcurrentHashMap<>();

    {
        for (int i = 0; i < 14; i++) {
            valuesRestriction.put(i, 0.0);
        }
    }

    public synchronized void addReadData(Map<Integer, Double> data) {
        valuesRestriction.clear();
        valuesRestriction.putAll(data);
    }

    public synchronized Map<Integer, Double> getValuesRestriction() {
        return valuesRestriction;
    }
}
