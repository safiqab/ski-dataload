package com.dish.scm.ski.skidataload.process;


import com.dish.scm.ski.skidataload.config.SkiReferenceNameConfig;
import com.dish.scm.ski.skidataload.connector.SkiApiClient;
import com.dish.scm.ski.skidataload.model.file.ManufacturerConfigRefVM;
import com.dish.scm.ski.skidataload.model.file.ModelConfigVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeReferenceVM;
import com.dish.scm.ski.skidataload.model.file.SupplierConfigRefVM;
import com.dish.scm.ski.skidataload.model.response.SkiReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dish.scm.ski.skidataload.util.DataUtil.distinctByKeys;

@Component
@Slf4j
public class SkiReferenceProcess {

    private final SkiApiClient skiApiClient;
    private final SkiReferenceNameConfig skiReferenceNameConfig;

    @Autowired
    public SkiReferenceProcess(SkiApiClient skiApiClient,SkiReferenceNameConfig skiReferenceNameConfig) {
        this.skiApiClient = skiApiClient;
        this.skiReferenceNameConfig = skiReferenceNameConfig;
    }

    public void insertReferenceData(SkuAttributeReferenceVM skuAttributeReferenceVM) {

        insertBrandCode(skuAttributeReferenceVM);
        insertChannel(skuAttributeReferenceVM);
        insertHexColor(skuAttributeReferenceVM);
        insertColor(skuAttributeReferenceVM);
        insertComponentType(skuAttributeReferenceVM);
        insertConfigType(skuAttributeReferenceVM);
        insertCountryOfOrgin(skuAttributeReferenceVM);
        insertDeptID(skuAttributeReferenceVM);
        insertDispositionCode(skuAttributeReferenceVM);
        insertEquipID(skuAttributeReferenceVM);
        insertEquipSubCat(skuAttributeReferenceVM);
        insertEquipSubType(skuAttributeReferenceVM);
        insertEquipType(skuAttributeReferenceVM);
        insertFeatureType(skuAttributeReferenceVM);
        insertLangRef(skuAttributeReferenceVM);
        insertLocationRef(skuAttributeReferenceVM);
        insertManfCodeRef(skuAttributeReferenceVM);
        insertManfConfigRef(skuAttributeReferenceVM);
        insertOsTypeRef(skuAttributeReferenceVM);
        insertOwnershipCodeRef(skuAttributeReferenceVM);
        insertPackageTypeRef(skuAttributeReferenceVM);
        insertProfileTypeRef(skuAttributeReferenceVM);
        insertSerialTypeRef(skuAttributeReferenceVM);
        insertShipmentStatus(skuAttributeReferenceVM);
        insertSimFormRef(skuAttributeReferenceVM);
        insertSimTypeRef(skuAttributeReferenceVM);
        insertSkuTypeRef(skuAttributeReferenceVM);
        insertSubDeptIdRef(skuAttributeReferenceVM);
        insertSupplierRef(skuAttributeReferenceVM);
        insertSupplierConfigRef(skuAttributeReferenceVM);
        insertTransactionTypeRef(skuAttributeReferenceVM);
        insertUnitOfMeasRef(skuAttributeReferenceVM);
        insertUnlockStatusRef(skuAttributeReferenceVM);
        insertUrlTypeRef(skuAttributeReferenceVM);
        insertSkuOwnerRef(skuAttributeReferenceVM);
        insertNetworkCodeRef(skuAttributeReferenceVM);
    }

    public void insertBrandCode(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstBrandcodeRefVM()) && skuAttributeReferenceVM.getLstBrandcodeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getBRAND_CODE_REF());

            skuAttributeReferenceVM.getLstBrandcodeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getBrand_code(),skiReferenceNameConfig.getBRAND_CODE_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getBrand_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getBRAND_CODE_REF())
                                .code(x.getBrand_code())
                                .name(x.getBrand_name())
                                .type(x.getBrand_type())
                                .description(x.getBrand_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "Modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertChannel(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstChannelRefVM()) && skuAttributeReferenceVM.getLstChannelRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCHANNEL_REF());

            skuAttributeReferenceVM.getLstChannelRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getChannel_code(),skiReferenceNameConfig.getCHANNEL_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getChannel_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCHANNEL_REF())
                                .code(x.getChannel_code())
                                .name(x.getChannel_name())
                                .description(x.getChannel_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertHexColor(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorHexRefVM()) && skuAttributeReferenceVM.getLstColorHexRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCOLOR_HEX_REF());

            skuAttributeReferenceVM.getLstColorHexRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getColor_hex(),skiReferenceNameConfig.getCOLOR_HEX_REF(),"HEXCOLOR");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getColor_hex().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCOLOR_HEX_REF())
                                .hexColor(x.getColor_hex())
                                .name(x.getColor_hex_name())
                                .description(x.getColor_hex_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertColor(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorRefVM()) && skuAttributeReferenceVM.getLstColorRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCOLOR_REF());

            skuAttributeReferenceVM.getLstColorRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getColor(),skiReferenceNameConfig.getCOLOR_REF(),"COLOR");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getColor().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCOLOR_REF())
                                .color(x.getColor())
                                .hexColor(x.getHex_color())
                                .fullNameCode(x.getFull_name_code())
                                .fourDigitCode(x.getFour_digit_code())
                                .threeDigitCode(x.getThree_digit_code())
                                .twoDigitCode(x.getTwo_digit_code())
                                .name(x.getColor_name())
                                .description(x.getColor_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertComponentType(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstComponentTypeRefVM()) && skuAttributeReferenceVM.getLstComponentTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCOMPONENT_TYPE_REF());

            skuAttributeReferenceVM.getLstComponentTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getComponent_type(),skiReferenceNameConfig.getCOMPONENT_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getComponent_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCOMPONENT_TYPE_REF())
                                .type(x.getComponent_type())
                                .name(x.getComponent_type_name())
                                .description(x.getComponent_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertConfigType(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstConfigTypeRefVM()) && skuAttributeReferenceVM.getLstConfigTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCONFIG_TYPE_REF());

            skuAttributeReferenceVM.getLstConfigTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getConfig_type(),skiReferenceNameConfig.getCONFIG_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getConfig_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCONFIG_TYPE_REF())
                                .type(x.getConfig_type())
                                .category(x.getCategory())
                                .subCategory1(x.getSubcategory1())
                                .subCategory2(x.getSubcategory2())
                                .name(x.getConfig_name())
                                .description(x.getConfig_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertCountryOfOrgin(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstCountryOfOrginRefVM()) && skuAttributeReferenceVM.getLstCountryOfOrginRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCOUNTRY_OF_ORIGIN_REF());

            skuAttributeReferenceVM.getLstCountryOfOrginRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getCountry_code(),skiReferenceNameConfig.getCOUNTRY_OF_ORIGIN_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getCountry_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getCOUNTRY_OF_ORIGIN_REF())
                                .code(x.getCountry_code())
                                .name(x.getCountry_name())
                                .description(x.getCountry_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertDeptID(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDeptIdRefVM()) && skuAttributeReferenceVM.getLstDeptIdRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getDEPT_ID_REF());

            skuAttributeReferenceVM.getLstDeptIdRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,Long.toString(x.getDept_id()),skiReferenceNameConfig.getDEPT_ID_REF(),"DEPTID");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? Long.toString(x.getDept_id()) : Id)
                                .refCollectionName(skiReferenceNameConfig.getDEPT_ID_REF())
                                .deptId(x.getDept_id())
                                .name(x.getDept_name())
                                .description(x.getDept_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertDispositionCode(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDispositioncodeRefVM()) && skuAttributeReferenceVM.getLstDispositioncodeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getDISPOSITION_CODE_REF());

            skuAttributeReferenceVM.getLstDispositioncodeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,Long.toString(x.getDisposition_code()),skiReferenceNameConfig.getDISPOSITION_CODE_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? Long.toString(x.getDisposition_code()) : Id)
                                .refCollectionName(skiReferenceNameConfig.getDISPOSITION_CODE_REF())
                                .code(Long.toString(x.getDisposition_code()))
                                .name(x.getDisposition_name())
                                .description(x.getDisposition_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertEquipID(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipIdRefVM()) && skuAttributeReferenceVM.getLstEquipIdRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_ID_REF());

            skuAttributeReferenceVM.getLstEquipIdRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getEquip_id(),skiReferenceNameConfig.getEQUIP_ID_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getEquip_id().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getEQUIP_ID_REF())
                                .code(x.getEquip_id())
                                .name(x.getEquip_name())
                                .description(x.getEquip_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertEquipSubCat(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipSubCatRefVM()) && skuAttributeReferenceVM.getLstEquipSubCatRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_SUB_CAT_REF());

            skuAttributeReferenceVM.getLstEquipSubCatRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getEquip_sub_cat(),skiReferenceNameConfig.getEQUIP_SUB_CAT_REF(),"SUBCAT");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getEquip_sub_cat().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getEQUIP_SUB_CAT_REF())
                                .equipSubCat(x.getEquip_sub_cat())
                                .name(x.getEquip_sub_cat_name())
                                .description(x.getEquip_sub_cat_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertEquipSubType(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipsubTypeRefVM()) && skuAttributeReferenceVM.getLstEquipsubTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_SUB_TYPE_REF());

            skuAttributeReferenceVM.getLstEquipsubTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getEquip_sub_type(),skiReferenceNameConfig.getEQUIP_SUB_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getEquip_sub_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getEQUIP_SUB_TYPE_REF())
                                .type(x.getEquip_sub_type())
                                .name(x.getEquip_sub_type_name())
                                .description(x.getEquip_sub_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertEquipType(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipTypeRefVM()) && skuAttributeReferenceVM.getLstEquipTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getEQUIP_TYPE_REF());

            skuAttributeReferenceVM.getLstEquipTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getEquip_type(),skiReferenceNameConfig.getEQUIP_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getEquip_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getEQUIP_TYPE_REF())
                                .type(x.getEquip_type())
                                .name(x.getEquip_type_name())
                                .description(x.getEquip_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertFeatureType(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstFeatureTypeRefVM()) && skuAttributeReferenceVM.getLstFeatureTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getFEATURE_TYPE_REF());

            skuAttributeReferenceVM.getLstFeatureTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getFeature_type(),skiReferenceNameConfig.getFEATURE_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getFeature_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getFEATURE_TYPE_REF())
                                .type(x.getFeature_type())
                                .name(x.getFeature_name())
                                .description(x.getFeature_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertLangRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLangcodeRefVM()) && skuAttributeReferenceVM.getLstLangcodeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getLANG_CODE_REF());

            skuAttributeReferenceVM.getLstLangcodeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getLanguage_code(),skiReferenceNameConfig.getLANG_CODE_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getLanguage_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getLANG_CODE_REF())
                                .code(x.getLanguage_code())
                                .name(x.getLanguage_name())
                                .description(x.getLanguage_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertLocationRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLocationRefVM()) && skuAttributeReferenceVM.getLstLocationRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getLOCATION_REF());

            skuAttributeReferenceVM.getLstLocationRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,Long.toString(x.getLocation_id()),skiReferenceNameConfig.getLOCATION_REF(),"LOCID");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? Long.toString(x.getLocation_id()) : Id)
                                .refCollectionName(skiReferenceNameConfig.getLOCATION_REF())
                                .locationId(x.getLocation_id())
                                .shipToCode(x.getShip_to_code())
                                .locationAddrLine1(x.getLocation_addr_line1())
                                .locationAddrLine2(x.getLocation_addr_line2())
                                .locationCity(x.getLocation_city())
                                .locationState(x.getLocation_state())
                                .locationPostalCode(x.getLocation_postal_code())
                                .locationCountryCode(x.getLocation_country_code())
                                .contactPhoneNumber(x.getContact_phone_number())
                                .contactFaxNumber(x.getContact_fax_number())
                                .contactFirstName(x.getContact_first_name())
                                .contactLastName(x.getContact_last_name())
                                .channelId(x.getChannel_id())
                                .name(x.getLocation_name())
                                .description(x.getLocation_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertManfCodeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerRefVM()) && skuAttributeReferenceVM.getLstManufacturerRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getMANUFACTURER_REF());

            skuAttributeReferenceVM.getLstManufacturerRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                Optional<List<ModelConfigVM>> lstConfig = Optional.empty();
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getManf_code(),skiReferenceNameConfig.getMANUFACTURER_REF(),"CODE");
                    if (!StringUtils.isEmpty(Id))
                        lstConfig = lstExistData.stream().filter(y -> !StringUtils.isEmpty(y.getCode()) && !StringUtils.isEmpty(y.getRefCollectionName())
                            && y.getCode().equalsIgnoreCase(x.getManf_code()) && y.getRefCollectionName().equalsIgnoreCase(skiReferenceNameConfig.getMANUFACTURER_REF()))
                            .map(SkiReference::getLstConfigVM).findFirst();
                }

                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getManf_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getMANUFACTURER_REF())
                                .code(x.getManf_code())
                                .name(x.getManf_name())
                                .lstConfigVM(StringUtils.isEmpty(lstConfig) ? null : lstConfig.orElse(null))
                                .description(x.getManf_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertManfConfigRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerConfigRefVM()) && skuAttributeReferenceVM.getLstManufacturerConfigRefVM().size()>0 ) {

            List<SkiReference> lstExistData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getMANUFACTURER_REF());
            if (StringUtils.isEmpty(lstExistData) && lstExistData.size()<=0 ) {
                skuAttributeReferenceVM.getLstManufacturerConfigRefVM()
                        .forEach(k-> {
                            k.setError_status("Failed");
                            k.setError_message("Manufacturer code does not exist.");
                        });
            }
            else {
                List<SkiReference> lstExistConfigTypeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCONFIG_TYPE_REF());
                if (StringUtils.isEmpty(lstExistConfigTypeData)) {
                    skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().forEach(k->{
                                k.setError_status("Failed");
                                k.setError_message("Config type does not exist.");
                            });
                }
                else {

                    List<String> lstManfCode = skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status()))
                            .map(ManufacturerConfigRefVM::getManf_code).distinct().collect(Collectors.toList());

                    lstManfCode.forEach(x->{

                        Optional<SkiReference> existData = lstExistData.stream().filter(y -> !StringUtils.isEmpty(y.getCode()) && !StringUtils.isEmpty(y.getRefCollectionName())
                                && y.getCode().equalsIgnoreCase(x) && y.getRefCollectionName().equalsIgnoreCase(skiReferenceNameConfig.getMANUFACTURER_REF()))
                                .findFirst();

                        if (existData.isPresent()) {
                            skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().filter(mn->mn.getManf_code().equalsIgnoreCase(x))
                                    .forEach(g->{
                                        if (!lstExistConfigTypeData.stream().anyMatch(kk->kk.getType().equalsIgnoreCase(g.getConfig_type()))) {
                                            g.setError_status("Failed");
                                            g.setError_message("Config type does not exist.");
                                        }
                                        else {
                                            existData.get().getLstConfigVM().add(
                                                    ModelConfigVM.builder().configType(g.getConfig_type()).configValue(g.getConfig_value()).build()
                                            );
                                        }
                                    });
                            if (skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().filter(mn->mn.getManf_code().equalsIgnoreCase(x))
                                    .anyMatch(m1-> !StringUtils.isEmpty(m1.getError_status()))) {

                                skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().filter(mn->mn.getManf_code().equalsIgnoreCase(x)
                                        && StringUtils.isEmpty(mn.getError_status()))
                                        .forEach(g-> {
                                            g.setError_status("Failed");
                                            g.setError_message("Config type does not exist.");
                                        });
                            }
                            else {

                                existData.get().getLstConfigVM().stream().filter(distinctByKeys(ModelConfigVM::getConfigType,ModelConfigVM::getConfigValue))
                                        .collect(Collectors.toList());
                                String rtnStatus = this.skiApiClient.addReferenceDataApi(existData.get(),"Modify");
                                skuAttributeReferenceVM.getLstManufacturerConfigRefVM().stream().filter(mn->mn.getManf_code().equalsIgnoreCase(x))
                                        .forEach(k-> {
                                            k.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                                            k.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
                                        });
                            }
                        }
                    });
                }
            }
        }
    }


    public void insertOsTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOsTypeRefVM()) && skuAttributeReferenceVM.getLstOsTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getOS_TYPE());

            skuAttributeReferenceVM.getLstOsTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getOs_type(),skiReferenceNameConfig.getOS_TYPE(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getOs_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getOS_TYPE())
                                .type(x.getOs_type())
                                .name(x.getOs_type_name())
                                .description(x.getOs_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertOwnershipCodeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOwnershipcodeRefVM()) && skuAttributeReferenceVM.getLstOwnershipcodeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getOWNERSHIP_CODE_REF());

            skuAttributeReferenceVM.getLstOwnershipcodeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getOwnership_code(),skiReferenceNameConfig.getOWNERSHIP_CODE_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getOwnership_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getOWNERSHIP_CODE_REF())
                                .code(x.getOwnership_code())
                                .name(x.getOwnership_code_name())
                                .description(x.getOwnership_code_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertPackageTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstPackageTypeRefVM()) && skuAttributeReferenceVM.getLstPackageTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getPACKAGE_TYPE_REF());

            skuAttributeReferenceVM.getLstPackageTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getPackage_type(),skiReferenceNameConfig.getPACKAGE_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getPackage_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getPACKAGE_TYPE_REF())
                                .type(x.getPackage_type())
                                .name(x.getPackage_type_name())
                                .description(x.getPackage_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertProfileTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstProfileTypeRefVM()) && skuAttributeReferenceVM.getLstProfileTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getPROFILE_TYPE_REF());

            skuAttributeReferenceVM.getLstProfileTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getProfile_type(),skiReferenceNameConfig.getPROFILE_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getProfile_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getPROFILE_TYPE_REF())
                                .type(x.getProfile_type())
                                .name(x.getProfile_type_name())
                                .description(x.getProfile_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSerialTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSerialTypeRefVM()) && skuAttributeReferenceVM.getLstSerialTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSERIAL_TYPE_REF());

            skuAttributeReferenceVM.getLstSerialTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSerial_type(),skiReferenceNameConfig.getSERIAL_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSerial_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSERIAL_TYPE_REF())
                                .type(x.getSerial_type())
                                .name(x.getSerial_type_name())
                                .description(x.getSerial_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertShipmentStatus(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstShipmentstatusRefVM()) && skuAttributeReferenceVM.getLstShipmentstatusRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSHIPMENT_STATUS_REF());

            skuAttributeReferenceVM.getLstShipmentstatusRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getShipment_status(),skiReferenceNameConfig.getSHIPMENT_STATUS_REF(),"NAME");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getShipment_status().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSHIPMENT_STATUS_REF())
                                .name(x.getShipment_status())
                                .description(x.getShipment_status_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSimFormRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimFormRefVM()) && skuAttributeReferenceVM.getLstSimFormRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSIM_FORM_REF());

            skuAttributeReferenceVM.getLstSimFormRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSim_form(),skiReferenceNameConfig.getSIM_FORM_REF(),"SIMFORM");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSim_form().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSIM_FORM_REF())
                                .simForm(x.getSim_form())
                                .name(x.getSim_form_name())
                                .description(x.getSim_form_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSimTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimTypeRefVM()) && skuAttributeReferenceVM.getLstSimTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSIM_TYPE_REF());

            skuAttributeReferenceVM.getLstSimTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSim_type(),skiReferenceNameConfig.getSIM_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSim_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSIM_TYPE_REF())
                                .type(x.getSim_type())
                                .name(x.getSim_type_name())
                                .description(x.getSim_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSkuTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuTypeRefVM()) && skuAttributeReferenceVM.getLstSkuTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSKU_TYPE());

            skuAttributeReferenceVM.getLstSkuTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSku_type(),skiReferenceNameConfig.getSKU_TYPE(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSku_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSKU_TYPE())
                                .type(x.getSku_type())
                                .name(x.getSku_type_name())
                                .description(x.getSku_type_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSubDeptIdRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSubDeptIdRefVM()) && skuAttributeReferenceVM.getLstSubDeptIdRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSUB_DEPT_ID());

            skuAttributeReferenceVM.getLstSubDeptIdRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,Long.toString(x.getSub_dept_id()),skiReferenceNameConfig.getSUB_DEPT_ID(),"SUBDEPTID");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? Long.toString(x.getSub_dept_id()) : Id)
                                .refCollectionName(skiReferenceNameConfig.getSUB_DEPT_ID())
                                .subDeptId(x.getSub_dept_id())
                                .name(x.getSub_dept_name())
                                .description(x.getSub_dept_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertSupplierRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierRefVM()) && skuAttributeReferenceVM.getLstSupplierRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSUPPLIER_CODE());

            skuAttributeReferenceVM.getLstSupplierRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSupplier_code(),skiReferenceNameConfig.getSUPPLIER_CODE(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSupplier_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSUPPLIER_CODE())
                                .code(x.getSupplier_code())
                                .name(x.getSupplier_name())
                                .description(x.getSupplier_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSupplierConfigRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierConfigRefVM()) && skuAttributeReferenceVM.getLstSupplierConfigRefVM().size()>0 ) {

            List<SkiReference> lstExistData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSUPPLIER_CODE());
            if (StringUtils.isEmpty(lstExistData) && lstExistData.size()<=0 ) {
                skuAttributeReferenceVM.getLstSupplierConfigRefVM()
                        .forEach(k-> {
                            k.setError_status("Failed");
                            k.setError_message("Supplier code does not exist.");
                        });
            }
            else {
                List<SkiReference> lstExistConfigTypeData = this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getCONFIG_TYPE_REF());
                if (StringUtils.isEmpty(lstExistConfigTypeData)) {
                    skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().forEach(k->{
                        k.setError_status("Failed");
                        k.setError_message("Config type does not exist.");
                    });
                }
                else {

                    List<String> lstSupplierCode = skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status()))
                            .map(SupplierConfigRefVM::getSupplier_code).distinct().collect(Collectors.toList());

                    lstSupplierCode.forEach(x->{

                        Optional<SkiReference> existData = lstExistData.stream().filter(y -> !StringUtils.isEmpty(y.getCode()) && !StringUtils.isEmpty(y.getRefCollectionName())
                                && y.getCode().equalsIgnoreCase(x) && y.getRefCollectionName().equalsIgnoreCase(skiReferenceNameConfig.getSUPPLIER_CODE()))
                                .findFirst();

                        if (existData.isPresent()) {
                            skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().filter(mn->mn.getSupplier_code().equalsIgnoreCase(x))
                                    .forEach(g->{
                                        if (!lstExistConfigTypeData.stream().anyMatch(kk->kk.getType().equalsIgnoreCase(g.getConfig_type()))) {
                                            g.setError_status("Failed");
                                            g.setError_message("Config type does not exist.");
                                        }
                                        else {
                                            existData.get().getLstConfigVM().add(
                                                    ModelConfigVM.builder().configType(g.getConfig_type()).configValue(g.getConfig_value()).build()
                                            );
                                        }
                                    });
                            if (skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().filter(mn->mn.getSupplier_code().equalsIgnoreCase(x))
                                    .anyMatch(m1-> !StringUtils.isEmpty(m1.getError_status()))) {

                                skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().filter(mn->mn.getSupplier_code().equalsIgnoreCase(x)
                                        && StringUtils.isEmpty(mn.getError_status()))
                                        .forEach(g-> {
                                            g.setError_status("Failed");
                                            g.setError_message("Config type does not exist.");
                                        });
                            }
                            else {

                                existData.get().getLstConfigVM().stream().filter(distinctByKeys(ModelConfigVM::getConfigType,ModelConfigVM::getConfigValue))
                                        .collect(Collectors.toList());
                                String rtnStatus = this.skiApiClient.addReferenceDataApi(existData.get(),"Modify");
                                skuAttributeReferenceVM.getLstSupplierConfigRefVM().stream().filter(mn->mn.getSupplier_code().equalsIgnoreCase(x))
                                        .forEach(k-> {
                                            k.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                                            k.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
                                        });
                            }
                        }
                    });
                }
            }
        }
    }





    public void insertTransactionTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstTransactionTypeRefVM()) && skuAttributeReferenceVM.getLstTransactionTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getTRANSACTION_TYPE_REF());

            skuAttributeReferenceVM.getLstTransactionTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getTrans_type(),skiReferenceNameConfig.getTRANSACTION_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getTrans_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getTRANSACTION_TYPE_REF())
                                .type(x.getTrans_type())
                                .name(x.getTrans_name())
                                .description(x.getTrans_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertUnitOfMeasRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnitOfMeasRefVM()) && skuAttributeReferenceVM.getLstUnitOfMeasRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getUNIT_OF_MEAS_REF());

            skuAttributeReferenceVM.getLstUnitOfMeasRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getUnit_of_meas(),skiReferenceNameConfig.getUNIT_OF_MEAS_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getUnit_of_meas().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getUNIT_OF_MEAS_REF())
                                .code(x.getUnit_of_meas())
                                .name(x.getUnit_of_meas_name())
                                .description(x.getUnit_of_meas_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertUnlockStatusRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnlockStatusRefVM()) && skuAttributeReferenceVM.getLstUnlockStatusRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getUNLOCK_STATUS_REF());

            skuAttributeReferenceVM.getLstUnlockStatusRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,Long.toString(x.getUnlock_status_code()),skiReferenceNameConfig.getUNLOCK_STATUS_REF(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? Long.toString(x.getUnlock_status_code()) : Id)
                                .refCollectionName(skiReferenceNameConfig.getUNLOCK_STATUS_REF())
                                .code(Long.toString(x.getUnlock_status_code()))
                                .name(x.getUnlock_status_name())
                                .description(x.getUnlock_status_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }


    public void insertUrlTypeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUrlTypeRefVM()) && skuAttributeReferenceVM.getLstUrlTypeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getURL_TYPE_REF());

            skuAttributeReferenceVM.getLstUrlTypeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getUrl_type(),skiReferenceNameConfig.getURL_TYPE_REF(),"TYPE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getUrl_type().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getURL_TYPE_REF())
                                .type(x.getUrl_type())
                                .name(x.getUrl_name())
                                .description(x.getUrl_desc())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertSkuOwnerRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuOwnerRefVM()) && skuAttributeReferenceVM.getLstSkuOwnerRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getSKU_OWNER_REF());

            skuAttributeReferenceVM.getLstSkuOwnerRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getSku_owner(),skiReferenceNameConfig.getSKU_OWNER_REF(),"SKUOWNER");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getSku_owner().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getSKU_OWNER_REF())
                                .skuOwner(x.getSku_owner())
                                .name(x.getName())
                                .description(x.getDescription())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public void insertNetworkCodeRef(SkuAttributeReferenceVM skuAttributeReferenceVM) {
        if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstNetworkCodeRefVM()) && skuAttributeReferenceVM.getLstNetworkCodeRefVM().size()>0 ) {

            List<SkiReference> lstExistData= this.skiApiClient.getReferenceDataApi(skiReferenceNameConfig.getNETWORK_CODE());

            skuAttributeReferenceVM.getLstNetworkCodeRefVM().stream().filter(x-> StringUtils.isEmpty(x.getError_status())).forEach(x-> {

                String Id = null;
                if (!StringUtils.isEmpty(lstExistData) && lstExistData.size()>0 ) {
                    Id = getReferenceIDFromCode(lstExistData,x.getNetwork_code(),skiReferenceNameConfig.getNETWORK_CODE(),"CODE");
                }
                String rtnStatus = this.skiApiClient.addReferenceDataApi(
                        SkiReference.builder()
                                .id(StringUtils.isEmpty(Id) ? x.getNetwork_code().replace(" ","") : Id)
                                .refCollectionName(skiReferenceNameConfig.getNETWORK_CODE())
                                .code(x.getNetwork_code())
                                .name(x.getName())
                                .description(x.getDescription())
                                .build(),
                        StringUtils.isEmpty(Id) ? "new" : "modify");

                x.setError_status(rtnStatus.equalsIgnoreCase("success")?rtnStatus:"Failed");
                x.setError_message( rtnStatus.equalsIgnoreCase("success") ? "":rtnStatus);
            });
        }
    }

    public String getReferenceIDFromCode(List<SkiReference> lstData, String code,String collType,String fieldName) {
        if (StringUtils.isEmpty(lstData) || lstData.size() <=0 )
            return null;
        if (fieldName.equalsIgnoreCase("CODE")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getCode()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getCode().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("TYPE")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getType()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getType().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("HEXCOLOR")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getHexColor()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getHexColor().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("COLOR")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getColor()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getColor().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("DEPTID")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getDeptId()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && Long.toString(x.getDeptId()).equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("SUBCAT")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getEquipSubCat()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getEquipSubCat().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("LOCID")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getLocationId()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && Long.toString(x.getLocationId()).equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("SIMFORM")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getSimForm()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getSimForm().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("SUBDEPTID")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getSubDeptId()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && Long.toString(x.getSubDeptId()).equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        else if (fieldName.equalsIgnoreCase("SKUOWNER")) {
            Optional<String> strPubId = lstData.stream().filter(x -> !StringUtils.isEmpty(x.getSkuOwner()) && !StringUtils.isEmpty(x.getRefCollectionName())
                    && x.getSkuOwner().equalsIgnoreCase(code) && x.getRefCollectionName().equalsIgnoreCase(collType))
                    .map(SkiReference::getId).findFirst();
            return strPubId.orElse(null);
        }
        return null;
    }

}
