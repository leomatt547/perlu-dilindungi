package com.android72.perludilindungi.backend.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FaskesData() {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("count_total")
    var count_total: Int? = null

    @SerializedName("results")
    @Expose
    var results: Results? = null

}

class Results {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("kode")
    @Expose
    var kode: String? = null

    @SerializedName("nama")
    @Expose
    var nama: String? = null

    @SerializedName("kota")
    @Expose
    var kota: String? = null

    @SerializedName("provinsi")
    @Expose
    var provinsi: String? = null

    @SerializedName("alamat")
    @Expose
    var alamat: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("telp")
    @Expose
    var telp: String? = null

    @SerializedName("jenis_faskes")
    @Expose
    var jenis_faskes: String? = null

    @SerializedName("kelas_rs")
    @Expose
    var kelas_rs: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("detail")
    @Expose
    var detail: DetailData? = null

    @SerializedName("source_data")
    @Expose
    var source_data: String? = null
}

class DetailData {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("kode")
    @Expose
    var kode: String? = null

    @SerializedName("batch")
    @Expose
    var batch: String? = null

    @SerializedName("divaksin")
    @Expose
    var divaksin: Int? = null

    @SerializedName("divaksin_1")
    @Expose
    var divaksin_1: Int? = null

    @SerializedName("divaksin_2")
    @Expose
    var divaksin_2: Int? = null

    @SerializedName("batal_vaksin")
    @Expose
    var batal_vaksin: Int? = null

    @SerializedName("batal_vaksin_1")
    @Expose
    var batal_vaksin_1: Int? = null

    @SerializedName("batal_vaksin_2")
    @Expose
    var batal_vaksin_2: Int? = null

    @SerializedName("pending_vaksin")
    @Expose
    var pending_vaksin: Int? = null

    @SerializedName("pending_vaksin_1")
    @Expose
    var pending_vaksin_1: Int? = null

    @SerializedName("pending_vaksin_2")
    @Expose
    var pending_vaksin_2: Int? = null

    @SerializedName("tanggal")
    @Expose
    var tanggal: String? = null
}