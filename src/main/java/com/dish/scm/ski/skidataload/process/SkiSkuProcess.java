package com.dish.scm.ski.skidataload.process;

import com.dish.scm.ski.skidataload.config.SkiReferenceNameConfig;
import com.dish.scm.ski.skidataload.connector.SkiApiClient;
import com.dish.scm.ski.skidataload.model.response.SkiReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SkiSkuProcess {
    private final SkiApiClient skiApiClient;
    private final SkiReferenceNameConfig skiReferenceNameConfig;
    private final DataValidation dataValidation;

    List<SkiReference> lstSkuTypeData = null;
    List<SkiReference> lstColorData = null;
    List<SkiReference> lstColorHexData = null;
    List<SkiReference> lstCountryOrginData = null;
    List<SkiReference> lstCompTypeData = null;
    List<SkiReference> lstPackageTypeData = null;
    List<SkiReference> lstUrlTypeData = null;
    List<SkiReference> lstSupplierData = null;
    List<SkiReference> lstChannelData = null;
    List<SkiReference> lstBrandCodeData = null;

    public SkiSkuProcess(SkiApiClient skiApiClient, SkiReferenceNameConfig skiReferenceNameConfig, DataValidation dataValidation) {
        this.skiApiClient = skiApiClient;
        this.skiReferenceNameConfig = skiReferenceNameConfig;
        this.dataValidation = dataValidation;
    }
}
