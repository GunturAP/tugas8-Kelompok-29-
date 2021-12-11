package com.example.pertemuan8_informasi_covid.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListProvinsi {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("jumlah_kasus")
    @Expose
    private String jumlah_kasus;
    @SerializedName("jumlah_sembuh")
    @Expose
    private String jumlah_sembuh;
    @SerializedName("jumlah_meninggal")
    @Expose
    private String jumlah_meninggal;
    @SerializedName("jumlah_dirawat")
    @Expose
    private String jumlah_dirawat;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJumlah_kasus() {
        return jumlah_kasus;
    }

    public void setJumlah_kasus(String jumlah_kasus) {
        this.jumlah_kasus = jumlah_kasus;
    }

    public String getJumlah_sembuh() {
        return jumlah_sembuh;
    }

    public void setJumlah_sembuh(String jumlah_sembuh) {
        this.jumlah_sembuh = jumlah_sembuh;
    }

    public String getJumlah_meninggal() {
        return jumlah_meninggal;
    }

    public void setJumlah_meninggal(String jumlah_meninggal) {
        this.jumlah_meninggal = jumlah_meninggal;
    }

    public String getJumlah_dirawat() {
        return jumlah_dirawat;
    }

    public void setJumlah_dirawat(String jumlah_dirawat) {
        this.jumlah_dirawat = jumlah_dirawat;
    }





}
