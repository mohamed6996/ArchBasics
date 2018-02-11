package com.splash.wallpaper.archbasics.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by lenovo on 2/6/2018.
 */
@Database(entities = arrayOf(PhotoEntry::class), version = 1)
abstract class PhotoDB : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        private var db: PhotoDB? = null

        fun getInstance(context: Context): PhotoDB {
            if (db == null) {
                db = Room.databaseBuilder(context, PhotoDB::class.java, "photo_db")
                        .build()
                //  .allowMainThreadQueries()
            }

            return db!!
        }
    }
}