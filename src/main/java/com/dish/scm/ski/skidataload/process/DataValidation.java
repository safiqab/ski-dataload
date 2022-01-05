package com.dish.scm.ski.skidataload.process;

import com.dish.scm.ski.skidataload.config.ApiConfig;
import com.dish.scm.ski.skidataload.model.common.ModelType;
import com.dish.scm.ski.skidataload.model.file.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.dish.scm.ski.skidataload.model.common.OutputStatus;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DataValidation {
    private final ApiConfig apiConfig;

    @Autowired
    public DataValidation(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public boolean isFactDataExist(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstBrandcodeRefVM()) && skuAttributeReferenceVM.getLstBrandcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstBrandcodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUrlTypeRefVM()) && skuAttributeReferenceVM.getLstUrlTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUrlTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnlockStatusRefVM()) && skuAttributeReferenceVM.getLstUnlockStatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnlockStatusRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnitOfMeasRefVM()) && skuAttributeReferenceVM.getLstUnitOfMeasRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnitOfMeasRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuTypeRefVM()) && skuAttributeReferenceVM.getLstSkuTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSkuTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierRefVM()) && skuAttributeReferenceVM.getLstSupplierRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSupplierRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstTransactionTypeRefVM()) && skuAttributeReferenceVM.getLstTransactionTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstTransactionTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSubDeptIdRefVM()) && skuAttributeReferenceVM.getLstSubDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSubDeptIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimTypeRefVM()) && skuAttributeReferenceVM.getLstSimTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimFormRefVM()) && skuAttributeReferenceVM.getLstSimFormRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimFormRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstShipmentstatusRefVM()) && skuAttributeReferenceVM.getLstShipmentstatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstShipmentstatusRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSerialTypeRefVM()) && skuAttributeReferenceVM.getLstSerialTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSerialTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstProfileTypeRefVM()) && skuAttributeReferenceVM.getLstProfileTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstProfileTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstPackageTypeRefVM()) && skuAttributeReferenceVM.getLstPackageTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstPackageTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOwnershipcodeRefVM()) && skuAttributeReferenceVM.getLstOwnershipcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOwnershipcodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOsTypeRefVM()) && skuAttributeReferenceVM.getLstOsTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOsTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerRefVM()) && skuAttributeReferenceVM.getLstManufacturerRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstManufacturerRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLocationRefVM()) && skuAttributeReferenceVM.getLstLocationRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstLocationRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstFeatureTypeRefVM()) && skuAttributeReferenceVM.getLstFeatureTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstFeatureTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipTypeRefVM()) && skuAttributeReferenceVM.getLstEquipTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipsubTypeRefVM()) && skuAttributeReferenceVM.getLstEquipsubTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipsubTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipSubCatRefVM()) && skuAttributeReferenceVM.getLstEquipSubCatRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipSubCatRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipIdRefVM()) && skuAttributeReferenceVM.getLstEquipIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDispositioncodeRefVM()) && skuAttributeReferenceVM.getLstDispositioncodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDispositioncodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDeptIdRefVM()) && skuAttributeReferenceVM.getLstDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDeptIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstCountryOfOrginRefVM()) && skuAttributeReferenceVM.getLstCountryOfOrginRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstCountryOfOrginRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstConfigTypeRefVM()) && skuAttributeReferenceVM.getLstConfigTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstConfigTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstComponentTypeRefVM()) && skuAttributeReferenceVM.getLstComponentTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstComponentTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorRefVM()) && skuAttributeReferenceVM.getLstColorRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstColorRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstChannelRefVM()) && skuAttributeReferenceVM.getLstChannelRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstChannelRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerConfigRefVM()) && skuAttributeReferenceVM.getLstManufacturerConfigRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierConfigRefVM()) && skuAttributeReferenceVM.getLstSupplierConfigRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstNetworkCodeRefVM()) && skuAttributeReferenceVM.getLstNetworkCodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstNetworkCodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuOwnerRefVM()) && skuAttributeReferenceVM.getLstSkuOwnerRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSkuOwnerRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLangcodeRefVM()) && skuAttributeReferenceVM.getLstLangcodeRefVM().size()>0)
            return skuAttributeReferenceVM.getLstLangcodeRefVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status()));

        return false;
    }

    public boolean isModelDataExist(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstModelConfigVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size() > 0)
            return skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status()));

        return false;
    }


    public List<String> getAllModelNumbersFromModels(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        log.info("getAllModelNumbers from all tabs");
        List<String>  lstModelNumbers = new ArrayList<>();

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelNumberVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> m.getModel_number()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstTacRangeVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> m.getModel_number()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelConfigVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> m.getModel_number()).distinct().collect(Collectors.toList()));

        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> m.getModel_number()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> m.getModel_number()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(lstModelNumbers) && lstModelNumbers.size()>0) {
            lstModelNumbers = lstModelNumbers.stream().distinct().collect(Collectors.toList());
        }
        return lstModelNumbers;
    }


    public boolean hasErrorInModel(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(e-> e.getModel_number().equalsIgnoreCase(modelNum) && !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(e-> e.getModel_number().equalsIgnoreCase(modelNum) && StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstModelConfigVM().stream().anyMatch(e-> e.getModel_number().equalsIgnoreCase(modelNum) && !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(e-> e.getModel_number().equalsIgnoreCase(modelNum) && StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(e-> e.getModel_number().equalsIgnoreCase(modelNum) && !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(e-> e.getModel_number().equalsIgnoreCase(modelNum) && StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .anyMatch(e-> e.getModel_number().equalsIgnoreCase(modelNum) && !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream().filter(e-> e.getModel_number().equalsIgnoreCase(modelNum) && StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .anyMatch(e -> e.getModel_number().equalsIgnoreCase(modelNum) && !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(e-> e.getModel_number().equalsIgnoreCase(modelNum) && StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        return false;
    }


    /*
    public List<ModelType> getAllModelNumbers(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        log.info("getAllModelNumbers from all tabs");
        List<ModelType>  lstModelNumbers = new ArrayList<>();

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelNumberVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstTacRangeVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));

        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelConfigVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));

        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSkuPartOwnerVM()) && skuAttributeDefinitionVM.getLstSkuPartOwnerVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstSkuPartOwnerVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstModelNumbers) && lstModelNumbers.size()>0) {
            List<ModelType>  lstModelNumbersFinal = new ArrayList<>();
            lstModelNumbersFinal.addAll(lstModelNumbers.stream().filter(distinctByKey(ps->ps.getModelNumber() + ps.getEquipType())).collect(Collectors.toList()));
            return lstModelNumbersFinal;
        }
        return lstModelNumbers;
    }


    public List<String> getAllSkus(List<SalesChannelVM> lstSalesChannelVM, List<LanguageSupportVM> lstLanguageSupportVM,
                                   List<ProductSupplierVM> lstProductSupplierVM, List<ImageUrlVM> lstImageUrlVM,
                                   List<ProductConfigVM> lstProductConfigVM, List<HazardousMaterialVM> lstHazardousMaterialVM,
                                   List<SkuPartOwnerVM> lstSkuPartOwnerVM) {
        log.info("getAllSkus from all tabs");
        List<String>  lstSkus = new ArrayList<>();

        if (!StringUtils.isEmpty(lstHazardousMaterialVM) && lstHazardousMaterialVM.size()>0) {
            lstSkus.addAll(lstHazardousMaterialVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(HazardousMaterialVM::getSku).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(lstSalesChannelVM) && lstSalesChannelVM.size()>0) {
            lstSkus.addAll(lstSalesChannelVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(SalesChannelVM::getSku).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstProductConfigVM) && lstProductConfigVM.size()>0) {
            lstSkus.addAll(lstProductConfigVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(ProductConfigVM::getSku).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(lstProductSupplierVM) && lstProductSupplierVM.size()>0) {
            lstSkus.addAll(lstProductSupplierVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(ProductSupplierVM::getSku).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstSkuPartOwnerVM) && lstSkuPartOwnerVM.size()>0) {
            lstSkus.addAll(lstSkuPartOwnerVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(SkuPartOwnerVM::getSku).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstLanguageSupportVM) && lstLanguageSupportVM.size()>0) {
            lstSkus.addAll(lstLanguageSupportVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(LanguageSupportVM::getSku).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstImageUrlVM) && lstImageUrlVM.size()>0) {
            lstSkus.addAll(lstImageUrlVM.stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(ImageUrlVM::getSku).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(lstSkus) && lstSkus.size()>0) {
            lstSkus = lstSkus.stream().distinct().collect(Collectors.toList());
        }
        return lstSkus;
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> uniqueMap = new ConcurrentHashMap<>();
        return t -> uniqueMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }



    public ModelNumberVM getModelsFromWB(SkuAttributeDefinitionVM skuAttributeDefinitionVM, ModelType modelType, ModelNumberData mnData) {
        log.info("getModelsFromWB for->{}",modelType.getModelNumber());
        ModelNumberVM modelNumberVMTemp = null;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM())
                && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0 ) {

            modelNumberVMTemp = skuAttributeDefinitionVM.getLstModelNumberVM().stream()
                    .filter(x->x.getModel_number().equals(modelType.getModelNumber()) && x.getEquip_type().equals(modelType.getEquipType()))
                    .findFirst().orElse(null);
        }
        if (StringUtils.isEmpty(modelNumberVMTemp)) {
            if (StringUtils.isEmpty(mnData)) {
                return null;
            }
            else {
                return ModelNumberVM.builder().intent("Modify").model_number_publicid(mnData.getModel_number_publicid())
                        .model_number(mnData.getModel_number())
                        .equip_type(modelType.getEquipType()).build();
            }
        }
        else {
            if (StringUtils.isEmpty(mnData)) {
                modelNumberVMTemp.setIntent("New");
                modelNumberVMTemp.setModel_number_publicid(UUID.randomUUID().toString());
                return modelNumberVMTemp;
            }
            else {
                modelNumberVMTemp.setIntent("Modify");
                modelNumberVMTemp.setModel_number_publicid(mnData.getModel_number_publicid());
                return modelNumberVMTemp;
            }
        }
    }


    public SkuAttributeDefinitionVM getAllSkuForModel(SkuAttributeDefinitionVM skuAttributeDefinitionVM,
                                                      ModelType modelType) {
        SkuAttributeDefinitionVM skuTemp = new SkuAttributeDefinitionVM();

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM())
                && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0 ) {

            skuTemp.setLstModelNumberVM(skuAttributeDefinitionVM.getLstModelNumberVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM())
                && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0 ) {

            skuTemp.setLstProductDefinitionVM(skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM())
                && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0 ) {

            skuTemp.setLstTacRangeVM(skuAttributeDefinitionVM.getLstTacRangeVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM())
                && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0 ) {

            skuTemp.setLstModelConfigVM(skuAttributeDefinitionVM.getLstModelConfigVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM())
                && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0 ) {

            skuTemp.setLstProductSupplierVM(skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM())
                && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0 ) {

            skuTemp.setLstSalesChannelVM(skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM())
                && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0 ) {

            skuTemp.setLstImageUrlVM(skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM())
                && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0 ) {

            skuTemp.setLstLanguageSupportVM(skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM())
                && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0 ) {
            skuTemp.setLstProductConfigVM(skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM())
                && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0 ) {
            skuTemp.setLstHazardousMaterialVM(skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSkuPartOwnerVM())
                && skuAttributeDefinitionVM.getLstSkuPartOwnerVM().size()>0 ) {
            skuTemp.setLstSkuPartOwnerVM(skuAttributeDefinitionVM.getLstSkuPartOwnerVM().stream()
                    .filter(x->x.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && x.getEquip_type().equalsIgnoreCase(modelType.getEquipType()))
                    .collect(Collectors.toList()));
        }
        return skuTemp;
    }

    public boolean isFactDataExist(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstBrandcodeRefVM()) && skuAttributeReferenceVM.getLstBrandcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstBrandcodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUrlTypeRefVM()) && skuAttributeReferenceVM.getLstUrlTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUrlTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnlockStatusRefVM()) && skuAttributeReferenceVM.getLstUnlockStatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnlockStatusRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnitOfMeasRefVM()) && skuAttributeReferenceVM.getLstUnitOfMeasRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnitOfMeasRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuTypeRefVM()) && skuAttributeReferenceVM.getLstSkuTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSkuTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierRefVM()) && skuAttributeReferenceVM.getLstSupplierRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSupplierRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstTransactionTypeRefVM()) && skuAttributeReferenceVM.getLstTransactionTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstTransactionTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSubDeptIdRefVM()) && skuAttributeReferenceVM.getLstSubDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSubDeptIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimTypeRefVM()) && skuAttributeReferenceVM.getLstSimTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimFormRefVM()) && skuAttributeReferenceVM.getLstSimFormRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimFormRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstShipmentstatusRefVM()) && skuAttributeReferenceVM.getLstShipmentstatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstShipmentstatusRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSerialTypeRefVM()) && skuAttributeReferenceVM.getLstSerialTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSerialTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstProfileTypeRefVM()) && skuAttributeReferenceVM.getLstProfileTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstProfileTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstPackageTypeRefVM()) && skuAttributeReferenceVM.getLstPackageTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstPackageTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOwnershipcodeRefVM()) && skuAttributeReferenceVM.getLstOwnershipcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOwnershipcodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOsTypeRefVM()) && skuAttributeReferenceVM.getLstOsTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOsTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerRefVM()) && skuAttributeReferenceVM.getLstManufacturerRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstManufacturerRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLocationRefVM()) && skuAttributeReferenceVM.getLstLocationRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstLocationRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstFeatureTypeRefVM()) && skuAttributeReferenceVM.getLstFeatureTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstFeatureTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipTypeRefVM()) && skuAttributeReferenceVM.getLstEquipTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipsubTypeRefVM()) && skuAttributeReferenceVM.getLstEquipsubTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipsubTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipSubCatRefVM()) && skuAttributeReferenceVM.getLstEquipSubCatRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipSubCatRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipIdRefVM()) && skuAttributeReferenceVM.getLstEquipIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDispositioncodeRefVM()) && skuAttributeReferenceVM.getLstDispositioncodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDispositioncodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDeptIdRefVM()) && skuAttributeReferenceVM.getLstDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDeptIdRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstCountryOfOrginRefVM()) && skuAttributeReferenceVM.getLstCountryOfOrginRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstCountryOfOrginRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstConfigTypeRefVM()) && skuAttributeReferenceVM.getLstConfigTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstConfigTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstComponentTypeRefVM()) && skuAttributeReferenceVM.getLstComponentTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstComponentTypeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorRefVM()) && skuAttributeReferenceVM.getLstColorRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstColorRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstChannelRefVM()) && skuAttributeReferenceVM.getLstChannelRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstChannelRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerConfigRefVM()) && skuAttributeReferenceVM.getLstManufacturerConfigRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierConfigRefVM()) && skuAttributeReferenceVM.getLstSupplierConfigRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstNetworkCodeRefVM()) && skuAttributeReferenceVM.getLstNetworkCodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstNetworkCodeRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuOwnerRefVM()) && skuAttributeReferenceVM.getLstSkuOwnerRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSkuOwnerRefVM().stream().anyMatch(x->StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLangcodeRefVM()) && skuAttributeReferenceVM.getLstLangcodeRefVM().size()>0)
            return skuAttributeReferenceVM.getLstLangcodeRefVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status()));

        return false;
    }

    public OutputStatus setOutputStatus(List<ProductDefinitionVM> lstTmpProductDefinitionVM, SkuAttributeDefinitionVM skuAttributeDefinitionVM) {

        lstTmpProductDefinitionVM.forEach(p1-> {

            skuAttributeDefinitionVM.getLstProductDefinitionVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                    && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()) )
                    .forEach(res-> {
                        res.setError_status(p1.getError_status());
                        res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                    });

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
                skuAttributeDefinitionVM.getLstImageUrlVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {

                skuAttributeDefinitionVM.getLstProductSupplierVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {

                skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
                skuAttributeDefinitionVM.getLstSalesChannelVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
                skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
                skuAttributeDefinitionVM.getLstProductConfigVM().stream().filter(r1-> r1.getSku().equalsIgnoreCase(p1.getSku())
                        && r1.getModel_number().equalsIgnoreCase(p1.getModel_number()) && r1.getEquip_type().equalsIgnoreCase(p1.getEquip_type()))
                        .forEach(res-> {
                            res.setError_status(p1.getError_status());
                            res.setError_message((StringUtils.isEmpty(res.getError_message())?  "" :res.getError_message()) +p1.getError_message());
                        });
            }

        });


        if (lstTmpProductDefinitionVM.stream().anyMatch(k1-> !StringUtils.isEmpty(k1.getError_status()) && k1.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
        {
            if (lstTmpProductDefinitionVM.stream().anyMatch(k1->k1.getError_status().equalsIgnoreCase(apiConfig.getImport_status_success()))) {
                if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
                    skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(f1->f1.getModel_number()
                            .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                            .forEach(m1-> {
                                m1.setError_status("Partial Failure");
                            });
                }
                if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
                    skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(f1->f1.getModel_number()
                            .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                            .forEach(m1-> {
                                m1.setError_status("Partial Failure");
                            });
                }
                return OutputStatus.builder().errorStatus("Partial Failure").build();
            }
            else {
                if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
                    skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(f1->f1.getModel_number()
                            .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                            .forEach(m1-> {
                                m1.setError_status(apiConfig.getImport_status_failed());
                            });
                }
                if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
                    skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(f1->f1.getModel_number()
                            .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                            .forEach(m1-> {
                                m1.setError_status(apiConfig.getImport_status_failed());
                            });
                }
                return OutputStatus.builder().errorStatus(apiConfig.getImport_status_failed()).build();
            }
        }
        else {
            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
                skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(f1->f1.getModel_number()
                        .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                        .forEach(m1-> m1.setError_status(apiConfig.getImport_status_success()));
            }
            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
                skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(f1->f1.getModel_number()
                        .equalsIgnoreCase(lstTmpProductDefinitionVM.stream().map(ProductDefinitionVM::getModel_number).findFirst().get()))
                        .forEach(m1-> m1.setError_status(apiConfig.getImport_status_success()));
            }
            return OutputStatus.builder().errorStatus(apiConfig.getImport_status_success()).build();
        }
    }

    public void setOutputStatus(OutputStatus rtnStatus,ModelType modelType, SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {

            skuAttributeDefinitionVM.getLstTacRangeVM().stream().
                    filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {

            skuAttributeDefinitionVM.getLstModelConfigVM().stream().
                    filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .filter(e->  e.getModel_number().equalsIgnoreCase(modelType.getModelNumber()) && e.getEquip_type().equalsIgnoreCase(modelType.getEquipType()) )
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
    }

    public void setOutputStatus(OutputStatus rtnStatus, SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {

            skuAttributeDefinitionVM.getLstTacRangeVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .forEach(m-> {
                        m.setError_status(rtnStatus.getErrorStatus());
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message()) +rtnStatus.getErrorMessage());
                    });
        }
    }





    public boolean isEntityDataExist(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductDefinitionVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductSupplierVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstSalesChannelVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstImageUrlVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size() > 0)
            return skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status()));

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductConfigVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstModelConfigVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSkuPartOwnerVM()) && skuAttributeDefinitionVM.getLstSkuPartOwnerVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstSkuPartOwnerVM().stream().anyMatch(x -> StringUtils.isEmpty(x.getError_status())))
                return true;
        return false;
    }

    public String getFactPublic(List<FactReferences> lstData, String name) {
        if (StringUtils.isEmpty(lstData) || lstData.size()<=0 )
            return null;
        Optional<String> strPubId = lstData.stream().filter(x->x.getName().equalsIgnoreCase(name))
                .map(FactReferences::getPublicID).findFirst();
        return strPubId.orElse(null);
    }

    public boolean isErrorDataExist(SkuAttributeReferenceVM skuAttributeReferenceVM, SkuAttributeDefinitionVM skuAttributeDefinitionVM, List<ModelNumVM> lstModelName,
                                    List<ModelNumVM> lstModelNameSA, List<ModelNumVM> lstModelNameApprove,
                                    List<ModelNumVM> lstModelNameStage) {

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstBrandcodeRefVM()) && skuAttributeReferenceVM.getLstBrandcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstBrandcodeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUrlTypeRefVM()) && skuAttributeReferenceVM.getLstUrlTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUrlTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnlockStatusRefVM()) && skuAttributeReferenceVM.getLstUnlockStatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnlockStatusRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnitOfMeasRefVM()) && skuAttributeReferenceVM.getLstUnitOfMeasRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstUnitOfMeasRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuTypeRefVM()) && skuAttributeReferenceVM.getLstSkuTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSkuTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierRefVM()) && skuAttributeReferenceVM.getLstSupplierRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSupplierRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstTransactionTypeRefVM()) && skuAttributeReferenceVM.getLstTransactionTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstTransactionTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSubDeptIdRefVM()) && skuAttributeReferenceVM.getLstSubDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSubDeptIdRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimTypeRefVM()) && skuAttributeReferenceVM.getLstSimTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimFormRefVM()) && skuAttributeReferenceVM.getLstSimFormRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSimFormRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstShipmentstatusRefVM()) && skuAttributeReferenceVM.getLstShipmentstatusRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstShipmentstatusRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSerialTypeRefVM()) && skuAttributeReferenceVM.getLstSerialTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstSerialTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstProfileTypeRefVM()) && skuAttributeReferenceVM.getLstProfileTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstProfileTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstPackageTypeRefVM()) && skuAttributeReferenceVM.getLstPackageTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstPackageTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOwnershipcodeRefVM()) && skuAttributeReferenceVM.getLstOwnershipcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOwnershipcodeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOsTypeRefVM()) && skuAttributeReferenceVM.getLstOsTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstOsTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerRefVM()) && skuAttributeReferenceVM.getLstManufacturerRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstManufacturerRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLocationRefVM()) && skuAttributeReferenceVM.getLstLocationRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstLocationRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstFeatureTypeRefVM()) && skuAttributeReferenceVM.getLstFeatureTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstFeatureTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipTypeRefVM()) && skuAttributeReferenceVM.getLstEquipTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipsubTypeRefVM()) && skuAttributeReferenceVM.getLstEquipsubTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipsubTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipSubCatRefVM()) && skuAttributeReferenceVM.getLstEquipSubCatRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipSubCatRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipIdRefVM()) && skuAttributeReferenceVM.getLstEquipIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstEquipIdRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDispositioncodeRefVM()) && skuAttributeReferenceVM.getLstDispositioncodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDispositioncodeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDeptIdRefVM()) && skuAttributeReferenceVM.getLstDeptIdRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstDeptIdRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstCountryOfOrginRefVM()) && skuAttributeReferenceVM.getLstCountryOfOrginRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstCountryOfOrginRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstConfigTypeRefVM()) && skuAttributeReferenceVM.getLstConfigTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstConfigTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstComponentTypeRefVM()) && skuAttributeReferenceVM.getLstComponentTypeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstComponentTypeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorRefVM()) && skuAttributeReferenceVM.getLstColorRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstColorRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstChannelRefVM()) && skuAttributeReferenceVM.getLstChannelRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstChannelRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLangcodeRefVM()) && skuAttributeReferenceVM.getLstLangcodeRefVM().size()>0)
            if (skuAttributeReferenceVM.getLstLangcodeRefVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductDefinitionVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductSupplierVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstSalesChannelVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstImageUrlVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size() > 0)
            if (skuAttributeDefinitionVM.getLstProductConfigVM().stream().anyMatch(x->!StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed())))
                return true;
        if (!StringUtils.isEmpty(lstModelName) && lstModelName.size()>0)
            return lstModelName.stream().anyMatch(x -> !StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed()));

        if (!StringUtils.isEmpty(lstModelNameSA) && lstModelNameSA.size()>0)
            return lstModelNameSA.stream().anyMatch(x -> !StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed()));

        if (!StringUtils.isEmpty(lstModelNameApprove) && lstModelNameApprove.size()>0)
            return lstModelNameApprove.stream().anyMatch(x -> !StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed()));

        if (!StringUtils.isEmpty(lstModelNameStage) && lstModelNameStage.size()>0)
            return lstModelNameStage.stream().anyMatch(x -> !StringUtils.isEmpty(x.getError_status()) && x.getError_status().equalsIgnoreCase(apiConfig.getImport_status_failed()));
        return false;
    }

    public boolean hasErrorInEntity(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstModelConfigVM().stream().anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstProductDefinitionVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstImageUrlVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }

        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstProductSupplierVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstSalesChannelVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .anyMatch(e -> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()))) {
                skuAttributeDefinitionVM.getLstProductConfigVM().stream().filter(e-> StringUtils.isEmpty(e.getError_status()))
                        .forEach(err->err.setError_status(apiConfig.getImport_status_failed()));
                return true;
            }
        }
        return false;
    }

    public boolean hasSuccessData(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstTacRangeVM().stream().anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;

        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            if (skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .anyMatch(e-> !StringUtils.isEmpty(e.getError_status()) && e.getError_status().equals(apiConfig.getImport_status_success()) ))
                return true;
        }
        return false;
    }

    public String setErrorMessage(String oldErrorMsg,String newErrorMsg) {
        return StringUtils.isEmpty(newErrorMsg) ? oldErrorMsg : StringUtils.isEmpty(oldErrorMsg) ? newErrorMsg : oldErrorMsg + newErrorMsg;
    }

    public void setErrorInEntity(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String errMsg) {

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            skuAttributeDefinitionVM.getLstModelNumberVM().forEach(x-> {
                if (StringUtils.isEmpty(x.getError_message())) {
                    x.setError_status(apiConfig.getImport_status_failed());
                }
                x.setError_message(setErrorMessage(x.getError_message(),errMsg));
            });
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            skuAttributeDefinitionVM.getLstTacRangeVM().forEach(x-> {
                if (StringUtils.isEmpty(x.getError_message())) {
                    x.setError_status(apiConfig.getImport_status_failed());
                }
                x.setError_message(setErrorMessage(x.getError_message(),errMsg));
            });
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .forEach(x-> {
                        if (StringUtils.isEmpty(x.getError_message())) {
                            x.setError_status(apiConfig.getImport_status_failed());
                        }
                        x.setError_message(setErrorMessage(x.getError_message(),errMsg));
                    });
        }
    }

    */


    /*
    public List<ModelType> getAllModelNumbersForModels(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        log.info("getAllModelNumbers from all tabs");
        List<ModelType>  lstModelNumbers = new ArrayList<>();

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelNumberVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstTacRangeVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));

        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstModelConfigVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));

        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductDefinitionVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstSalesChannelVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstImageUrlVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstLanguageSupportVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductSupplierVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }

        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstProductConfigVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSkuPartOwnerVM()) && skuAttributeDefinitionVM.getLstSkuPartOwnerVM().size()>0) {
            lstModelNumbers.addAll(skuAttributeDefinitionVM.getLstSkuPartOwnerVM().stream()
                    .filter(xx->StringUtils.isEmpty(xx.getError_status()))
                    .map(m-> ModelType.builder().modelNumber(m.getModel_number()).equipType(m.getEquip_type()).build()).distinct().collect(Collectors.toList()));
        }
        if (!StringUtils.isEmpty(lstModelNumbers) && lstModelNumbers.size()>0) {
            List<ModelType>  lstModelNumbersFinal = new ArrayList<>();
            lstModelNumbersFinal.addAll(lstModelNumbers.stream().filter(distinctByKey(ps->ps.getModelNumber() + ps.getEquipType())).collect(Collectors.toList()));
            return lstModelNumbersFinal;
        }
        return lstModelNumbers;
    }
    */
}
