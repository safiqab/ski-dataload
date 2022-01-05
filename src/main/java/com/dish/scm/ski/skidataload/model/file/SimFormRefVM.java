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
public class SimFormRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("simForm")
	private String sim_form;
	@JsonProperty("name")
	private String sim_form_name;
	@JsonProperty("description")
	private String sim_form_desc;
	private String error_status;
	private String error_message;

}
