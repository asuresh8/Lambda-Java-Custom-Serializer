package com.amazonaws.lambda.runtime.events.samples;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by adsuresh on 9/6/17.
 */
public abstract class ArtifactLocationMixin {

    @JsonProperty("artifactLocationType") abstract public String getType();
    @JsonProperty("artifactLocationType") abstract public void setType(String type);

}
