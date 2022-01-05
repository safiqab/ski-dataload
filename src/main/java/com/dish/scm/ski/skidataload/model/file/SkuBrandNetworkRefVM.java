package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuBrandNetworkRefVM {
    private String sku;
    private String network_code;
    private String brand_code;
    private String error_status;
    private String error_message;
}
