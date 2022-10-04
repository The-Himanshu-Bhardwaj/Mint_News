package com.example.mintnews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceModel {

    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("name")
    @Expose
    private String name;

    public SourceModel(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
