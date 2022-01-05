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
public class SupplierRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("code")
	private String supplier_code;
	@JsonProperty("name")
	private String supplier_name;
	@JsonProperty("description")
	private String supplier_desc;
	@JsonProperty("ediIsaId")
	private String EDI_ISA_ID;
	@JsonProperty("ediGsId")
	private String EDI_GS_ID;
	private String error_status;
	private String error_message;

}
