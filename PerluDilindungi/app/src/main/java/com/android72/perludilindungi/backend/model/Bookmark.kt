package com.android72.perludilindungi.backend.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class Bookmark(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "kode") val kode: String?,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "alamat") val alamat: String?,
    @ColumnInfo(name = "telp") val telp: String?,
    @ColumnInfo(name = "jenis_faskes") val jenis_faskes: String?,
    //@ColumnInfo(name = "status") val status: String?,
)