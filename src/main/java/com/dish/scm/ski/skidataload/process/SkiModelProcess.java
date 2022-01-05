package com.dish.scm.ski.skidataload.process;

import com.dish.scm.ski.skidataload.config.SkiReferenceNameConfig;
import com.dish.scm.ski.skidataload.connector.SkiApiClient;
import com.dish.scm.ski.skidataload.model.file.*;
import com.dish.scm.ski.skidataload.model.request.ModelData;
import com.dish.scm.ski.skidataload.model.response.SkiReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SkiModelProcess {
    private final SkiApiClient skiApiClient;
    private final SkiReferenceNameConfig skiReferenceNameConfig;
    private final DataValidation dataValidation;

    List<SkiReference> lstManfData = null;
    List<SkiReference> lstOsData = null;
    List<SkiReference> lstSerialTypeData = null;
    List<SkiReference> lstEquipIdData = null;
    List<SkiReference> lstEquipTypeData = null;
    List<SkiReference> lstEquipSubData = null;
    List<SkiReference> lstEquipSubCatData = null;
    List<SkiReference> lstConfigTypeData = null;
    List<SkiReference> lstLangCodeData = null;
    List<SkiReference> lstUnitOfMeas = null;
    List<SkiReference> lstNetworkCode = null;

    @Autowired
    public SkiModelProcess(SkiApiClient skiApiClient, SkiReferenceNameConfig skiReferenceNameConfig, DataValidation dataValidation) {
        this.skiApiClient = skiApiClient;
        this.skiReferenceNameConfig = skiReferenceNameConfig;
        this.dataValidation = dataValidation;
    }

    public void insertModel(SkuAttributeDefinitionVM skuAttributeDefinitionVM) {
        List<String> lstModelNumbers = dataValidation.getAllModelNumbersFromModels(skuAttributeDefinitionVM);
        if (!StringUtils.isEmpty(lstModelNumbers) && lstModelNumbers.size()>0) {
            lstModelNumbers.forEach(m1-> {
                validateReference(skuAttributeDefinitionVM,m1);
                if (!this.dataValidation.hasErrorInModel(skuAttributeDefinitionVM,m1)) {
                    ModelData existData = this.skiApiClient.getModelDataApi(m1);
                    if (StringUtils.isEmpty(existData)) {
                        Optional<ModelNumberVM> modelNumNew = skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(r->r.getModel_number().equalsIgnoreCase(m1))
                                .findFirst();
                        List<TacRangeVM> lstTC = skuAttributeDefinitionVM.getLstTacRangeVM().stream().filter(r->r.getModel_number().equalsIgnoreCase(m1)).collect(Collectors.toList());
                        List<HazardousMaterialVM> lstHM = skuAttributeDefinitionVM.getLstHazardousMaterialVM().stream().filter(r->r.getModel_number().equalsIgnoreCase(m1)).collect(Collectors.toList());
                        List<LanguageSupportVM> lstLS = skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(r->r.getModel_number().equalsIgnoreCase(m1)).collect(Collectors.toList());
                        List<ModelConfigVM> lstMC = skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(r->r.getModel_number().equalsIgnoreCase(m1)).collect(Collectors.toList());
                        if (modelNumNew.isPresent()) {
                            String rtnStatus = this.skiApiClient.addModelDataApi(
                                    ModelData.builder().modelNumberVM(modelNumNew.get())
                                            .lstTacRangeVm(StringUtils.isEmpty(lstTC)?null:lstTC)
                                            .lstHazardousMaterialVM(StringUtils.isEmpty(lstHM)?null:lstHM)
                                            .lstLanguageSupportVM(StringUtils.isEmpty(lstLS)?null:lstLS)
                                            .productDimensions(StringUtils.isEmpty(modelNumNew.get().getProductDimensionVM())
                                                    ? null : modelNumNew.get().getProductDimensionVM())
                                            .networkCode(StringUtils.isEmpty(modelNumNew.get().getNetworkCode()) ? null:
                                                    modelNumNew.get().getNetworkCode())
                                            .lstModelConfigVM(StringUtils.isEmpty(lstMC)?null:lstMC)
                                            .build(),"New");
                        }
                    }
                }
            });
        }
    }


    public void validateReference(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {
        checkManfCode(skuAttributeDefinitionVM,modelNum);
        checkOsType(skuAttributeDefinitionVM,modelNum);
        checkSerialType(skuAttributeDefinitionVM,modelNum);
        checkEquipmentId(skuAttributeDefinitionVM,modelNum);
        checkEquipmentType(skuAttributeDefinitionVM,modelNum);
        checkEquipmentSubType(skuAttributeDefinitionVM,modelNum);
        checkEquipmentSubCategory(skuAttributeDefinitionVM,modelNum);
        checkModelConfigType(skuAttributeDefinitionVM,modelNum);
        checkModelLanguage(skuAttributeDefinitionVM,modelNum);
        checkUOM(skuAttributeDefinitionVM,modelNum);
        checkNetworkCode(skuAttributeDefinitionVM,modelNum);
    }



    public void checkManfCode(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getManf_code())).map(y->y.getManf_code()).count() > 0) {

            if (StringUtils.isEmpty(lstManfData))
                lstManfData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getMANUFACTURER_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstManfData)) {
                                    if (StringUtils.isEmpty(m.getManf_code())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find manufacturer code-->"+m.getManf_code());
                                    }
                                }
                                else {
                                    if (!lstManfData.stream().anyMatch(r->r.getCode().equalsIgnoreCase(m.getManf_code()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find manufacturer code-->"+m.getManf_code());
                                    }
                                }
                            }
                    );

        }
    }

    public void checkOsType(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getOs_type())).map(y->y.getOs_type()).count() > 0) {

            if (StringUtils.isEmpty(lstOsData))
                lstOsData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getOS_TYPE());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstOsData)) {
                                    if (StringUtils.isEmpty(m.getOs_type())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find os type-->"+m.getOs_type());
                                    }
                                }
                                else {
                                    if (!lstOsData.stream().anyMatch(r->r.getType().equalsIgnoreCase(m.getOs_type()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find os type-->"+m.getOs_type());
                                    }
                                }
                            }
                    );

        }
    }


    public void checkSerialType(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getSerial_type())).map(y->y.getSerial_type()).count() > 0) {

            if (StringUtils.isEmpty(lstSerialTypeData))
                lstSerialTypeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSERIAL_TYPE_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstSerialTypeData)) {
                                    if (StringUtils.isEmpty(m.getSerial_type())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getSerial_type());
                                    }
                                }
                                else {
                                    if (!lstSerialTypeData.stream().anyMatch(r->r.getType().equalsIgnoreCase(m.getSerial_type()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getSerial_type());
                                    }
                                }
                            }
                    );

        }
    }

    public void checkEquipmentId(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getEquip_id())).map(y->y.getEquip_id()).count() > 0) {

            if (StringUtils.isEmpty(lstEquipIdData))
                lstEquipIdData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_ID_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstEquipIdData)) {
                                    if (StringUtils.isEmpty(m.getEquip_id())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getEquip_id());
                                    }
                                }
                                else {
                                    if (!lstSerialTypeData.stream().anyMatch(r->r.getCode().equalsIgnoreCase(m.getEquip_id()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getEquip_id());
                                    }
                                }
                            }
                    );
        }
    }

    public void checkEquipmentType(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getEquip_type())).map(y->y.getEquip_type()).count() > 0) {

            if (StringUtils.isEmpty(lstEquipTypeData))
                lstEquipTypeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_TYPE_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstEquipTypeData)) {
                                    if (StringUtils.isEmpty(m.getEquip_type())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getEquip_type());
                                    }
                                }
                                else {
                                    if (!lstEquipTypeData.stream().anyMatch(r->r.getType().equalsIgnoreCase(m.getEquip_type()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find serial type-->"+m.getEquip_type());
                                    }
                                }
                            }
                    );

        }
    }

    public void checkEquipmentSubType(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getEquip_sub_type())).map(y->y.getEquip_sub_type()).count() > 0) {

            if (StringUtils.isEmpty(lstEquipSubData))
                lstEquipSubData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_SUB_TYPE_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstEquipSubData)) {
                                    if (StringUtils.isEmpty(m.getEquip_sub_type())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Equip sub type type-->"+m.getEquip_sub_type());
                                    }
                                }
                                else {
                                    if (!lstEquipSubData.stream().anyMatch(r->r.getType().equalsIgnoreCase(m.getEquip_sub_type()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Equip sub type-->"+m.getEquip_sub_type());
                                    }
                                }
                            }
                    );

        }
    }

    public void checkEquipmentSubCategory(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getEquip_sub_cat())).map(y->y.getEquip_sub_cat()).count() > 0) {

            if (StringUtils.isEmpty(lstEquipSubCatData))
                lstEquipSubCatData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_SUB_CAT_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstEquipSubCatData)) {
                                    if (StringUtils.isEmpty(m.getEquip_sub_cat())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Equipment Sub Category-->"+m.getEquip_sub_cat());
                                    }
                                }
                                else {
                                    if (!lstEquipSubCatData.stream().anyMatch(r->r.getEquipSubCat().equalsIgnoreCase(m.getEquip_sub_cat()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Equipment Sub Category-->"+m.getEquip_sub_cat());
                                    }
                                }
                            }
                    );
        }
    }


    public void checkModelConfigType(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getConfigType())).map(y->y.getConfigType()).count() > 0) {

            if (StringUtils.isEmpty(lstConfigTypeData))
                lstConfigTypeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCONFIG_TYPE_REF());

            skuAttributeDefinitionVM.getLstModelConfigVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstConfigTypeData)) {
                                    if (StringUtils.isEmpty(m.getConfigType())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find config type-->"+m.getConfigType());
                                    }
                                }
                                else {

                                    if (!lstConfigTypeData.stream().anyMatch(r->r.getType().equalsIgnoreCase(m.getConfigType()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find config type-->"+m.getConfigType());
                                    }
                                }
                            }
                    );
        }
    }

    public void checkModelLanguage(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getLanguage_code())).map(y->y.getLanguage_code()).count() > 0) {

            if (StringUtils.isEmpty(lstLangCodeData))
                lstLangCodeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getLANG_CODE_REF());

            skuAttributeDefinitionVM.getLstLanguageSupportVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                    .forEach(m-> {
                                if (StringUtils.isEmpty(lstLangCodeData)) {
                                    if (StringUtils.isEmpty(m.getLanguage_code())) {
                                        m.setError_status("Failed");
                                    }
                                    else {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Language Code-->"+m.getLanguage_code());
                                    }
                                }
                                else {
                                    if (!lstLangCodeData.stream().anyMatch(r->r.getCode().equalsIgnoreCase(m.getLanguage_code()))) {
                                        m.setError_status("Failed");
                                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Language Code-->"+m.getLanguage_code());
                                    }
                                }
                            }
                    );
        }
    }


    public void checkUOM(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        if (skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
             && !StringUtils.isEmpty(x.getProductDimensionVM())
            && (!StringUtils.isEmpty(x.getProductDimensionVM().getProduct_weight_unit_of_meas()) ||
                !StringUtils.isEmpty(x.getProductDimensionVM().getProduct_size_unit_of_meas()
                ))).count()>0) {

            if (StringUtils.isEmpty(lstUnitOfMeas))
                lstUnitOfMeas = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getUNIT_OF_MEAS_REF());

            skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                    && !StringUtils.isEmpty(x.getProductDimensionVM())
                    && (!StringUtils.isEmpty(x.getProductDimensionVM().getProduct_weight_unit_of_meas()) ||
                    !StringUtils.isEmpty(x.getProductDimensionVM().getProduct_size_unit_of_meas()
                    ))).forEach(m-> {

                if (StringUtils.isEmpty(lstUnitOfMeas)) {
                    if (StringUtils.isEmpty(m.getProductDimensionVM().getProduct_weight_unit_of_meas()) ||
                            StringUtils.isEmpty(m.getProductDimensionVM().getProduct_size_unit_of_meas())) {
                        m.setError_status("Failed");
                    }
                    else {
                        m.setError_status("Failed");
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find UOM-->");
                    }
                }
                else {
                    if (!lstLangCodeData.stream().anyMatch(r->r.getCode().equalsIgnoreCase(m.getProductDimensionVM().getProduct_weight_unit_of_meas()))) {
                        m.setError_status("Failed");
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find weight UOM-->"+m.getProductDimensionVM().getProduct_weight_unit_of_meas());
                    }
                    if (!lstLangCodeData.stream().anyMatch(r->r.getCode().equalsIgnoreCase(m.getProductDimensionVM().getProduct_size_unit_of_meas()))) {
                        m.setError_status("Failed");
                        m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find size UOM-->"+m.getProductDimensionVM().getProduct_size_unit_of_meas());
                    }
                }
            });
        }
    }

    public void checkNetworkCode(SkuAttributeDefinitionVM skuAttributeDefinitionVM,String modelNum) {

        Optional<String[]> networkcode = skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                && !StringUtils.isEmpty(x.getNetworkCode()) && x.getNetworkCode().length>0)
                .map(m->m.getNetworkCode()).distinct().findFirst();
        if (networkcode.isPresent()) {
            if (StringUtils.isEmpty(lstNetworkCode))
                lstNetworkCode = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getNETWORK_CODE());
            if (StringUtils.isEmpty(lstNetworkCode)) {
                skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum))
                        .forEach(m-> {
                            m.setError_status("Failed");
                            m.setError_message((StringUtils.isEmpty(m.getError_message())? "" :m.getError_message() + ".") +"Unable to find Network Code-->"+m.getNetworkCode().toString());
                        });
            }
            else {

                skuAttributeDefinitionVM.getLstModelNumberVM().stream().filter(x->x.getModel_number().equalsIgnoreCase(modelNum)
                        && !StringUtils.isEmpty(x.getNetworkCode()) && x.getNetworkCode().length>0)
                        .forEach(h->{
                            Arrays.stream(h.getNetworkCode()).forEach(p->{
                                if (!lstNetworkCode.stream().anyMatch(r->r.getCode().equalsIgnoreCase(p))) {
                                    h.setError_status("Failed");
                                    h.setError_message((StringUtils.isEmpty(h.getError_message())? "" :h.getError_message() + ".") +p);
                                }
                            });
                            h.setError_message((StringUtils.isEmpty(h.getError_message())? "" :h.getError_message() + " missing"));
                        });
            }
        }
    }


}