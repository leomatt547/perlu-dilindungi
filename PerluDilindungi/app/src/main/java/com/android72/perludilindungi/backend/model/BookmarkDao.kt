package com.android72.perludilindungi.backend.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Bookmark>>

    /* @Delete
    suspend fun deleteBookmark(bookmark: Bookmark) */

    @Query("DELETE FROM bookmark_table WHERE id = :id")
    suspend fun deleteBookmark(id: Int)

    @Query("DELETE FROM bookmark_table")
    suspend fun deleteAllBookmarks()

    @Query("SELECT * FROM bookmark_table WHERE id = :id")
    fun checkBookmarkExist(id : Int) : LiveData<List<Bookmark>>

    /*@Query("SELECT EXISTS (SELECT * FROM bookmark_table WHERE id = :id)")
    fun selectIfExists(id: Int): LiveData<List<Bookmark>> */

}