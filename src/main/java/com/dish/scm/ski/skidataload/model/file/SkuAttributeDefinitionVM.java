package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuAttributeDefinitionVM {
    private List<ModelNumberVM> lstModelNumberVM;
    private List<TacRangeVM> lstTacRangeVM;
    private List<ModelConfigVM> lstModelConfigVM;
    private List<HazardousMaterialVM> lstHazardousMaterialVM;
    private List<LanguageSupportVM> lstLanguageSupportVM;


    private List<ProductDefinitionVM> lstProductDefinitionVM;
    private List<ProductSupplierVM> lstProductSupplierVM;
    private List<SalesChannelVM> lstSalesChannelVM;
    private List<ImageUrlVM> lstImageUrlVM;
    private List<ProductConfigVM> lstProductConfigVM;
    private List<SkuPartOwnerVM> lstSkuPartOwnerVM;
    private List<CompatibleCodeVM> lstCompactibleBrandsVM;
    private List<CompatibleCodeVM> lstCompactibleNetworkVM;
    private List<CompactibleSKUVM> lstSimCompactibleVM;
    private List<CompactibleSKUVM> lstAccessoryCompactibleVM;
    private List<SimConfigVM> lstSimConfigVM;

}
