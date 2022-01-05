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
public class LocationRefVM {

	private String id;
	private String refCollectionName;
	@JsonProperty("locationId")
	private Long location_id;
	@JsonProperty("name")
	private String location_name;
	@JsonProperty("description")
	private String location_desc;
	@JsonProperty("channelId")
	private long channel_id;
	@JsonProperty("contactFirstName")
	private String contact_first_name;
	@JsonProperty("contactLastName")
	private String contact_last_name;
	@JsonProperty("contactPhoneNumber")
	private String contact_phone_number;
	@JsonProperty("contactFaxNumber")
	private String contact_fax_number;
	@JsonProperty("locationAddrLine1")
	private String location_addr_line1;
	@JsonProperty("locationAddrLine2")
	private String location_addr_line2;
	@JsonProperty("locationCity")
	private String location_city;
	@JsonProperty("locationPostalCode")
	private long location_postal_code;
	@JsonProperty("locationCountryCode")
	private int location_country_code;
	@JsonProperty("locationState")
	private String location_state;
	@JsonProperty("shipToCode")
	private String ship_to_code;
	private String error_status;
	private String error_message;

}
