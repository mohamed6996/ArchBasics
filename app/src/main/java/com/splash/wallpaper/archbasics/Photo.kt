package com.splash.wallpaper.archbasics
import com.google.gson.annotations.SerializedName
import java.io.Serializable
/**
 * Created by lenovo on 1/26/2018.
 */
data class Photo(val id: String,
                  val likes: Int,
                  val favourites: Int,
                  val tags: String,
                  val imageHeight: Double,
                  val imageWidth: Double,
                  @SerializedName("previewURL")val previewUrl: String,
                  @SerializedName("webformatURL") val webFormatUrl: String) : Serializable
