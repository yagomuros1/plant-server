package com.plant.server.util.google.drive.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OutputDriveCustom implements Serializable {

    protected ByteArrayOutputStream byteArrayOutputStream;

    protected String mimetype;

}