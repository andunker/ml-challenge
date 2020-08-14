package com.andunker.mercadolibre.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Health {

    private String date;
    private int avgResponseTime;
    private int totalRequest;
    private int avgResponseTimeApiCalls;
    private int totalCountApiCalls;
    private List<InfoRequest> infoRequests;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("avg_response_time")
    public int getAvgResponseTime() {
        return avgResponseTime;
    }

    @JsonProperty("avg_response_time")
    public void setAvgResponseTime(int avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    @JsonProperty("total_request")
    public int getTotalRequest() {
        return totalRequest;
    }

    @JsonProperty("total_request")
    public void setTotalRequest(int totalRequest) {
        this.totalRequest = totalRequest;
    }

    @JsonProperty("avg_response_time_api_calls")
    public int getAvgResponseTimeApiCalls() {
        return avgResponseTimeApiCalls;
    }

    @JsonProperty("avg_response_time_api_calls")
    public void setAvgResponseTimeApiCalls(int avgResponseTimeApiCalls) {
        this.avgResponseTimeApiCalls = avgResponseTimeApiCalls;
    }

    @JsonProperty("total_count_api_calls")
    public int getTotalCountApiCalls() {
        return totalCountApiCalls;
    }

    @JsonProperty("total_count_api_calls")
    public void setTotalCountApiCalls(int totalCountApiCalls) {
        this.totalCountApiCalls = totalCountApiCalls;
    }

    @JsonProperty("info_requests")
    public List<InfoRequest> getInfoRequests() {
        return infoRequests;
    }

    @JsonProperty("info_requests")
    public void setInfoRequests(List<InfoRequest> infoRequests) {
        this.infoRequests = infoRequests;
    }

}
