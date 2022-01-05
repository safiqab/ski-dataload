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
@JsonIgnoreProperties({"model_number","error_status","error_message"})
public class HazardousMaterialVM {
    private String model_number;
    @JsonProperty("htsCodeUs")
    private String hts_code_us;
    @JsonProperty("eccnCode")
    private String eccn_code;
    @JsonProperty("hazmatCommodityType")
    private String hazmat_commodity_type;
    @JsonProperty("batteryType")
    private String battery_type;
    @JsonProperty("batteryForm")
    private String battery_form;
    @JsonProperty("batteryCellCount")
    private long battery_cell_count;
    @JsonProperty("batteryPackaging")
    private String battery_packaging;
    @JsonProperty("batteryVoltage")
    private long battery_voltage;
    @JsonProperty("hazmatUnNumber")
    private long hazmat_un_number;
    @JsonProperty("piNumber")
    private long pi_number;
    @JsonProperty("liIonWattHours")
    private long li_ion_watt_hours;
    @JsonProperty("totalBatteryKgs")
    private long total_battery_kgs;

    private String error_status;
    private String error_message;
}
