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
public class ConfigTypeRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("configType")
	private String config_type;
	@JsonProperty("name")
	private String config_name;
	@JsonProperty("description")
	private String config_desc;
	@JsonProperty("category")
	private String category;
	@JsonProperty("subCategory1")
	private String subcategory1;
	@JsonProperty("subCategory2")
	private String subcategory2;
	private String error_status;
	private String error_message;

}
