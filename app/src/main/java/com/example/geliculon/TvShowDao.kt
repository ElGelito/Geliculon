package com.example.geliculon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tbl_tv_shows")
    fun getAll(): List<TvShowEntity>
    @Insert
    fun insert(tvShow: TvShowEntity)
    @Query("SELECT * FROM tbl_tv_shows WHERE id = :id LIMIT 1")
    fun findById(id: Long): TvShowEntity?
    @Query("DELETE FROM tbl_tv_shows WHERE id = :id")
    fun delete(id: Long)
}