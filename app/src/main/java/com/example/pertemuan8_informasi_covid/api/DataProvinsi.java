package com.example.pertemuan8_informasi_covid.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class DataProvinsi {
    @SerializedName("last_date")
    @Expose
    private String last_date;
    @SerializedName("list_data")
    @Expose
    private List<ListProvinsi> list_data =null;



    public DataProvinsi(String last_date, List<ListProvinsi> list_data) {
        this.last_date = last_date;
        this.list_data = list_data;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public List<ListProvinsi> getList_data() {
        return list_data;
    }

    public void setList_data(List<ListProvinsi> list_data) {
        this.list_data = list_data;
    }



}

