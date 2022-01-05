package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelResourceDocVM {
	
	private String model_name;
	private long mrd_requirement_num;
	private String network;
	private String item_category;
	private String subcategory;
	private boolean is_concept_flag;
	private String oem_notes;
	private String category;
	private boolean open_market_flag;
	private String operator;	
	private String error_status;
	private String error_message;

}
