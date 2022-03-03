package com.android72.perludilindungi.ui.berita

data class BeritaData(
    val count_total: Int,
    val message: String,
    val results: List<Result>,
    val success: Boolean
)