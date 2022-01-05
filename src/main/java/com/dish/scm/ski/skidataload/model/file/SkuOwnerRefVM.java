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
public class SkuOwnerRefVM {
    private String id;
    private String refCollectionName;
    @JsonProperty("skuOwner")
    private String sku_owner;
    @JsonProperty("name")
    private String Name;
    @JsonProperty("description")
    private String Description;
    private String error_status;
    private String error_message;


}
