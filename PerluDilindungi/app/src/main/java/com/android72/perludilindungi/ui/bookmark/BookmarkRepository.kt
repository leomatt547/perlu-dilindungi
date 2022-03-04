package com.android72.perludilindungi.ui.bookmark

import androidx.lifecycle.LiveData
import com.android72.perludilindungi.backend.model.Bookmark
import com.android72.perludilindungi.backend.model.BookmarkDao

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    val readAllData: LiveData<List<Bookmark>> = bookmarkDao.readAllData()

    suspend fun insert(bookmark: Bookmark){
        bookmarkDao.addBookmark(bookmark)
    }

}