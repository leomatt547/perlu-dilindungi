package com.android72.perludilindungi.backend.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CheckinData(
    @field:SerializedName("qrCode") var qrCode: String,
    @field:SerializedName("latitude") var latitude: String,
    @field:SerializedName("longitude") var longitude: String,
) {

    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}

class Data {
    @SerializedName("userStatus")
    @Expose
    var userStatus: String? = null

    @SerializedName("reason")
    @Expose
    var reason: String? = null
}