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
public class ManufacturerRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("manfCode")
	private String manf_code;
	@JsonProperty("name")
	private String manf_name;
	@JsonProperty("description")
	private String manf_desc;
	private String error_status;
	private String error_message;
	
}
