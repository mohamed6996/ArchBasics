package com.splash.wallpaper.archbasics

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    //var photoList: List<Photo>? = null
    lateinit var viewModel: MainActivityViewModel
    lateinit var observer: Observer<PhotoList>
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter

    private var mNetworkReceiver: NetworkBroadcastReceiver? = null
    var mNetworkIntentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)


        viewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityViewModel::class.java)

        checkForInternet() // will pass internet state to the view model

        viewModel.setContextSource(this@MainActivity)

        viewModel.getList().observe(this, object : Observer<List<Photo>> {
            override fun onChanged(it: List<Photo>?) {
                Toast.makeText(this@MainActivity, it?.size.toString(), Toast.LENGTH_SHORT).show()
                mainAdapter = MainAdapter(it!!
                       , this@MainActivity)
                recyclerView.adapter = mainAdapter
                mainAdapter.notifyDataSetChanged()
            }
        })

        //setup  broadcast receiver
        mNetworkIntentFilter = IntentFilter()
        mNetworkReceiver = NetworkBroadcastReceiver()
        mNetworkIntentFilter!!.addAction(ConnectivityManager.CONNECTIVITY_ACTION)


    }


    private fun checkForInternet() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        viewModel.setInternetState(netInfo != null && netInfo.isConnectedOrConnecting)
    }

    private inner class NetworkBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkForInternet()
        }
    }


    override fun onResume() {
        super.onResume()
        registerReceiver(mNetworkReceiver, mNetworkIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetworkReceiver)
    }
}

