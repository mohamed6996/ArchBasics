package com.splash.wallpaper.archbasics.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by lenovo on 2/6/2018.
 */

@Entity(tableName = "photos_table")
data class PhotoEntry(
        @PrimaryKey var id: String,
        var likes: Int,
        var favourites: Int,
        var tags: String,
        var imageHeight: Double,
        var imageWidth: Double,
        var previewUrl: String,
        var webFormatUrl: String
)

//    @PrimaryKey
//    var id: String=""
//    var likes: Int=0
//    var favourites: Int=0
//    var tags: String= ""
//    var imageHeight: Double=0.0
//    var imageWidth: Double=0.0
//    var previewUrl: String=""
//    var webFormatUrl: String=""


