package com.dish.scm.ski.skidataload.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputStatus {
    private String errorStatus;
    private String errorMessage;
}
