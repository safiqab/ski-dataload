package com.dish.scm.ski.skidataload.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompatibleNetworks {
    @JsonProperty("networkCode")
    private String[] networkCode;
}
