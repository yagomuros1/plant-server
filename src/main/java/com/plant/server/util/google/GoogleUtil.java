package com.plant.server.util.google;

import com.plant.server.commons.properties.CommonProperties;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleUtil {

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    @Autowired
    private CommonProperties commonProperties;

    private Credential credential;

    public Credential getCredential() throws IOException {
        if (this.credential == null) {
            try {
                this.credential = GoogleCredential.fromStream(new FileInputStream(getCert())).createScoped(SCOPES);
            } catch (java.io.IOException e) {
                throw new IOException(e);
            }
        }
        return this.credential;
    }

    private File getCert() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + this.commonProperties.getOrderManagementGoogleServiceCert());
    }

}
