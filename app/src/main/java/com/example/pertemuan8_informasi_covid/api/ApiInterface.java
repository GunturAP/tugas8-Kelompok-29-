package com.example.pertemuan8_informasi_covid.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
public interface ApiInterface {
    static final String BASE_URL="https://data.covid19.go.id/public/api/";
    @GET("prov.json")
    Call<DataProvinsi> getJson();
}
