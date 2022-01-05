package com.dish.scm.ski.skidataload.model.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"error_status","error_message"})
public class SkuTypeRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("skuType")
	private String sku_type;
	@JsonProperty("name")
	private String sku_type_name;
	@JsonProperty("description")
	private String sku_type_desc;
	@JsonProperty("defaultNameConv")
	private String default_name_conv;
	@JsonProperty("hasVendorPartNum")
	private boolean has_vendor_part_num;
	@JsonProperty("hasUpc")
	private boolean has_upc;
	@JsonProperty("upcOwner")
	private String upc_owner;
	@JsonProperty("activatable")
	private boolean activatable;
	private String error_status;
	private String error_message;

}
