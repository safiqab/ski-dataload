package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoveSKUVM {
    private String curr_model_number;
    private String curr_equip_type;
    private String new_model_number;
    private String new_equip_type;
    private String sku;
    private String error_status;
    private String error_message;
}
