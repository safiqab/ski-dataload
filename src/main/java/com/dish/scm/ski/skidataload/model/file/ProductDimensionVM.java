package com.dish.scm.ski.skidataload.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDimensionVM {
    @JsonProperty("productWeight")
    private String product_weight;
    @JsonProperty("productWeightUom")
    private String product_weight_unit_of_meas;
    @JsonProperty("productHeight")
    private String product_height;
    @JsonProperty("productWidth")
    private String product_width;
    @JsonProperty("productLength")
    private String product_length;
    @JsonProperty("productSizeUom")
    private String product_size_unit_of_meas;
}
