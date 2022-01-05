package com.dish.scm.ski.skidataload.connector;

import com.dish.scm.ski.skidataload.config.ApiEndPointConfig;
import com.dish.scm.ski.skidataload.config.ApiConfig;
//import com.dish.scm.ski.skidataload.model.api.response.*;
import com.dish.scm.ski.skidataload.model.request.ModelData;
import com.dish.scm.ski.skidataload.model.response.ErrorMessage;
import com.dish.scm.ski.skidataload.model.response.SkiReference;
import com.dish.scm.ski.skidataload.model.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.dish.scm.ski.skidataload.util.RequestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dish.scm.ski.skidataload.util.ApiUtil.getJsonHeaders;

@Component
@Slf4j
public class SkiApiClient {

    private final ApiConnector apiConnector;
    private final ApiEndPointConfig apiEndPointConfig;
    private final ApiConfig apiConfig;
    private final HttpRestClientImpl httpRestClientImpl;

    @Autowired
    public SkiApiClient(ApiConnector apiConnector,
                        ApiConfig apiConfig,
                        ApiEndPointConfig apiEndPointConfig, HttpRestClientImpl httpRestClientImpl) {
        this.apiConnector = apiConnector;
        this.apiEndPointConfig = apiEndPointConfig;
        this.apiConfig = apiConfig;
        this.httpRestClientImpl = httpRestClientImpl;
    }

    public List<SkiReference> getReferenceDataApi(String factType) {

        String url = apiEndPointConfig.getSkireference().getGet_reference_url();
        url = url + (url.endsWith("/") ? factType : "/" + factType);
        int cnt = 0;
        HttpHeaders headers = getJsonHeaders();
        if (StringUtils.isEmpty(headers))
            return null;
        while (true) {
            ResponseEntity<String> resp = httpRestClientImpl.callApi(RequestUtil.get(url, headers), String.class);
            if (!StringUtils.isEmpty(resp) && resp.getStatusCodeValue() == 200 && !StringUtils.isEmpty(resp.getBody())) {

                ObjectMapper mapper = new ObjectMapper();
                try {
                    return Arrays.asList(mapper.readValue(resp.getBody(),SkiReference[].class));
                } catch (JsonProcessingException e) {
                    log.info("Unable to parse json array for getReferenceDataApi({}) response:{}",factType,resp.getBody());
                    e.printStackTrace();
                }
            } else {
                if (cnt < 4) {
                    cnt++;
                }
                else
                    return null;
            }
        }
    }


    public String addReferenceDataApi(SkiReference req,String type) {

        String url = apiEndPointConfig.getSkireference().getPost_reference_url();

        HttpHeaders headers = getJsonHeaders();
        if (StringUtils.isEmpty(headers))
            return null;

        RequestEntity<SkiReference> req1 = type.equalsIgnoreCase("new") ?
                RequestUtil.post(apiEndPointConfig.getSkireference().getPost_reference_url(), req, headers)
                : RequestUtil.patch(apiEndPointConfig.getSkireference().getPost_reference_url(), req, headers);

        while (true) {
            ResponseEntity<String> resp = this.httpRestClientImpl.callApi(req1, String.class);
            if (!StringUtils.isEmpty(resp)) {
                if (resp.getStatusCodeValue() == 201)
                    return "Success";
                else if (resp.getStatusCodeValue() == 400) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        ErrorResponse errorResponse = mapper.readValue(resp.getBody(), ErrorResponse.class);
                        if (StringUtils.isEmpty(errorResponse))
                            return "Unable to add reference";
                        return errorResponse.getLstStatus().stream().map(M->M.getMessage())
                                    .collect(Collectors.joining(","));
                    } catch (JsonProcessingException e) {
                        log.info("Unable to parse response for addReferenceDataApi response:{}",resp.getBody());
                        e.printStackTrace();
                        return "Unable to add reference";
                    }
                }
            }
            else {
                return "Unable to add reference";
            }
        }
    }


    public ModelData getModelDataApi(String modelName) {

        String url = apiEndPointConfig.getSkimodel().getGet_model_url();
        url = url + (url.endsWith("/") ? modelName : "/" + modelName);
        int cnt = 0;
        HttpHeaders headers = getJsonHeaders();
        if (StringUtils.isEmpty(headers))
            return null;
        while (true) {
            ResponseEntity<ModelData> resp = httpRestClientImpl.callApi(RequestUtil.get(url, headers), ModelData.class);
            if (!StringUtils.isEmpty(resp) && resp.getStatusCodeValue() == 200 && !StringUtils.isEmpty(resp.getBody())) {
                return resp.getBody();
            } else {
                if (cnt < 4) {
                    cnt++;
                }
                else
                    return null;
            }
        }
    }

    public String addModelDataApi(ModelData req,String type) {

        int cnt =1;
        String url = apiEndPointConfig.getSkimodel().getPost_model_url();

        HttpHeaders headers = getJsonHeaders();
        if (StringUtils.isEmpty(headers))
            return null;

        RequestEntity<ModelData> req1 = type.equalsIgnoreCase("new") ?
                RequestUtil.post(apiEndPointConfig.getSkimodel().getPost_model_url(), req, headers)
                : RequestUtil.patch(apiEndPointConfig.getSkimodel().getPost_model_url(), req, headers);

        while (true) {
            ResponseEntity<ModelData> resp = this.httpRestClientImpl.callApi(req1, ModelData.class);
            if (!StringUtils.isEmpty(resp) && resp.getStatusCodeValue() == 201) {
                return "Success";
            } else {
                if (cnt < 4) {
                    cnt++;
                }
                else
                    return "Failed";
            }
        }
    }


    /*
    private OutputStatus callFactInsertApi(String reqeust) {
        if (StringUtils.isEmpty(reqeust))
            return OutputStatus.builder().errorStatus(appConfig.getImport_status_failed())
                    .errorMessage("Unable to insert fact data").build();
        else
            return insertFactData(reqeust,1);
    }

    public OutputStatus callBrandCodeRefApi(BrandCodeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getBrandCodeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callChannelRefApi(ChannelRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getChannelRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callColorRefApi(ColorRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getColorRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callColorHexRefApi(ColorHexRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getColorHexRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callComponentTypeRefApi(ComponentTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getComponentTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callConfigTypeRefApi(ConfigTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getConfigTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callCountryOfOrginRefApi(CountryOfOrginRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getCountryOfOrginRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callDeptIdRefApi(DeptIdRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getDeptIdRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callDispositionCodeRefApi(DispositionCodeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getDispositionRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callEquipIdRefApi(EquipIdRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getEquipIdRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callEquipSubCatRefApi(EquipSubCatRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getEquipSubCatRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callEquipSubTypeRefApi(EquipSubTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getEquipSubTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callEquipTypeRefApi(EquipTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getEquipTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callFeatureTypeRefApi(FeatureTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getFeatureTypeRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callLangCodeRefApi(LangCodeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getLangCodeRefRequestXML(refData,intent,publicId));
    }



    public OutputStatus callLocationRefApi(LocationRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getLocationRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callManufacturerRefApi(ManufacturerRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getManfCodeRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callOsTypeRefApi(OsTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getOsTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callOwnershipCodeRefApi(OwnershipCodeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getOwnerShipCodeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callPackageTypeRefApi(PackageTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getPackageTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callProfileTypeRefApi(ProfileTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getProfileTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSerialTypeRefApi(SerialTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSerialTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callShipmentStatusRefApi(ShipmentStatusRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getShipmentStatusRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSimFormRefApi(SimFormRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSimFormRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSimTypeRefApi(SimTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSimTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSkuTypeRefApi(SkuTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSkuTypeRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callSubDeptIdRefApi(SubDeptIdRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSubDeptIdRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSupplierRefApi(SupplierRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSupplierRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callTransactionTypeRefApi(TransactionTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getTransactionTypeRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callUnitOfMeasRefApi(UnitOfMeasRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getUnitMeasRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callUnlockStatusRefApi(UnlockStatusRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getUnlockStatusRefRequestXML(refData,intent,publicId));
    }


    public OutputStatus callUrlTypeRefApi(UrlTypeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getUrlTypeRefRequestXML(refData,intent,publicId));
    }

    public OutputStatus callSkuOwnerRefApi(SkuOwnerRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSkuOwnerRequestXML(refData,intent,publicId));
    }

    public OutputStatus callNetworkCodeRefApi(NetworkCodeRefVM refData, String intent, String publicId) {
        return callFactInsertApi(this.skuReferenceRequestXml.getNetworkCodeRequestXML(refData,intent,publicId));
    }

    public OutputStatus callManfConfigRefApi(List<ManufacturerConfigRefVM> lstRefData) {
        return callFactInsertApi(this.skuReferenceRequestXml.getManfConfigXML(lstRefData));
    }

    public OutputStatus callSupplierConfigRefApi(List<SupplierConfigRefVM> lstRefData) {
        return callFactInsertApi(this.skuReferenceRequestXml.getSupplierConfigXML(lstRefData));
    }

    public List<FactReferences> getFactDataApi(String factType, int cnt) {

        try {

            String url = apiEndPointConfig.getFact_references_url();
            url = url.replace("{}",factType);

            HttpHeaders headers = this.hansenApiConnector.getHeadersForHansen();
            if (StringUtils.isEmpty(headers))
                return null;

            ResponseEntity<String> response =  this.hansenApiConnector.getFactData(url,headers);
            if (!StringUtils.isEmpty(response)) {
                if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    if (cnt<4) {
                        this.hansenApiConnector.resetToken();
                        getFactDataApi(factType, cnt++);
                    }
                }
                else if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
                    if (cnt<4) {
                        getFactDataApi(factType, cnt++);
                    }
                }
                else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    try {
                        JSONObject jobj = new JSONObject(response.getBody());
                        JSONArray JARRAY = jobj.getJSONArray("Messages");
                        if (!StringUtils.isEmpty(JARRAY)) {
                            StringBuilder errMsg = new StringBuilder();
                            for (int i = 0; i < JARRAY.length(); i++) {
                                errMsg.append(JARRAY.getJSONObject(i).getString("Message"));
                            }
                            log.error("Bad Request:{}",errMsg.toString());
                        }
                    }
                    catch (JSONException je) {
                        log.error("Json exception:{}",je.getMessage());
                    }
                }
                else if (response.getStatusCode() == HttpStatus.OK) {
                    try {
                        List<FactReferences> lstRef = new ArrayList<>();
                        JSONObject jobj = new JSONObject(response.getBody());
                        if (!StringUtils.isEmpty(jobj)) {
                            JSONObject jres = jobj.getJSONObject("Response");
                            if (!StringUtils.isEmpty(jres)) {
                                JSONArray JARRAY = jres.getJSONArray ("References");
                                if (!StringUtils.isEmpty(JARRAY)) {
                                    for (int i=0;i<JARRAY.length();i++ ) {

                                        lstRef.add(FactReferences.builder().Name(JARRAY.getJSONObject(i).getString("Name"))
                                                .PublicID(JARRAY.getJSONObject(i).getString("PublicID")).build());
                                    }
                                }
                            }
                        }
                        return lstRef;
                    }
                    catch (JSONException je) {
                        log.error("Json exception:{}",je.getMessage());
                    }
                }
            }
        }
        catch (Exception e) {
            log.error("Exception:{}",e.getMessage());
        }
        return null;
    }


    public ManufacturerConfigRefData callManufRefFactData(String publicID) {
        ManufacturerConfigRefData mnData = null;
        try {
            ResponseEntity<String> response = getFactData(publicID,1);

            if (!StringUtils.isEmpty(response)) {

                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject respBody = new JSONObject(response.getBody());
                    JSONObject jres = respBody.getJSONObject("Response");
                    if (!StringUtils.isEmpty(jres)) {
                        mnData = new ManufacturerConfigRefData();
                        mnData.setManu_code(jres.getString("Name"));
                        mnData.setManu_code_publicid(jres.getString("PublicID"));

                        JSONArray JARRAY = jres.getJSONArray("ItemElements");
                        if (!StringUtils.isEmpty(JARRAY) && JARRAY.length()>0 ) {
                            for (int i = 0; i < JARRAY.length(); i++) {

                                if (JARRAY.getJSONObject(i).getString("Name").equalsIgnoreCase("CONFIG_TYPE")) {
                                    try {
                                        JSONArray JTacValues  = JARRAY.getJSONObject(i).getJSONArray("Values");
                                        if (!StringUtils.isEmpty(JTacValues) && JTacValues.length()>0) {
                                            List<ConfigTypeData> lstTD = getConfigTypeClass(JTacValues,"CONFIG_TYPE_VALUE");
                                            if (!StringUtils.isEmpty(lstTD) && lstTD.size()>0)
                                                mnData.setLstConfigTypeData(lstTD);
                                        }
                                    }
                                    catch (JSONException jee) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (JSONException je) {
            log.error("Json exception:{}",je.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Exception:{}",e.getMessage());
        }
        return mnData;
    }

    public SupplierConfigRefData callSupplierRefFactData(String publicID) {
        SupplierConfigRefData mnData = null;
        try {
            ResponseEntity<String> response = getFactData(publicID,1);

            if (!StringUtils.isEmpty(response)) {

                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject respBody = new JSONObject(response.getBody());
                    JSONObject jres = respBody.getJSONObject("Response");
                    if (!StringUtils.isEmpty(jres)) {
                        mnData = new SupplierConfigRefData();
                        mnData.setSupplier_code(jres.getString("Name"));
                        mnData.setSupplier_code_publicid(jres.getString("PublicID"));

                        JSONArray JARRAY = jres.getJSONArray("ItemElements");
                        if (!StringUtils.isEmpty(JARRAY) && JARRAY.length()>0 ) {
                            for (int i = 0; i < JARRAY.length(); i++) {

                                if (JARRAY.getJSONObject(i).getString("Name").equalsIgnoreCase("CONFIG_TYPE")) {
                                    try {
                                        JSONArray JTacValues  = JARRAY.getJSONObject(i).getJSONArray("Values");
                                        if (!StringUtils.isEmpty(JTacValues) && JTacValues.length()>0) {
                                            List<ConfigTypeData> lstTD = getConfigTypeClass(JTacValues,"CONFIG_TYPE_VALUE");
                                            if (!StringUtils.isEmpty(lstTD) && lstTD.size()>0)
                                                mnData.setLstConfigTypeData(lstTD);
                                        }
                                    }
                                    catch (JSONException jee) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (JSONException je) {
            log.error("Json exception:{}",je.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Exception:{}",e.getMessage());
        }
        return mnData;
    }

    public List<ConfigTypeData> getConfigTypeClass(JSONArray JPSArray,String classType) {
        List<ConfigTypeData> lstConfigTypeData = new ArrayList<>();
        try {
            if (!StringUtils.isEmpty(JPSArray) && JPSArray.length()>0) {
                for(int i=0;i<JPSArray.length();i++) {
                    JSONObject JDefi = JPSArray.getJSONObject(i);
                    if (!StringUtils.isEmpty(JDefi)) {
                        JSONObject JPS = JDefi.getJSONObject("Definition");
                        if (!StringUtils.isEmpty(JPS) && JPS.getString("ClassType").equalsIgnoreCase(classType.toLowerCase())) {
                            JSONArray JARRAY = JPS.getJSONArray("ItemElements");
                            if (!StringUtils.isEmpty(JARRAY) && JARRAY.length()>0 ) {
                                for(int j=0;j<JARRAY.length();j++) {
                                    JSONObject JS = JARRAY.getJSONObject(j);
                                    if (JS.getString("Name").equalsIgnoreCase("CONFIG_TYPE".toLowerCase())) {
                                        lstConfigTypeData.add(ConfigTypeData.builder().config_type_publicId(JPS.getString("PublicID"))
                                                .config_type_refid(getValue(JS)).build());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (JSONException je) {
            log.error("getClassType->>{},json exception:{}",classType, je.getMessage());
        }
        return lstConfigTypeData;
    }


    private String getValue(JSONObject JS) {
        try {
            JSONArray JV = JS.getJSONArray("Values");
            if (!StringUtils.isEmpty(JV) && JV.length()>0) {
                JSONObject JVV = JV.getJSONObject(0);
                if (!StringUtils.isEmpty(JVV))
                    return JVV.getString("Value");
            }
        }
        catch (JSONException je) {
            log.warn("getValue->>No Value:{}", je.getMessage());
        }
        return null;
    }

    private ResponseEntity<String> getFactData(String publicID,int cnt) {
        HttpHeaders headers = this.hansenApiConnector.getHeadersForHansen();
        if (StringUtils.isEmpty(headers)) {
            log.error("Unable to get token");
            return null;
        }

        ResponseEntity<String> response = this.hansenApiConnector.getFactData(apiEndPointConfig.getFact_by_id() + publicID, headers);
        if (!StringUtils.isEmpty(response)) {
            cnt = cnt+1;
            try {
                if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    if (cnt < 4) {
                        this.hansenApiConnector.resetToken();
                        getFactData(publicID, cnt);
                    }
                }
                else if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
                    if (cnt < 4) {
                        getFactData(publicID, cnt);
                    }
                }
            }
            catch (Exception e) {
                log.error("Exception:{}",e.getMessage());
            }
        }
        return response;
    }

    public OutputStatus insertFactData(String request,int cnt) {

        HttpHeaders headers = this.hansenApiConnector.getHeadersForHansen();
        if (StringUtils.isEmpty(headers))
            return OutputStatus.builder().errorStatus(appConfig.getImport_status_failed()).errorMessage("Unable to get okta token").build();

        ResponseEntity<String> response = this.hansenApiConnector.insertFact(request,apiEndPointConfig.getFact_insert_url(),headers);
        if (!StringUtils.isEmpty(response)) {
            try {

                if (response.getStatusCode() == HttpStatus.CREATED) {
                    return OutputStatus.builder().errorStatus(appConfig.getImport_status_success()).build();
                }
                else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    if (cnt<4) {
                        this.hansenApiConnector.resetToken();
                        insertFactData(request, cnt++);
                    }
                    else
                        return OutputStatus.builder().errorStatus(appConfig.getImport_status_failed())
                                .errorMessage("Authorization failed in hansen").build();
                }
                else if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
                    if (cnt<4) {
                        insertFactData(request, cnt++);
                    }
                    else
                        return OutputStatus.builder().errorStatus(appConfig.getImport_status_failed())
                                .errorMessage("Gateway timeout in hansen").build();
                }
                else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                    try {
                        JSONObject jobj = new JSONObject(response.getBody());
                        JSONArray JARRAY = jobj.getJSONArray("Messages");
                        if (!StringUtils.isEmpty(JARRAY)) {
                            StringBuilder errMsg = new StringBuilder();
                            for (int i = 0; i < JARRAY.length(); i++) {
                                errMsg.append(JARRAY.getJSONObject(i).getString("Message"));
                            }
                            return OutputStatus.builder()
                                    .errorStatus(appConfig.getImport_status_failed())
                                    .errorMessage(errMsg.toString()).build();
                        }
                    }
                    catch (JSONException je) {
                        log.error("Json exception:{}",je.getMessage());
                    }
                }
                else {
                    return OutputStatus.builder()
                            .errorStatus(appConfig.getImport_status_failed())
                            .errorMessage(response.getStatusCode().toString() + " error.").build();
                }
            }
            catch (Exception e) {
                log.error("Exception:{}",e.getMessage());
            }
        }
        return OutputStatus.builder().errorStatus(appConfig.getImport_status_success())
                .errorMessage("Unable to insert facty.").build();
    }


    */



}
