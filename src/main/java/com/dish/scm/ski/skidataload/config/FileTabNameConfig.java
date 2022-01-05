package com.dish.scm.ski.skidataload.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "worksheet.tabname")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileTabNameConfig {
    private String feature_type_ref;
    private String config_type_ref;
    private String manufacturer_ref;
    private String country_of_orgin_ref;
    private String supplier_ref;
    private String equip_id_ref;
    private String sim_type_ref;
    private String sim_form_ref;
    private String package_type_ref;
    private String sku_type_ref;
    private String unit_of_meas_ref;
    private String color_ref;
    private String color_hex_ref;
    private String dept_id_ref;
    private String sub_dept_id_ref;
    private String os_type_ref;
    private String serial_type_ref;
    private String equip_type_ref;
    private String equip_sub_type_ref;
    private String equip_sub_cat_ref;
    private String lang_code_ref;
    private String brand_code_ref;
    private String location_ref;
    private String channel_ref;
    private String url_type_ref;
    private String ownership_code_ref;
    private String component_type_ref;
    private String profile_type_ref;
    private String transaction_type_ref;
    private String disposition_code_ref;
    private String shipment_status_ref;
    private String unlock_status_ref;
    private String sku_owner_ref;
    private String network_code_ref;
    private String supplier_config_ref;
    private String manufacturer_config_ref;
    private String model_number;
    private String product_definition;
    private String product_supplier;
    private String model_config;
    private String image_url;
    private String product_config;
    private String product_compatibility;
    private String language_support;
    private String sales_channel;
    private String tac_range;
    private String ownership_code_mapping;
    private String kit_components;
    private String model_compatibility;
    private String model_resource_doc;
    private String submit_approval;
    private String approve;
    private String delete;
    private String purge;
    private String stage;
    private String movesku;
    private String hazardous_material;
    private String sku_part_owner;
    private String sku_brand;
    private String sku_network;
    private String sim_compactible_sku;
    private String accessory_compactible_sku;
    private String sku_sim_config;
}
