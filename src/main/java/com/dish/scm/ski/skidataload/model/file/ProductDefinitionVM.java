package com.dish.scm.ski.skidataload.model.file;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDefinitionVM {

	private String model_number;
	private String sku;
	private String product_name;
	private String product_desc;
	private String marketing_product_name;
	private String sku_type;
	private String parent_sku;
	private String color;
	private String color_hex;
	private String memory_capacity;
	private String memory_unit_of_meas;
	private String upc;
	private String sim_type1;
	private String sim_form1;
	private String sim_type2;
	private String sim_form2;
	private boolean pre_inserted_sim;
	private String config_code;
	private boolean available_to_activate;
	private boolean open_market;
	private boolean online_visible;
	private boolean required_return;
	private Date planned_launch_date;
	private Date do_not_activate_date;
	private boolean available_to_order;
	private boolean available_to_agent;
	private boolean retail_available;
	private String bom_sku;
	private String finance_sku;
	private boolean retail_inventoried;
	private boolean consigned;
	private boolean available_to_report;
	private String product_owner_id;
	private String country_of_orgin;
	private String default_srp;
	private String product_value;
	private boolean available_to_ship;

	private long Product_units;
	private long pallet_product_qty;
	private long pallet_layer_qty;
	private long pallet_layer_master_ctn_qty;
	private long pallet_layer_product_qty;
	private long pallet_master_carton_qty;

	private String master_carton_length;
	private String master_carton_width;
	private String master_carton_height;
	private String master_carton_size_unit_of_meas;
	private String master_carton_weight;
	private String master_carton_weight_unit_of_meas;
	private long master_carton_product_qty;

	private String package_type;
	private String package_length;
	private String package_width;
	private String package_height;
	private String package_size_unit_of_meas;
	private String package_weight;
	private String package_weight_unit_of_meas;

	private String error_status;
	private String error_message;

}
