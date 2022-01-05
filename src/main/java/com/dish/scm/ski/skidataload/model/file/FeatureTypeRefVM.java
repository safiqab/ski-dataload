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
public class FeatureTypeRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("featureType")
	private String feature_type;
	@JsonProperty("name")
	private String feature_name;
	@JsonProperty("description")
	private String feature_desc;
	private String error_status;
	private String error_message;

}
