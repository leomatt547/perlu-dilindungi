package com.android72.perludilindungi.ui.bookmark

import android.app.Application
import androidx.lifecycle.*
import com.android72.perludilindungi.backend.model.Bookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Bookmark>>
    private val repository: BookmarkRepository
    //val checkBookmarkExist: LiveData<List<Bookmark>>

    init {
        val bookmarkDao = BookmarkDatabase.getDatabase(application).bookmarkDao()
        repository = BookmarkRepository(bookmarkDao)
        readAllData = repository.readAllData
    }

    fun addBookmark(bookmark: Bookmark){
        viewModelScope.launch(Dispatchers.IO) {
            /*repository.addBookmark(
                Bookmark(
                    2725,
                    "N0001702",
                    "KLINIK DPR RI",
                    "Gedung DPR-RI, JL. Gatot Subroto, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270, Indonesia",
                    "(021) 5715801",
                    "KLINIK",
                    "Siap Vaksinasi"
            )) */
            repository.addBookmark(bookmark)
        }
    }

    fun checkBookmarkExist(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkBookmarkExist(2725)
        }
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmark(id)
        }
    }

}