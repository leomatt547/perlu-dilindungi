package com.android72.perludilindungi.backend.api

import com.android72.perludilindungi.backend.model.CheckinData
import com.android72.perludilindungi.ui.faskes.CityData
import com.android72.perludilindungi.ui.faskes.FaskesData
import com.android72.perludilindungi.ui.faskes.ProvinceData
import com.android72.perludilindungi.ui.berita.BeritaData
import retrofit2.Call
import retrofit2.http.*

interface RetrofitAPI {
    @POST("check-in")
    fun checkIn(@Body checkinData: CheckinData): Call<CheckinData>

    @GET("/api/get-faskes-vaksinasi")
    fun getFaskes(@Query("province")province: String, @Query("city")city: String): Call<FaskesData>

    @GET("/api/get-news")
    fun beritaData(): Call<BeritaData>

    @GET("/api/get-province")
    fun getProvince(): Call<ProvinceData>;

    @GET("/api/get-city")
    fun getCity(@Query("province")province: String): Call<CityData>;
}