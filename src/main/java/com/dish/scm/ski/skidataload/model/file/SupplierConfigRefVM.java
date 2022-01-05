package com.dish.scm.ski.skidataload.model.file;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierConfigRefVM {
	
	private String supplier_code;
	private String config_type;
	private String config_value;
	private String error_status;
	private String error_message;
}
