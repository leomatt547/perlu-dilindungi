package com.android72.perludilindungi.backend.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Bookmark>>

    @Delete
    suspend fun deleteUser(bookmark: Bookmark)

    @Query("DELETE FROM bookmark_table")
    suspend fun deleteAllUsers()

}