package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkCodeRefVM {
    private String network_code;
    private String name;
    private String description;
    private String error_status;
    private String error_message;
}
