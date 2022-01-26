package com.am.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"restrictions", "sensorsData", "timestamp"})
public class ExperimentData implements Serializable {
    private static final long serialVersionUID = -4181985100899233094L;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE, namespace = "sensorsData")
    private Map<Integer, Double> sensorsData = new HashMap<>();
    @JsonProperty(access = JsonProperty.Access.READ_WRITE, namespace = "restrictions")
    private Map<Integer, Double> restrictions = new HashMap<>();
    @JsonProperty(access = JsonProperty.Access.READ_WRITE, namespace = "timestamp")
    private Date timestamp;
}
