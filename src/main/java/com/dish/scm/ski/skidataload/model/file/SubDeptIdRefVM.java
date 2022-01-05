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
public class SubDeptIdRefVM {
	private String id;
	private String refCollectionName;
	@JsonProperty("subDeptId")
	private long sub_dept_id;
	@JsonProperty("name")
	private String sub_dept_name;
	@JsonProperty("description")
	private String sub_dept_desc;	
	private String error_status;
	private String error_message;

}
