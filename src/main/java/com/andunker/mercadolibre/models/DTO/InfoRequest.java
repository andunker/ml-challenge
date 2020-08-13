package com.andunker.mercadolibre.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoRequest {


    private int statusCode;
    private int count;

    @JsonProperty("status_code")
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

}