package com.android72.perludilindungi.ui.bookmark

import android.app.Application
import androidx.lifecycle.*
import com.android72.perludilindungi.backend.model.Bookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Bookmark>>
    private val repository: BookmarkRepository

    init {
        val bookmarkDao = BookmarkDatabase.getDatabase(application).bookmarkDao()
        repository = BookmarkRepository(bookmarkDao)
        readAllData = repository.readAllData
    }

    fun insert(bookmark: Bookmark){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(
                Bookmark(
                    2725,
                    "N0001702",
                    "KLINIK DPR RI",
                    "Gedung DPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia",
                    "(021) 5715801",
                    "KLINIK",
            ))
        }
    }

}