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
public class SerialTypeRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("type")
	private String serial_type;
	@JsonProperty("name")
	private String serial_type_name;
	@JsonProperty("description")
	private String serial_type_desc;
	private String error_status;
	private String error_message;

}
