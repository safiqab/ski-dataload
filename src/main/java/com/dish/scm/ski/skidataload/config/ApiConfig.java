package com.dish.scm.ski.skidataload.config;

import com.dish.scm.ski.skidataload.errorhandler.CustomApiError;
import com.dish.scm.ski.skidataload.interceptor.CustomHttpRequestInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;

@Configuration
@Slf4j
@Getter
public class ApiConfig {
    @Value("${restapi.hansen.read-time-out:900000}")
    private String apiReadTimeOut;
    @Value("${restapi.hansen.connection-time-out:900000}")
    private String apiConnectionTimeOut;
    @Value("${import.status.failed}")
    private String import_status_failed;
    @Value("${import.status.success}")
    private String import_status_success;


    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate =  new RestTemplate(new SimpleClientHttpRequestFactory()
        {
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setInstanceFollowRedirects(false);
            }
        });

        try
        {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(Integer.parseInt(apiConnectionTimeOut))
                    .setConnectTimeout(Integer.parseInt(apiReadTimeOut))
                    .setCircularRedirectsAllowed(false)
                    .setMaxRedirects(1)
                    .setRedirectsEnabled(false)
                    .setRelativeRedirectsAllowed(false)
                    .build();

            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
                    new String[]{"TLSv1", "TLSv1.2","TLSv1.1"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            //SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);


            CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .disableAuthCaching()
                    .setSSLSocketFactory(csf)
                    .disableRedirectHandling()
                    .build();

            HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
            restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
            mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

            restTemplate.setInterceptors(Collections.singletonList(new CustomHttpRequestInterceptor()));
            restTemplate.setErrorHandler(new CustomApiError());


        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return restTemplate;

    }
}
