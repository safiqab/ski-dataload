package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnershipCodeMappingVM {
	
	private String ownership_code;
	private String ship_to;
	private String sold_to;
	private String error_status;
	private String error_message;

}
