package com.plant.server.util.google.drive;

import com.plant.server.business.services.exceptions.InternalErrorException;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.google.GoogleUtil;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@Slf4j
public class GDriveUtil {

    @Autowired
    private CommonProperties commonProperties;
    @Autowired
    private GoogleUtil googleUtil;

    private Drive service;

    private Drive getDriveService() throws IOException {
        try {
            if (this.service == null) {
                // init service
                final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
                this.service =
                        new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, this.googleUtil.getCredential()).setApplicationName(this.commonProperties.getOrderManagementGoogleAppName()).build();
            }
            return this.service;
        } catch (GeneralSecurityException e) {
            throw new InternalErrorException(e);
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    public String uploadDoc(MultipartFile file) throws IOException {
        File mFile = uploadDocAux(file);
        return mFile.getId();
    }

    public ByteArrayOutputStream getDoc(String fileId) throws IOException {
        return getFile(fileId);
    }

    private File uploadDocAux(MultipartFile file) throws IOException {

        // get pdf from holded
        byte[] data = file.getBytes();

        //Tika is apache library used for get mimetype from file
        Tika tika = new Tika();
        String mimeType = tika.detect(file.getOriginalFilename());

        // set metadata
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList(this.commonProperties.getOrderManagementGoogleDriveIncomeFolderId()));

        // set content
        ByteArrayContent fileContent = new ByteArrayContent(mimeType, data);

        // upload file
        try {
            log.debug("uploading file to google drive");
            return getDriveService().files().create(fileMetadata, fileContent).execute();
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

    public void deleteFile(String fileId) {
        try {
            log.debug("deleting file to google drive");
            getDriveService().files().delete(fileId).execute();
        } catch (java.io.IOException e) {
            log.error("Error deleting file: " + fileId);
        }
    }

    private ByteArrayOutputStream getFile(String fileId) throws IOException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            getDriveService().files().get(fileId).executeMediaAndDownloadTo(outputStream);
            return outputStream;
        } catch (java.io.IOException e) {
            throw new IOException(e);
        }
    }

}