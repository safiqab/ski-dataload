package com.dish.scm.ski.skidataload.model.request;

import com.dish.scm.ski.skidataload.model.file.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelData {
    private String id;
    private String modelNumber;
    @JsonProperty("modelInfo")
    private ModelNumberVM modelNumberVM;
    @JsonProperty("modelConfig")
    private List<ModelConfigVM> lstModelConfigVM;
    @JsonProperty("modelTacs")
    private List<TacRangeVM> lstTacRangeVm;
    @JsonProperty("modelHazmat")
    private List<HazardousMaterialVM> lstHazardousMaterialVM;
    @JsonProperty("modelLanguage")
    private List<LanguageSupportVM> lstLanguageSupportVM;
    @JsonProperty("productDimensions")
    private ProductDimensionVM productDimensions;
    @JsonProperty("compatibleNetworks")
    private String[] networkCode;
    //private CompatibleNetworks compatibleNetworks;

}
