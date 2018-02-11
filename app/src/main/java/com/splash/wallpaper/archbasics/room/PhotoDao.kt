package com.splash.wallpaper.archbasics.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.splash.wallpaper.archbasics.Photo

/**
 * Created by lenovo on 2/6/2018.
 */
@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos_table")
    fun getAllPhotos(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPhotos(photoList: List<PhotoEntry>)
}