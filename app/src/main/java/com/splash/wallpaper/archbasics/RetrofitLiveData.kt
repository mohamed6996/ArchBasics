package com.splash.wallpaper.archbasics

import android.arch.lifecycle.LiveData
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.splash.wallpaper.archbasics.PhotoList

/**
 * Created by lenovo on 2/1/2018.
 */
class RetrofitLiveData<PhotoList>(private val call: Call<PhotoList>) : LiveData<PhotoList>(), Callback<PhotoList> {
    var photoList: List<Photo>? = null


    override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
        response?.isSuccessful.let {
            //// only run if isSuccessful returned a non null value
            Log.i("INFO", response?.body().toString())

            response?.body()

        }
    }


    override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
        //not implemented
    }


    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    fun cancel() = if (!call.isCanceled) call.cancel() else Unit
}