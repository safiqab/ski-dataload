package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitComponentsVM {

	private String bom_sku;
	private String component_type;
	private String sku;
	private String error_status;
	private String error_message;

}
