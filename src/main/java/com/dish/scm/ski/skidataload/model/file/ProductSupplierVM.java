package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductSupplierVM {

	private String model_number;
	private String sku;
	private String supplier_code;
	private String supplier_part_number;
	private Date supplier_enable_date;
	private Date supplier_disable_date;
	private String default_supplier_cost;

	private String error_status;
	private String error_message;
	
}
