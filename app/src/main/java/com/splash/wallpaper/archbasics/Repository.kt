package com.splash.wallpaper.archbasics

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.splash.wallpaper.archbasics.room.PhotoDB
import com.splash.wallpaper.archbasics.room.PhotoEntry
import com.splash.wallpaper.splash.PhotoApi
import com.splash.wallpaper.splash.PhotoRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    val photoApi: PhotoApi
    var photoList: List<Photo>?
    var retlist = MutableLiveData<List<Photo>>()

    init {
        photoApi = PhotoRetriever.service
        photoList = ArrayList<Photo>()

    }


    fun getLiveData(context: Context, internetState: Boolean): LiveData<List<Photo>> {
        if (internetState)
            return getItemsListFromWeb(context)
        else
            return getItemsListFromDB(context)

    }


    fun getItemsListFromWeb(context: Context): MutableLiveData<List<Photo>> {
        photoApi.getPhotos().enqueue(object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
               }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                if (response!!.isSuccessful) {
                    retlist.value = response.body()?.hits
                    saveCallResult(response.body()?.hits, context)

                }
            }

        })

        return retlist

    }


    fun saveCallResult(item: List<Photo>?, context: Context) {
        var photoDB = PhotoDB.getInstance(context)
        object : AsyncTask<List<Photo>, Void, Void>() {
            override fun doInBackground(vararg p0: List<Photo>?): Void? {
                var photo_list = ArrayList<PhotoEntry>()
                item?.forEach {
                    var photoEntity = PhotoEntry(it.id, it.likes, it.favourites, it.tags,
                            it.imageHeight, it.imageWidth, it.previewUrl, it.webFormatUrl)
                    photo_list.add(photoEntity)
                }

                photoDB.getPhotoDao().addAllPhotos(photo_list)

                return null
            }

        }.execute(item)
    }


    fun getItemsListFromDB(context: Context): MutableLiveData<List<Photo>> {
        var photoDB = PhotoDB.getInstance(context)
        object : AsyncTask<Void, Void, List<Photo>>() {
            override fun doInBackground(vararg p0: Void?): List<Photo> {
                return photoDB.getPhotoDao().getAllPhotos()
            }

            override fun onPostExecute(result: List<Photo>?) {
                retlist.value = result
            }


        }.execute()

        return retlist
    }
}

