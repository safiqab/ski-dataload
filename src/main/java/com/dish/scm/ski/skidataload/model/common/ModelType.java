package com.dish.scm.ski.skidataload.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelType {
    private String modelNumber;
    private String equipType;

    public ModelType(ModelType modelType) {
    }
}
