package com.andunker.mercadolibre.models.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ChildrenDTO {

    @JsonProperty("item_id")
    public String getId() {
        return id;
    }

    @JsonAlias({ "item_id", "id" })
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("stop_time")
    public Date getStopTime() {
        return stopTime;
    }

    @JsonProperty("stop_time")
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    private String id;
    private Date stopTime;
}
