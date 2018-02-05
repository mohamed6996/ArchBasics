package com.splash.wallpaper.archbasics

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.splash.wallpaper.splash.PhotoApi
import com.splash.wallpaper.splash.PhotoRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    val photoApi: PhotoApi
    var photoList: List<Photo>?
    val retlist = MutableLiveData<PhotoList>()

    init {
        photoApi = PhotoRetriever.service
        photoList = ArrayList<Photo>()

    }

    fun getItemsListFromWeb() {
        photoApi.getPhotos().enqueue(object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.i("RECEIVED", "${t?.message} from repository ")
                // retlist.value = null
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                if (response!!.isSuccessful) {
                    retlist.value = response.body()
                    Log.i("RECEIVED", "size of photoList is ${retlist.value?.hits?.size} from repository ")
                }
              //  Log.i("RECEIVED", "size is ${retlist.value?.size} from repository ")

            }

        })

    }

    fun getNetworkData(): LiveData<PhotoList> {
        getItemsListFromWeb()
        return retlist
    }

}

