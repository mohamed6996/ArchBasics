package com.splash.wallpaper.archbasics

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log

/**
 * Created by lenovo on 2/1/2018.
 */
class MainActivityViewModel : ViewModel() {

    private val repository: Repository
    lateinit var photoList: LiveData<List<Photo>>
    lateinit var context: Context
    private var internetState = false


    init {
        repository = Repository()
    }

    fun getList(): LiveData<List<Photo>> {

        photoList = repository.getLiveData(context, internetState)
        return photoList
    }

    fun setContextSource(context: Context) {
        this.context = context
    }

    fun setInternetState(internetState: Boolean) {
        this.internetState = internetState
    }
}