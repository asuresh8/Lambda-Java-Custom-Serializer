package com.amazonaws.lambda.runtime.events.samples;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.codepipeline.model.ArtifactLocation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * represents how to handle an ses event in lambda
 */
public class CustomHandler implements RequestStreamHandler {

    /**
     * handle lambda request
     * @param inputStream input stream of bytes
     * @param outputStream output stream of bytes
     * @param context Lambda context
     */
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(ArtifactLocation.class, ArtifactLocationMixin.class);
        try {
            ArtifactLocation artifactLocation = mapper.readValue(inputStream, ArtifactLocation.class);
            System.out.println("Received artifact location with type: " + artifactLocation.getType());
            mapper.writeValue(outputStream, artifactLocation);
        } catch (IOException e) {
            System.out.println("Unable to deserialize ses event");
            throw new RuntimeException(e);
        }
    }
}
