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

public class SalesChannelVM {

	private String model_number;
	private String sku;
	private String channel_code;
	private String brand_code;
	private Date channel_sales_start_date;
	private Date channel_sales_end_date;
	private String channel_price;
	private String channel_cost;

	private String error_status;
	private String error_message;

}
