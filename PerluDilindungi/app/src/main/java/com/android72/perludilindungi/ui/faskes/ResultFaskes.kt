package com.android72.perludilindungi.ui.faskes

data class ResultFaskes(
    val alamat: String,
    val detail: List<Detail>,
    val id: Int,
    val jenis_faskes: String,
    val kelas_rs: String,
    val kode: String,
    val kota: String,
    val latitude: String,
    val longitude: String,
    val nama: String,
    val provinsi: String,
    val source_data: String,
    val status: String,
    val telp: String
)