package com.dish.scm.ski.skidataload.model.file;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties({"model_number","error_status","error_message"})
public class ModelConfigVM {

	private String model_number;
	@JsonProperty("configType")
	private String configType;
	@JsonProperty("configValue")
	private String configValue;

	private String error_status;
	private String error_message;
}
