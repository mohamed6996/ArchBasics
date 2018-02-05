package com.splash.wallpaper.archbasics

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log


class MainActivity : AppCompatActivity() {
    //var photoList: List<Photo>? = null
    lateinit var viewModel: MainActivityViewModel
    lateinit var observer: Observer<PhotoList>
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)


        viewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityViewModel::class.java)
        viewModel.getList().observe(this, object : Observer<PhotoList> {
            override fun onChanged(it: PhotoList?) {
                Log.i("RECEIVED", "from mainActivity ${it?.hits?.size}")
                mainAdapter = MainAdapter(it!!
                        ,this@MainActivity)
                recyclerView.adapter = mainAdapter
                mainAdapter.notifyDataSetChanged()
            }
        })


//
//        observer = Observer {
//            if (it != null) {
//                //   textview.setText(it?.hits?.size.toString())
//                Log.i("RECEIVED", "from mainActivity ${it.hits.size}")
//                mainAdapter = MainAdapter(it, this)
//                recyclerView.adapter = mainAdapter
//                mainAdapter.notifyDataSetChanged()
//            }
//        }
//
//        viewModel.getList().observe(this, observer)


    }
}

