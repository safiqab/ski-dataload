package com.dish.scm.ski.skidataload.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audit {
    private String applicationCreated;
    private String dateCreated;
    private String userCreated;
    private String applicationModified;
    private String dateModified;
    private String userModified;
}
