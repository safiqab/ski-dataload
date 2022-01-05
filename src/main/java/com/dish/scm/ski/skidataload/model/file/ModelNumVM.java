package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelNumVM {
    private String modelNumber;
    private String equipType;
    private String modelNumber_PublicId;
    private String error_status;
    private String error_message;

}
