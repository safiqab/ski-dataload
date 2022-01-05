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
public class DispositionCodeRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("dispositionCode")
	private int disposition_code;
	@JsonProperty("name")
	private String disposition_name;
	@JsonProperty("description")
	private String disposition_desc;
	private String error_status;
	private String error_message;

}
