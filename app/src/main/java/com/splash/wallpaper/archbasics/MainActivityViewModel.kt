package com.splash.wallpaper.archbasics

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import com.splash.wallpaper.splash.PhotoRetriever

/**
 * Created by lenovo on 2/1/2018.
 */
class MainActivityViewModel : ViewModel() {

    private val repository: Repository
    lateinit var photoList: LiveData<PhotoList>
    val itemsListObservable: MediatorLiveData<List<Photo>>


    init {
        repository = Repository()
        itemsListObservable = MediatorLiveData()
    }

    fun getList(): LiveData<PhotoList> {

       photoList = repository.getNetworkData()
        Log.i("RECEIVED","size is ${photoList.value?.hits?.size} from viewModel ")
        return photoList
    }
}