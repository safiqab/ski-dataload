package com.dish.scm.ski.skidataload.model.response;

import com.dish.scm.ski.skidataload.model.file.ModelConfigVM;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkiReference {

    private String id;
    private String refCollectionName;
    private String name;
    private String description;
    private String code;
    private String type;
    private String color;
    private String hexColor;
    private String fullNameCode;
    private String fourDigitCode;
    private String threeDigitCode;
    private String twoDigitCode;
    private String category;
    private String subCategory1;
    private String subCategory2;
    private Long deptId;
    private String equipSubCat;
    private Long locationId;
    private String shipToCode;
    private String locationAddrLine1;
    private String locationAddrLine2;
    private String locationCity;
    private String locationState;
    private long locationPostalCode;
    private long locationCountryCode;
    private String contactPhoneNumber;
    private String contactFaxNumber;
    private String contactFirstName;
    private String contactLastName;
    private long channelId;
    private String manfCode;
    private String simForm;
    private String skuOwner;
    private boolean activatable;
    private String hasUpc;
    private String upcOwner;
    private String hasVendorPartNum;
    private String defaultNameConv;
    private long subDeptId;
    private String ediIsaId;
    private String ediGsId;
    @JsonProperty("ModelConfigVM")
    private List<ModelConfigVM> lstConfigVM;
}
