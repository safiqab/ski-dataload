package com.dish.scm.ski.skidataload.model.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"error_status","error_message"})
public class BrandCodeRefVM {
    private String id;
    private String refCollectionName;
    @JsonProperty("code")
    private String brand_code;
    @JsonProperty("name")
    private String brand_name;
    @JsonProperty("type")
    private String brand_type;
    @JsonProperty("description")
    private String brand_desc;
    private String error_status;
    private String error_message;

}
