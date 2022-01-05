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
@JsonIgnoreProperties({"model_number","error_status","error_message"})
public class LanguageSupportVM {

	private String model_number;
	@JsonProperty("languageCode")
	private String language_code;
	@JsonProperty("languageProductName")
	private String lang_prod_name;
	@JsonProperty("languageProductDescription")
	private String lang_prod_desc;

	private String error_status;
	private String error_message;

}
