package com.android72.perludilindungi.ui.faskes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Faskes (
    val id: Int,
    val kode: String,
    val nama: String,
    var alamat: String,
    var telp: String,
    val jenis_faskes: String,
    val status: String,
    val latitude: String,
    val longitude: String
    ) : Parcelable