package com.andunker.mercadolibre.models.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ItemDTO {

    private String id;
    private String title;
    private String categoryId;
    private int price;
    private Date startTime;
    private Date stopTime;
    private List<ChildrenDTO> childrens ;

    @JsonProperty("item_id")
    public String getId() {
        return id;
    }

    @JsonAlias({ "item_id", "id" })
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("category_id")
    public String getCategoryId() {
        return categoryId;
    }

    @JsonProperty("category_id")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
    }

    @JsonProperty("start_time")
    public Date getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("stop_time")
    public Date getStopTime() {
        return stopTime;
    }

    @JsonProperty("stop_time")
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    @JsonProperty("children")
    public List<ChildrenDTO> getChildrens() {
        return childrens;
    }

    @JsonProperty("children")
    public void setChildrens(List<ChildrenDTO> childrens) {
        this.childrens = childrens;
    }

}
