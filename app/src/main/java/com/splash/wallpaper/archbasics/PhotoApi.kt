package com.splash.wallpaper.splash

import android.arch.lifecycle.MutableLiveData
import com.splash.wallpaper.archbasics.PhotoList
import com.splash.wallpaper.archbasics.R
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by lenovo on 1/26/2018.
 */
interface PhotoApi {
    @GET("?key=3759940-2ad2e64eaca323a5916a18590&q=nature&image_type=photo")
    fun getPhotos(): Call<PhotoList>
}