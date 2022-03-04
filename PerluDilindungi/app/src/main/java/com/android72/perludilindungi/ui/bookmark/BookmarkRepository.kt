package com.android72.perludilindungi.ui.bookmark

import androidx.lifecycle.LiveData
import com.android72.perludilindungi.backend.model.Bookmark
import com.android72.perludilindungi.backend.model.BookmarkDao

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {

    val readAllData: LiveData<List<Bookmark>> = bookmarkDao.readAllData()

    suspend fun addBookmark(bookmark: Bookmark){
        bookmarkDao.addBookmark(bookmark)
    }

    suspend fun deleteBookmark(id: Int){
        bookmarkDao.deleteBookmark(id)
    }

    suspend fun deleteAllBookmarks(){
        bookmarkDao.deleteAllBookmarks()
    }

    fun checkBookmarkExist(id: Int){
        /*val checkBookmarkExist: LiveData<List<Bookmark>> = bookmarkDao.checkBookmarkExist(id)
        return checkBookmarkExist */

        bookmarkDao.checkBookmarkExist(id)
    }
}