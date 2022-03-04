package com.android72.perludilindungi.backend.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityData {
    @SerializedName("curr_val")
    var curr_val: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("results")
    @Expose
    var results: ResultsCity? = null
}

class ResultsCity {
    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null
}