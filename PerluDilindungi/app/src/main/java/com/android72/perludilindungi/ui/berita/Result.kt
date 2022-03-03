package com.android72.perludilindungi.ui.berita

data class Result(
    val description: Description,
    val enclosure: Enclosure,
    val guid: String,
    val link: List<String>,
    val pubDate: String,
    val title: String
)