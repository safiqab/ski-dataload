package com.dish.scm.ski.skidataload.model.file;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductConfigVM {

	private String model_number;
	private String equip_type;
	private String sku;
	private String config_type;
	private String config_value;
	private String error_status;
	private String error_message;

	private String intent;
	private String config_type_refid;
	private String product_config_publicid;
	private String product_definition_publicid;
}
