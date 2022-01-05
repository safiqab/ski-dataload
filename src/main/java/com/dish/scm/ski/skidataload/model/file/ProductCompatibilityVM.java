package com.dish.scm.ski.skidataload.model.file;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCompatibilityVM {

	private String dev_sku;
	private String comp_sku;
	private String error_status;
	private String error_message;

}
