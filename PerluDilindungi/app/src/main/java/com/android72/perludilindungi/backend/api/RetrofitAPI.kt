package com.android72.perludilindungi.backend.api

import com.android72.perludilindungi.backend.model.CheckinData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("check-in")
    fun checkIn(@Body checkinData: CheckinData): Call<CheckinData>
}