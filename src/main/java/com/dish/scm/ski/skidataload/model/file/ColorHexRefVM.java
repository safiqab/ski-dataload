package com.dish.scm.ski.skidataload.model.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"error_status","error_message"})
public class ColorHexRefVM {
    private String id;
    private String refCollectionName;
    @JsonProperty("hexColor")
    private String color_hex;
    @JsonProperty("name")
    private String color_hex_name;
    @JsonProperty("description")
    private String color_hex_desc;
    private String error_status;
    private String error_message;
}
