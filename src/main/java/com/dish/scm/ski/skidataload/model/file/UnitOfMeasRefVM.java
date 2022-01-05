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
public class UnitOfMeasRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("code")
	private String unit_of_meas;
	@JsonProperty("name")
	private String unit_of_meas_name;
	@JsonProperty("description")
	private String unit_of_meas_desc;
	private String error_status;
	private String error_message;

}
