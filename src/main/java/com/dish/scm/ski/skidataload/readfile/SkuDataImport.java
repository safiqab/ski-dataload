package com.dish.scm.ski.skidataload.readfile;

import com.dish.scm.ski.skidataload.config.FileTabNameConfig;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeDefinitionVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeReferenceVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@Slf4j
public class SkuDataImport {

    private final FeatureTypeRefWS featureTypeRefWS;
    private final ConfigTypeRefWS configTypeRefWS;
    private final ManufacturerRefWS manufacturerRefWS;
    private final SupplierRefWS supplierRefWS;
    private final EquipIDRefWS equipIdRefWS;
    private final SimTypeRefWS simTypeRefWS;
    private final SimFormRefWS simFormRefWS;
    private final PackageTypeRefWS packageTypeRefWS;
    private final SkuTypeRefWS skuTypeRefWS;
    private final UnitOfMeasRefWS unitOfMeasRefWS;
    private final ColorRefWS colorRefWS;
    private final ColorHexRefWS colorHexRefWS;
    private final DeptIdRefWS deptIdRefWS;
    private final SubDeptIdRefWS subDeptIdRefWS;
    private final OsTypeRefWS osTypeRefWS;
    private final SerialTypeRefWS serialTypeRefWS;
    private final EquipTypeRefWS equipTypeRefWS;
    private final EquipSubTypeRefWS equipSubTypeRefWS;
    private final EquipSubCatRefWS equipSubCatRefWS;
    private final LangCodeRefWS langCodeRefWS;
    private final BrandCodeRefWS brandCodeRefWS;
    private final LocationRefWS locationRefWS;
    private final ChannelRefWS channelRefWS;
    private final UrlTypeRefWS urlTypeRefWS;
    private final OwnershipCodeRefWS ownershipCodeRefWS;
    private final SKUBrandWS skuBrandWS;
    private final SKUNetworkWS skuNetworkWS;
    private final AccessorySKUWS accessorySKUWS;
    private final SimSKUWS simSKUWS;
    private final SimConfigWS simConfigWS;

    private final ComponentTypeRefWS componentTypeRefWS;
    private final ProfileTypeRefWS profileTypeRefWS;
    private final TransactionTypeRefWS transactionTypeRefWS;
    private final DispositionCodeRefWS dispositionCodeRefWS;
    private final ShipmentStatusRefWS shipmentStatusRefWS;
    private final UnlockStatusRefWS unlockStatusRefWS;
    private final SkuOwnerRefWS skuOwnerRefWS;
    private final ModelNumberWS modelNumberWS;
    private final ProductDefinitionWS productDefinitionWS;
    private final ProductSupplierWS productSupplierWS;
    private final TacRangeWS tacRangeWS;
    private final LanguageSupportWS languageSupportWS;
    private final ImageUrlWS imageUrlWS;
    private final SalesChannelWS salesChannelWS;
    private final CountryOfOrginWS countryOfOrginWS;
    private final ProductConfigWS productConfigWS;
    private final ModelConfigWS modelConfigWS;
    private final HazardousMaterialWS hazardousMaterialWS;
    private final ManufacturerConfigRefWS manufacturerConfigRefWS;
    private final SupplierConfigWS supplierConfigWS;
    private final SkuPartOwnerWS skuPartOwnerWS;
    private final FileTabNameConfig fileTabNameConfig;

    @Autowired
    public SkuDataImport(ConfigTypeRefWS configTypeRefWS, SkuTypeRefWS skuTypeRefWS, FeatureTypeRefWS featureTypeRefWS, ColorHexRefWS colorHexRefWS, SerialTypeRefWS serialTypeRefWS, ManufacturerRefWS manufacturerRefWS, SupplierRefWS supplierRefWS, SKUBrandWS skuBrandWS, SkuOwnerRefWS skuOwnerRefWS, SalesChannelWS salesChannelWS, LanguageSupportWS languageSupportWS, DeptIdRefWS deptIdRefWS, BrandCodeRefWS brandCodeRefWS, TransactionTypeRefWS transactionTypeRefWS, TacRangeWS tacRangeWS, ShipmentStatusRefWS shipmentStatusRefWS, UrlTypeRefWS urlTypeRefWS, EquipIDRefWS equipIdRefWS, SimTypeRefWS simTypeRefWS, SimFormRefWS simFormRefWS, EquipSubTypeRefWS equipSubTypeRefWS, ModelNumberWS modelNumberWS, ProductDefinitionWS productDefinitionWS, OwnershipCodeRefWS ownershipCodeRefWS, ImageUrlWS imageUrlWS, OsTypeRefWS osTypeRefWS, ProductSupplierWS productSupplierWS, ComponentTypeRefWS componentTypeRefWS, UnlockStatusRefWS unlockStatusRefWS, FileTabNameConfig fileTabNameConfig, DispositionCodeRefWS dispositionCodeRefWS, LangCodeRefWS langCodeRefWS, ChannelRefWS channelRefWS, ProfileTypeRefWS profileTypeRefWS, PackageTypeRefWS packageTypeRefWS, EquipTypeRefWS equipTypeRefWS, EquipSubCatRefWS equipSubCatRefWS, UnitOfMeasRefWS unitOfMeasRefWS, LocationRefWS locationRefWS, ColorRefWS colorRefWS, CountryOfOrginWS countryOfOrginWS, SubDeptIdRefWS subDeptIdRefWS, SKUNetworkWS skuNetworkWS, AccessorySKUWS accessorySKUWS, SimSKUWS simSKUWS, SimConfigWS simConfigWS, ProductConfigWS productConfigWS, ModelConfigWS modelConfigWS, HazardousMaterialWS hazardousMaterialWS, ManufacturerConfigRefWS manufacturerConfigRefWS, SupplierConfigWS supplierConfigWS, SkuPartOwnerWS skuPartOwnerWS) {
        this.configTypeRefWS = configTypeRefWS;
        this.skuTypeRefWS = skuTypeRefWS;
        this.featureTypeRefWS = featureTypeRefWS;
        this.colorHexRefWS = colorHexRefWS;
        this.serialTypeRefWS = serialTypeRefWS;
        this.manufacturerRefWS = manufacturerRefWS;
        this.supplierRefWS = supplierRefWS;
        this.skuBrandWS = skuBrandWS;
        this.skuOwnerRefWS = skuOwnerRefWS;
        this.salesChannelWS = salesChannelWS;
        this.languageSupportWS = languageSupportWS;
        this.deptIdRefWS = deptIdRefWS;
        this.brandCodeRefWS = brandCodeRefWS;
        this.transactionTypeRefWS = transactionTypeRefWS;
        this.tacRangeWS = tacRangeWS;
        this.shipmentStatusRefWS = shipmentStatusRefWS;
        this.urlTypeRefWS = urlTypeRefWS;
        this.equipIdRefWS = equipIdRefWS;
        this.simTypeRefWS = simTypeRefWS;
        this.simFormRefWS = simFormRefWS;
        this.equipSubTypeRefWS = equipSubTypeRefWS;
        this.modelNumberWS = modelNumberWS;
        this.productDefinitionWS = productDefinitionWS;
        this.ownershipCodeRefWS = ownershipCodeRefWS;
        this.imageUrlWS = imageUrlWS;
        this.osTypeRefWS = osTypeRefWS;
        this.productSupplierWS = productSupplierWS;
        this.componentTypeRefWS = componentTypeRefWS;
        this.unlockStatusRefWS = unlockStatusRefWS;
        this.fileTabNameConfig = fileTabNameConfig;
        this.dispositionCodeRefWS = dispositionCodeRefWS;
        this.langCodeRefWS = langCodeRefWS;
        this.channelRefWS = channelRefWS;
        this.profileTypeRefWS = profileTypeRefWS;
        this.packageTypeRefWS = packageTypeRefWS;
        this.equipTypeRefWS = equipTypeRefWS;
        this.equipSubCatRefWS = equipSubCatRefWS;
        this.unitOfMeasRefWS = unitOfMeasRefWS;
        this.locationRefWS = locationRefWS;
        this.colorRefWS = colorRefWS;
        this.countryOfOrginWS = countryOfOrginWS;
        this.subDeptIdRefWS = subDeptIdRefWS;
        this.skuNetworkWS = skuNetworkWS;
        this.accessorySKUWS = accessorySKUWS;
        this.simSKUWS = simSKUWS;
        this.simConfigWS = simConfigWS;
        this.productConfigWS = productConfigWS;
        this.modelConfigWS = modelConfigWS;
        this.hazardousMaterialWS = hazardousMaterialWS;
        this.manufacturerConfigRefWS = manufacturerConfigRefWS;
        this.supplierConfigWS = supplierConfigWS;
        this.skuPartOwnerWS = skuPartOwnerWS;
    }

    public void readData(String fileName,SkuAttributeReferenceVM skuAttributeReferenceVM,
                         SkuAttributeDefinitionVM skuAttributeDefinitionVM) {

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {

            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fis);

            for (Sheet sheet : workbook) {
                log.info("sheet name:{}", sheet.getSheetName());
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getBrand_code_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstBrandcodeRefVM(brandCodeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getChannel_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstChannelRefVM(channelRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getColor_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstColorRefVM(colorRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getColor_hex_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstColorHexRefVM(colorHexRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getComponent_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstComponentTypeRefVM(componentTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getConfig_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstConfigTypeRefVM(configTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getCountry_of_orgin_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstCountryOfOrginRefVM(countryOfOrginWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getDept_id_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstDeptIdRefVM(deptIdRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getDisposition_code_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstDispositioncodeRefVM(dispositionCodeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getEquip_id_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstEquipIdRefVM(equipIdRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getEquip_sub_cat_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstEquipSubCatRefVM(equipSubCatRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getEquip_sub_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstEquipsubTypeRefVM(equipSubTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getEquip_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstEquipTypeRefVM(equipTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getFeature_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstFeatureTypeRefVM(featureTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getLang_code_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstLangcodeRefVM(langCodeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getLocation_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstLocationRefVM(locationRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getManufacturer_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstManufacturerRefVM(manufacturerRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getOs_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstOsTypeRefVM(osTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getOwnership_code_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstOwnershipcodeRefVM(ownershipCodeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getPackage_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstPackageTypeRefVM(packageTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getProfile_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstProfileTypeRefVM(profileTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSerial_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSerialTypeRefVM(serialTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getShipment_status_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstShipmentstatusRefVM(shipmentStatusRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSim_form_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSimFormRefVM(simFormRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSim_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSimTypeRefVM(simTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_owner_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSkuOwnerRefVM(skuOwnerRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSkuTypeRefVM(skuTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSub_dept_id_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSubDeptIdRefVM(subDeptIdRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSupplier_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSupplierRefVM(supplierRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getTransaction_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstTransactionTypeRefVM(transactionTypeRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getUnit_of_meas_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstUnitOfMeasRefVM(unitOfMeasRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getUnlock_status_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstUnlockStatusRefVM(unlockStatusRefWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getUrl_type_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstUrlTypeRefVM(urlTypeRefWS.readSheet(sheet));
                }

                //check later
                //if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getNetwork_code_ref().toLowerCase())) {
                //    skuAttributeReferenceVM.setLstNetworkCodeRefVM(skuNetworkWS.readSheet(sheet));
                //}

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getManufacturer_config_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstManufacturerConfigRefVM(manufacturerConfigRefWS.readSheet(sheet));
                }

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSupplier_config_ref().toLowerCase())) {
                    skuAttributeReferenceVM.setLstSupplierConfigRefVM(supplierConfigWS.readSheet(sheet));
                }

                //end of reference

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getModel_number().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstModelNumberVM(modelNumberWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getTac_range().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstTacRangeVM(tacRangeWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getModel_config().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstModelConfigVM(modelConfigWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getLanguage_support().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstLanguageSupportVM(languageSupportWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getHazardous_material().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstHazardousMaterialVM(hazardousMaterialWS.readSheet(sheet));
                }

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getProduct_definition().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstProductDefinitionVM(productDefinitionWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getProduct_supplier().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstProductSupplierVM(productSupplierWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSales_channel().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstSalesChannelVM(salesChannelWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getImage_url().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstImageUrlVM(imageUrlWS.readSheet(sheet));
                }

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getProduct_config().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstProductConfigVM(productConfigWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_part_owner().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstSkuPartOwnerVM(skuPartOwnerWS.readSheet(sheet));
                }

                /*
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_brand().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstCompactibleBrandsVM(skuBrandWS.readSheet(sheet));
                }

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_network().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstCompactibleNetworkVM(skuNetworkWS.readSheet(sheet));
                }

                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getAccessory_compactible_sku().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstAccessoryCompactibleVM(accessorySKUWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSim_compactible_sku().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstSimCompactibleVM(simSKUWS.readSheet(sheet));
                }
                if (sheet.getSheetName().equalsIgnoreCase(fileTabNameConfig.getSku_sim_config().toLowerCase())) {
                    skuAttributeDefinitionVM.setLstSimConfigVM(simConfigWS.readSheet(sheet));
                }
                */
            }
            workbook.close();
            fis.close();

        }
        catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (workbook != null)
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
        }
    }

}
