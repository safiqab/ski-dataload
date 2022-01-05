package com.dish.scm.ski.skidataload.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConfigurationProperties(prefix = "ski-reference.collection-name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkiReferenceNameConfig {
    private String BRAND_CODE_REF;
    private String CHANNEL_REF;
    private String COLOR_HEX_REF;
    private String COLOR_REF;
    private String COMPONENT_TYPE_REF;
    private String CONFIG_TYPE_REF;
    private String COUNTRY_OF_ORIGIN_REF;
    private String DEPT_ID_REF;
    private String DISPOSITION_CODE_REF;
    private String EQUIP_ID_REF;
    private String EQUIP_SUB_CAT_REF;
    private String EQUIP_SUB_TYPE_REF;
    private String EQUIP_TYPE_REF;
    private String FEATURE_TYPE_REF;
    private String LANG_CODE_REF;
    private String LOCATION_REF;
    private String MANUFACTURER_REF;
    private String NETWORK_CODE;
    private String OS_TYPE;
    private String OWNERSHIP_CODE_REF;
    private String PACKAGE_TYPE_REF;
    private String PROFILE_TYPE_REF;
    private String SERIAL_TYPE_REF;
    private String SHIPMENT_STATUS_REF;
    private String SIM_FORM_REF;
    private String SIM_TYPE_REF;
    private String SKU_OWNER_REF;
    private String SKU_TYPE;
    private String SUB_DEPT_ID;
    private String SUPPLIER_CODE;
    private String TRANSACTION_TYPE_REF;
    private String UNIT_OF_MEAS_REF;
    private String UNLOCK_STATUS_REF;
    private String URL_TYPE_REF;

}
