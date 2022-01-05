package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuPartOwnerVM {
    private String model_number;
    private String sku;
    private String part_owner;
    private String part_number;

    private String error_status;
    private String error_message;
}
