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
public class UnlockStatusRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("code")
	private long unlock_status_code;
	@JsonProperty("name")
	private String unlock_status_name;
	@JsonProperty("description")
	private String unlock_status_desc;
	private String error_status;
	private String error_message;

}
