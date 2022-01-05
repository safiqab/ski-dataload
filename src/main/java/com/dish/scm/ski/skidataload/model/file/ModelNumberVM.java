package com.dish.scm.ski.skidataload.model.file;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ModelNumberVM {

	@JsonIgnore
	private String model_number;
	@JsonProperty("modelName")
	private String model_name;
	@JsonProperty("modelDescription")
	private String model_desc;
	@JsonProperty("manufacturerCode")
	private String manf_code;
	@JsonProperty("osType")
	private String os_type;
	@JsonProperty("serialType")
	private String serial_type;
	@JsonProperty("additionalLanguage")
	private boolean add_lang_supt_flag;
	@JsonProperty("fccId")
	private String fcc_id;
	@JsonProperty("equipmentId")
	private String equip_id;
	@JsonProperty("equipmentType")
	private String equip_type;
	@JsonProperty("equipmentSubType")
	private String equip_sub_type;
	@JsonProperty("equipmentSubCategory")
	private String equip_sub_cat;
	@JsonProperty("byodSku")
	private String byod;
	@JsonProperty("hazmatRequired")
	private boolean hazmat_required;

	@JsonIgnore
	private String[] networkCode;
	@JsonIgnore
	private ProductDimensionVM productDimensionVM;
	@JsonIgnore
	private String error_status;
	@JsonIgnore
	private String error_message;
}
