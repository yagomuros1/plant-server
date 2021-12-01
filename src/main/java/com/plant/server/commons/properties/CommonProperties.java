package com.plant.server.commons.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("cmmn")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:common.properties")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:common-${spring.profiles.active}.properties")
@Getter
@Setter
public class CommonProperties {

    private String version;
    private String clientBaseUrl;
    private String test;

    private int defaultChunkSize;

    // Hash keys
    private String privateKeyHash;
    private String privateKeySymmetric;
    private String privateKeySymmetricSalt;

    // Google Drive
    private String orderManagementGoogleServiceAccountId;
    private String orderManagementGoogleServiceCert;
    private String orderManagementGoogleAppName;
    private String orderManagementGoogleDriveIncomeFolderId;
    private Long secondsToExpireLink;

}
