package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimConfigVM {
    private String model_number;
    private String sku;
    private String supplier_code;
    private String config_type;
    private String config_value;
}
