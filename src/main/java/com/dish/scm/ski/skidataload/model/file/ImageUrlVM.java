package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUrlVM {

	private String model_number;
	private String sku;
	private String url_type;
	private String image_url;
	private String image_alt_text;

	private String error_status;
	private String error_message;

}
