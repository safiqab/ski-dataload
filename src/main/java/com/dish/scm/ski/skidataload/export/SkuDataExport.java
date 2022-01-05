package com.dish.scm.ski.skidataload.export;

import com.dish.scm.ski.skidataload.config.FileTabNameConfig;
import com.dish.scm.ski.skidataload.config.S3Config;
import com.dish.scm.ski.skidataload.model.file.ModelNumVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeDefinitionVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeReferenceVM;
import com.dish.scm.ski.skidataload.config.FileTabNameConfig;
import com.dish.scm.ski.skidataload.config.S3Config;
import com.dish.scm.ski.skidataload.model.file.ModelNumVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeReferenceVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.DateUtil.getDateForFile;
import static com.dish.scm.ski.skidataload.util.FileUtil.deleteAllFiles;

@Component
@Slf4j
public class SkuDataExport {


   private final FileTabNameConfig fileTabNameConfig;
   private final S3Config s3Config;

    private final FeatureTypeRefExport featureTypeRefExport;
    private final ConfigTypeRefExport configTypeRefExport;
    private final ManufacturerRefExport manufacturerRefExport;
    private final SupplierRefExport supplierRefExport;
    private final EquipIdRefExport equipIdRefExport;
    private final SimTypeRefExport simTypeRefExport;
    private final SimFormRefExport simFormRefExport;
    private final PackageTypeRefExport packageTypeRefExport;
    private final SkuTypeRefExport skuTypeRefExport;
    private final UnitOfMeasRefExport unitOfMeasRefExport;
    private final ColorRefExport colorRefExport;
    private final ColorHexRefExport colorHexRefExport;
    private final DeptIdRefExport deptIdRefExport;
    private final SubDeptIdRefExport subDeptIdRefExport;
    private final osTypeRefExport osTypeRefExport;
    private final SerialTypeRefExport serialTypeRefExport;
    private final EquipTypeRefExport equipTypeRefExport;
    private final EquipSubTypeRefExport equipSubTypeRefExport;
    private final EquipSubCatRefExport equipSubCatRefExport;
    private final LangCodeRefExport langCodeRefExport;
    private final BrandCodeRefExport brandCodeRefExport;
    private final LocationRefExport locationRefExport;
    private final ChannelRefExport channelRefExport;
    private final UrlTypeRefExport urlTypeRefExport;
    private final OwnershipCodeRefExport ownershipCodeRefExport;
    private final ComponentTypeRefExport componentTypeRefExport;
    private final ProfileTypeRefExport profileTypeRefExport;
    private final TransactionTypeRefExport transactionTypeRefExport;
    private final DispositionCodeRefExport dispositionCodeRefExport;
    private final ShipmentStatusRefExport shipmentStatusRefExport;
    private final UnlockStatusRefExport unlockStatusRefExport;
    private final CountryOfOrginRefExport countryOfOrginRefExport;
    private final SkuOwnerRefExport skuOwnerRefExport;
    private final NetworkCodeRefExport networkCodeRefExport;
    private final ManufacturerConfigRefExport manufacturerConfigRefExport;
    private final SupplierConfigRefExport supplierConfigRefExport;

    private final ModelNumberExport modelNumberExport;
    private final ProductDefinitionExport productDefinitionExport;
    private final ProductSupplierExport productSupplierExport;
    private final ImageUrlExport imageUrlExport;
    private final SalesChannelExport salesChannelExport;
    private final TacRangeExport tacRangeExport;
    private final ModelConfigExport modelConfigExport;
    private final ProductConfigExport productConfigExport;
    private final LanguageSupportExport languageSupportExport;
    private final ModelNameExport modelNameExport;
    private final HazardousMaterialExport hazardousMaterialExport;
    private final SkuPartOwnerExport skuPartOwnerExport;

    @Autowired
    public SkuDataExport(S3Config s3Config, ManufacturerRefExport manufacturerRefExport, FeatureTypeRefExport featureTypeRefExport, EquipSubCatRefExport equipSubCatRefExport, ProductDefinitionExport productDefinitionExport, DeptIdRefExport deptIdRefExport, ChannelRefExport channelRefExport, SkuOwnerRefExport skuOwnerRefExport, TacRangeExport tacRangeExport, ModelNameExport modelNameExport, ProductSupplierExport productSupplierExport, SalesChannelExport salesChannelExport, DispositionCodeRefExport dispositionCodeRefExport, SubDeptIdRefExport subDeptIdRefExport, ConfigTypeRefExport configTypeRefExport, EquipTypeRefExport equipTypeRefExport, osTypeRefExport osTypeRefExport, TransactionTypeRefExport transactionTypeRefExport, OwnershipCodeRefExport ownershipCodeRefExport, ShipmentStatusRefExport shipmentStatusRefExport, SupplierRefExport supplierRefExport, EquipIdRefExport equipIdRefExport, SimTypeRefExport simTypeRefExport, ProfileTypeRefExport profileTypeRefExport, BrandCodeRefExport brandCodeRefExport, ComponentTypeRefExport componentTypeRefExport, ModelNumberExport modelNumberExport, SimFormRefExport simFormRefExport, LocationRefExport locationRefExport, CountryOfOrginRefExport countryOfOrginRefExport, UnlockStatusRefExport unlockStatusRefExport, NetworkCodeRefExport networkCodeRefExport, LanguageSupportExport languageSupportExport, SerialTypeRefExport serialTypeRefExport, PackageTypeRefExport packageTypeRefExport, SkuTypeRefExport skuTypeRefExport, UnitOfMeasRefExport unitOfMeasRefExport, LangCodeRefExport langCodeRefExport, EquipSubTypeRefExport equipSubTypeRefExport, ManufacturerConfigRefExport manufacturerConfigRefExport, SupplierConfigRefExport supplierConfigRefExport, ImageUrlExport imageUrlExport, ColorRefExport colorRefExport, UrlTypeRefExport urlTypeRefExport,
                         FileTabNameConfig fileTabNameConfig, ColorHexRefExport colorHexRefExport, ModelConfigExport modelConfigExport, ProductConfigExport productConfigExport, HazardousMaterialExport hazardousMaterialExport, SkuPartOwnerExport skuPartOwnerExport) {
        this.s3Config = s3Config;
        this.manufacturerRefExport = manufacturerRefExport;
        this.featureTypeRefExport = featureTypeRefExport;
        this.equipSubCatRefExport = equipSubCatRefExport;
        this.productDefinitionExport = productDefinitionExport;
        this.deptIdRefExport = deptIdRefExport;
        this.channelRefExport = channelRefExport;
        this.skuOwnerRefExport = skuOwnerRefExport;
        this.tacRangeExport = tacRangeExport;
        this.modelNameExport = modelNameExport;
        this.productSupplierExport = productSupplierExport;
        this.salesChannelExport = salesChannelExport;
        this.dispositionCodeRefExport = dispositionCodeRefExport;
        this.subDeptIdRefExport = subDeptIdRefExport;
        this.configTypeRefExport = configTypeRefExport;
        this.equipTypeRefExport = equipTypeRefExport;
        this.osTypeRefExport = osTypeRefExport;
        this.transactionTypeRefExport = transactionTypeRefExport;
        this.ownershipCodeRefExport = ownershipCodeRefExport;
        this.shipmentStatusRefExport = shipmentStatusRefExport;
        this.supplierRefExport = supplierRefExport;
        this.equipIdRefExport = equipIdRefExport;
        this.simTypeRefExport = simTypeRefExport;
        this.profileTypeRefExport = profileTypeRefExport;
        this.brandCodeRefExport = brandCodeRefExport;
        this.componentTypeRefExport = componentTypeRefExport;
        this.modelNumberExport = modelNumberExport;
        this.simFormRefExport = simFormRefExport;
        this.locationRefExport = locationRefExport;
        this.countryOfOrginRefExport = countryOfOrginRefExport;
        this.unlockStatusRefExport = unlockStatusRefExport;
        this.networkCodeRefExport = networkCodeRefExport;
        this.languageSupportExport = languageSupportExport;
        this.serialTypeRefExport = serialTypeRefExport;
        this.packageTypeRefExport = packageTypeRefExport;
        this.skuTypeRefExport = skuTypeRefExport;
        this.unitOfMeasRefExport = unitOfMeasRefExport;
        this.langCodeRefExport = langCodeRefExport;
        this.equipSubTypeRefExport = equipSubTypeRefExport;
        this.manufacturerConfigRefExport = manufacturerConfigRefExport;
        this.supplierConfigRefExport = supplierConfigRefExport;
        this.imageUrlExport = imageUrlExport;
        this.colorRefExport = colorRefExport;
        this.urlTypeRefExport = urlTypeRefExport;
        this.fileTabNameConfig = fileTabNameConfig;
        this.colorHexRefExport = colorHexRefExport;
        this.modelConfigExport = modelConfigExport;
        this.productConfigExport = productConfigExport;
        this.hazardousMaterialExport = hazardousMaterialExport;
        this.skuPartOwnerExport = skuPartOwnerExport;
    }


    public String exportData(String inputFileName, SkuAttributeReferenceVM skuAttributeReferenceVM, SkuAttributeDefinitionVM skuAttributeDefinitionVM,
                             List<ModelNumVM> lstModelNameDelete, List<ModelNumVM> lstModelNamePurge, List<ModelNumVM> lstModelNameSA, List<ModelNumVM> lstModelNameApprove,
                             List<ModelNumVM> lstModelNameStage) {

        File file;
        OutputStream fos =null;
        XSSFWorkbook wb = null;
        Sheet sheet;
        String file_output_location;
        String outputFile = null;
        try {

            if (System.getProperty("os.name").startsWith("Window")) {
                file_output_location = this.s3Config.getWin_location().getOutput();
            }
            else {
                file_output_location = this.s3Config.getLinux_location().getOutput();
            }
            deleteAllFiles(file_output_location,0);
            outputFile = file_output_location +  inputFileName.replace(".xlsx", "_" + getDateForFile() +".xlsx" );

            file = new File(outputFile);
            if (file.exists()) {
                wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
            }
            else {
                wb = new XSSFWorkbook();
            }

            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstFeatureTypeRefVM()) && skuAttributeReferenceVM.getLstFeatureTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getFeature_type_ref());
                featureTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstFeatureTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstConfigTypeRefVM()) && skuAttributeReferenceVM.getLstConfigTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getConfig_type_ref());
                configTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstConfigTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerRefVM()) && skuAttributeReferenceVM.getLstManufacturerRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getManufacturer_ref());
                manufacturerRefExport.writeData(sheet, skuAttributeReferenceVM.getLstManufacturerRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierRefVM()) && skuAttributeReferenceVM.getLstSupplierRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSupplier_ref());
                supplierRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSupplierRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipIdRefVM()) && skuAttributeReferenceVM.getLstEquipIdRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getEquip_id_ref());
                equipIdRefExport.writeData(sheet, skuAttributeReferenceVM.getLstEquipIdRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimTypeRefVM()) && skuAttributeReferenceVM.getLstSimTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSim_type_ref());
                simTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSimTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSimFormRefVM()) && skuAttributeReferenceVM.getLstSimFormRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSim_form_ref());
                simFormRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSimFormRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstPackageTypeRefVM()) && skuAttributeReferenceVM.getLstPackageTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getPackage_type_ref());
                packageTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstPackageTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuTypeRefVM()) && skuAttributeReferenceVM.getLstSkuTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSku_type_ref());
                skuTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSkuTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnitOfMeasRefVM()) && skuAttributeReferenceVM.getLstUnitOfMeasRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getUnit_of_meas_ref());
                unitOfMeasRefExport.writeData(sheet, skuAttributeReferenceVM.getLstUnitOfMeasRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorRefVM()) && skuAttributeReferenceVM.getLstColorRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getColor_ref());
                colorRefExport.writeData(sheet, skuAttributeReferenceVM.getLstColorRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstColorHexRefVM()) && skuAttributeReferenceVM.getLstColorHexRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getColor_hex_ref());
                colorHexRefExport.writeData(sheet, skuAttributeReferenceVM.getLstColorHexRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDeptIdRefVM()) && skuAttributeReferenceVM.getLstDeptIdRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getDept_id_ref());
                deptIdRefExport.writeData(sheet, skuAttributeReferenceVM.getLstDeptIdRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSubDeptIdRefVM()) && skuAttributeReferenceVM.getLstSubDeptIdRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSub_dept_id_ref());
                subDeptIdRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSubDeptIdRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOsTypeRefVM()) && skuAttributeReferenceVM.getLstOsTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getOs_type_ref());
                osTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstOsTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSerialTypeRefVM()) && skuAttributeReferenceVM.getLstSerialTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSerial_type_ref());
                serialTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSerialTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipTypeRefVM()) && skuAttributeReferenceVM.getLstEquipTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getEquip_type_ref());
                equipTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstEquipTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipsubTypeRefVM()) && skuAttributeReferenceVM.getLstEquipsubTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getEquip_sub_type_ref());
                equipSubTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstEquipsubTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstEquipSubCatRefVM()) && skuAttributeReferenceVM.getLstEquipSubCatRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getEquip_sub_cat_ref());
                equipSubCatRefExport.writeData(sheet, skuAttributeReferenceVM.getLstEquipSubCatRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLangcodeRefVM()) && skuAttributeReferenceVM.getLstLangcodeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getLang_code_ref());
                langCodeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstLangcodeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstBrandcodeRefVM()) && skuAttributeReferenceVM.getLstBrandcodeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getBrand_code_ref());
                brandCodeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstBrandcodeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstLocationRefVM()) && skuAttributeReferenceVM.getLstLocationRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getLocation_ref());
                locationRefExport.writeData(sheet, skuAttributeReferenceVM.getLstLocationRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstChannelRefVM()) && skuAttributeReferenceVM.getLstChannelRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getChannel_ref());
                channelRefExport.writeData(sheet, skuAttributeReferenceVM.getLstChannelRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUrlTypeRefVM()) && skuAttributeReferenceVM.getLstUrlTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getUrl_type_ref());
                urlTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstUrlTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstOwnershipcodeRefVM()) && skuAttributeReferenceVM.getLstOwnershipcodeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getOwnership_code_ref());
                ownershipCodeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstOwnershipcodeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstComponentTypeRefVM()) && skuAttributeReferenceVM.getLstComponentTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getComponent_type_ref());
                componentTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstComponentTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstProfileTypeRefVM()) && skuAttributeReferenceVM.getLstProfileTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getProfile_type_ref());
                profileTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstProfileTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstTransactionTypeRefVM()) && skuAttributeReferenceVM.getLstTransactionTypeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getTransaction_type_ref());
                transactionTypeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstTransactionTypeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstCountryOfOrginRefVM()) && skuAttributeReferenceVM.getLstCountryOfOrginRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getCountry_of_orgin_ref());
                countryOfOrginRefExport.writeData(sheet, skuAttributeReferenceVM.getLstCountryOfOrginRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstDispositioncodeRefVM()) && skuAttributeReferenceVM.getLstDispositioncodeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getDisposition_code_ref());
                dispositionCodeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstDispositioncodeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstShipmentstatusRefVM()) && skuAttributeReferenceVM.getLstShipmentstatusRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getShipment_status_ref());
                shipmentStatusRefExport.writeData(sheet, skuAttributeReferenceVM.getLstShipmentstatusRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstUnlockStatusRefVM()) && skuAttributeReferenceVM.getLstUnlockStatusRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getUnlock_status_ref());
                unlockStatusRefExport.writeData(sheet, skuAttributeReferenceVM.getLstUnlockStatusRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSkuOwnerRefVM()) && skuAttributeReferenceVM.getLstSkuOwnerRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSku_owner_ref());
                skuOwnerRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSkuOwnerRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstManufacturerConfigRefVM()) && skuAttributeReferenceVM.getLstManufacturerConfigRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getManufacturer_config_ref());
                manufacturerConfigRefExport.writeData(sheet, skuAttributeReferenceVM.getLstManufacturerConfigRefVM());
            }

            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstSupplierConfigRefVM()) && skuAttributeReferenceVM.getLstSupplierConfigRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSupplier_config_ref());
                supplierConfigRefExport.writeData(sheet, skuAttributeReferenceVM.getLstSupplierConfigRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeReferenceVM.getLstNetworkCodeRefVM()) && skuAttributeReferenceVM.getLstNetworkCodeRefVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getNetwork_code_ref());
                networkCodeRefExport.writeData(sheet, skuAttributeReferenceVM.getLstNetworkCodeRefVM());
            }
            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelNumberVM()) && skuAttributeDefinitionVM.getLstModelNumberVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getModel_number());
                modelNumberExport.writeData(sheet,skuAttributeDefinitionVM.getLstModelNumberVM());
                skuAttributeDefinitionVM.getLstModelNumberVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductDefinitionVM()) && skuAttributeDefinitionVM.getLstProductDefinitionVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getProduct_definition());
                productDefinitionExport.writeData(sheet,skuAttributeDefinitionVM.getLstProductDefinitionVM());
                skuAttributeDefinitionVM.getLstProductDefinitionVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductSupplierVM()) && skuAttributeDefinitionVM.getLstProductSupplierVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getProduct_supplier());
                productSupplierExport.writeData(sheet,skuAttributeDefinitionVM.getLstProductSupplierVM());
                skuAttributeDefinitionVM.getLstProductSupplierVM().clear();
            }


            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstImageUrlVM()) && skuAttributeDefinitionVM.getLstImageUrlVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getImage_url());
                imageUrlExport.writeData(sheet,skuAttributeDefinitionVM.getLstImageUrlVM());
                skuAttributeDefinitionVM.getLstImageUrlVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstLanguageSupportVM()) && skuAttributeDefinitionVM.getLstLanguageSupportVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getLanguage_support());
                languageSupportExport.writeData(sheet,skuAttributeDefinitionVM.getLstLanguageSupportVM());
                skuAttributeDefinitionVM.getLstLanguageSupportVM().clear();
            }


            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSalesChannelVM()) && skuAttributeDefinitionVM.getLstSalesChannelVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSales_channel());
                salesChannelExport.writeData(sheet,skuAttributeDefinitionVM.getLstSalesChannelVM());
                skuAttributeDefinitionVM.getLstSalesChannelVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstTacRangeVM()) && skuAttributeDefinitionVM.getLstTacRangeVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getTac_range());
                tacRangeExport.writeData(sheet,skuAttributeDefinitionVM.getLstTacRangeVM());
                skuAttributeDefinitionVM.getLstTacRangeVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstModelConfigVM()) && skuAttributeDefinitionVM.getLstModelConfigVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getModel_config());
                modelConfigExport.writeData(sheet,skuAttributeDefinitionVM.getLstModelConfigVM());
                skuAttributeDefinitionVM.getLstModelConfigVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstProductConfigVM()) && skuAttributeDefinitionVM.getLstProductConfigVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getProduct_config());
                productConfigExport.writeData(sheet,skuAttributeDefinitionVM.getLstProductConfigVM());
                skuAttributeDefinitionVM.getLstProductConfigVM().clear();
            }

            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstHazardousMaterialVM()) && skuAttributeDefinitionVM.getLstHazardousMaterialVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getHazardous_material());
                hazardousMaterialExport.writeData(sheet,skuAttributeDefinitionVM.getLstHazardousMaterialVM());
                skuAttributeDefinitionVM.getLstHazardousMaterialVM().clear();
            }
            if (!StringUtils.isEmpty(skuAttributeDefinitionVM.getLstSkuPartOwnerVM()) && skuAttributeDefinitionVM.getLstSkuPartOwnerVM().size()>0 ) {
                sheet = wb.createSheet(fileTabNameConfig.getSku_part_owner());
                skuPartOwnerExport.writeData(sheet,skuAttributeDefinitionVM.getLstSkuPartOwnerVM());
                skuAttributeDefinitionVM.getLstSkuPartOwnerVM().clear();
            }


            if (!StringUtils.isEmpty(lstModelNameDelete) && lstModelNameDelete.size()>0) {
                sheet = wb.createSheet(fileTabNameConfig.getDelete());
                modelNameExport.writeData(sheet, lstModelNameDelete);
            }
            if (!StringUtils.isEmpty(lstModelNamePurge) && lstModelNamePurge.size()>0) {
                sheet = wb.createSheet(fileTabNameConfig.getPurge());
                modelNameExport.writeData(sheet, lstModelNamePurge);
            }
            if (!StringUtils.isEmpty(lstModelNameSA) && lstModelNameSA.size()>0) {
                sheet = wb.createSheet(fileTabNameConfig.getSubmit_approval());
                modelNameExport.writeData(sheet, lstModelNameSA);
            }
            if (!StringUtils.isEmpty(lstModelNameApprove) && lstModelNameApprove.size()>0) {
                sheet = wb.createSheet(fileTabNameConfig.getApprove());
                modelNameExport.writeData(sheet, lstModelNameApprove);
            }
            if (!StringUtils.isEmpty(lstModelNameStage) && lstModelNameStage.size()>0) {
                sheet = wb.createSheet(fileTabNameConfig.getStage());
                modelNameExport.writeData(sheet, lstModelNameStage);
            }

            fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        finally  {
            try {

                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException e) {
                log.error(e.getMessage());
            }
            try {
                if (wb != null) {
                    wb.close();
                }
            }
            catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return outputFile;
    }
}
