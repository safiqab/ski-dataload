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
public class ColorRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("color")
	private String color;
	@JsonProperty("name")
	private String color_name;
	@JsonProperty("description")
	private String color_desc;
	@JsonProperty("hexColor")
	private String hex_color;
	@JsonProperty("fullNameCode")
	private String full_name_code;
	@JsonProperty("fourDigitCode")
	private String four_digit_code;
	@JsonProperty("threeDigitCode")
	private String three_digit_code;
	@JsonProperty("twoDigitCode")
	private String two_digit_code;
	private String error_status;
	private String error_message;

}
