package com.example.sm_pc.lasttrain_alarm.Network

import android.app.Application
import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by LEESANGYUN on 2018-01-06.
 */
class ApplicationController : Application() {
    var networkService: NetworkService? = null
        private set
    var baseUrl = "http://18.235.225.253:3210/"
    var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()

        mContext = applicationContext

        instance = this

        buildNetwork()
    }

    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null

    }
}