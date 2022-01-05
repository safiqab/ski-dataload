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
public class ComponentTypeRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("componentType")
	private String component_type;
	@JsonProperty("name")
	private String component_type_name;
	@JsonProperty("description")
	private String component_type_desc;
	private String error_status;
	private String error_message;

}
