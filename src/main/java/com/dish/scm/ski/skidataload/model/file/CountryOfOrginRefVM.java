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
public class CountryOfOrginRefVM {
    private String id;
    private String refCollectionName;
    @JsonProperty("countryCode")
    private String country_code;
    @JsonProperty("name")
    private String country_name;
    @JsonProperty("description")
    private String country_desc;
    private String error_status;
    private String error_message;

}

