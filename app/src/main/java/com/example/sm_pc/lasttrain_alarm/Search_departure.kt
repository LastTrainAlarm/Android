package com.example.sm_pc.lasttrain_alarm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_departure.*
import android.R.attr.key
import android.R.attr.radius
import android.os.AsyncTask.execute
import android.text.method.TextKeyListener.clear
import android.R.attr.button
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import com.example.sm_pc.lasttrain_alarm.Network.ApplicationController
import com.example.sm_pc.lasttrain_alarm.Network.ApplicationController1
import com.example.sm_pc.lasttrain_alarm.Network.ApplicationController1.getInstance
import com.example.sm_pc.lasttrain_alarm.Network.NetworkService
import com.example.sm_pc.lasttrain_alarm.R.id.search_nearstaion
import noman.googleplaces.NRPlaces
import noman.googleplaces.PlaceType
import noman.googleplaces.PlacesListener
import noman.googleplaces.Place
import noman.googleplaces.PlacesException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Search_departure : AppCompatActivity() {

    private var search_nearstaion : Button ?= null
    private var search : Button ?= null
    private var name : EditText ?= null
    private var favorite_list : ListView?= null
    private var networkService: NetworkService ?= null
    private var adapter: CustomStationData? = null
    var searchList: ArrayList<StationListData>? = null
    var favoriteStation : ArrayList<FavoriteListData> ?= null
    var token : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MSwiaWF0IjoxNTM3ODAyMjU3LCJleHAiOjE1NDAzOTQyNTd9.MUdWLVRRg1H0YZQDTJb4Hh3rCGR7rWCdPviagoWId_4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_departure)

        search_nearstaion = findViewById(R.id.search_nearstaion)
        search_nearstaion!!.setOnClickListener{

            val intent = Intent(this,Search::class.java)
            startActivity(intent)
        }
        networkService = ApplicationController1.getInstance()!!.networkService

        name = findViewById(R.id.name)
        search = findViewById(R.id.search)
        favorite_list = findViewById(R.id.station_list)
        adapter = CustomStationData()

        search!!.setOnClickListener(View.OnClickListener {
            //getList()
            var keyword: String = name!!.text!!.toString()

            var getContentList = networkService!!.getSearchList(token, keyword)

            getContentList!!.enqueue(object : Callback<SearchStationResponse> {
                override fun onResponse(call: Call<SearchStationResponse>?, response: Response<SearchStationResponse>?) {

                    if (response!!.isSuccessful) {
                        if (response.body().message.equals("Successfully search subway")) {
                            searchList = response.body().data
                            //동적 다이얼로그
                            val resultDialog = AlertDialog.Builder(this@Search_departure)
                            var inflater = layoutInflater
                            val dialogView = inflater.inflate(R.layout.search_list_dialog, null)
                            resultDialog.setView(dialogView)
                            val alertDialog = resultDialog.create()
                            var image : ImageButton = dialogView.findViewById(R.id.favorite)
                            var text: TextView = dialogView.findViewById(R.id.station_name)
                            var btn1: Button = dialogView.findViewById(R.id.dialog_btn1)
                            var btn2: Button = dialogView.findViewById(R.id.dialog_btn2)
                            if (searchList!!.size != 0) {

                                text.text = searchList!!.get(0).station_name
                                btn1.text = "재검색"
                                btn2.text = "도착역 지정"
                                if(searchList!!.get(0).fav_onoff == 1){
                                    image.setBackgroundResource(R.drawable.favorite_star_fill)
                                }
                                image!!.setOnClickListener(View.OnClickListener {
                                    Log.d("touch","터치성공")
                                    val keyword = networkService!!.putFavorite(token, searchList!!.get(0).station_name)
                                    keyword.enqueue(object : Callback<FavoriteResponse> {
                                        override fun onResponse(call: Call<FavoriteResponse>, response: Response<FavoriteResponse>) {
                                            //즐찾 등록
                                            Log.d("touch서버","서버연결 터치성공")
                                            if (response.isSuccessful) {
                                                if (response.body().message == "Successfully register favorite station") {
                                                    image!!.setBackgroundResource(R.drawable.favorite_star_fill)
                                                } else if (response.body().message == "Successfully clear favorite station") {
                                                    image!!.setBackgroundResource(R.drawable.favorite_star_empty)
                                                }//즐찾 해제
                                            } else {
                                                Log.i("status", "failfailfailfailfailfailfailfailfailfailfail")
                                                //                          ApplicationController.instance!!.makeToast("북마크 실패.")
                                            }
                                        }

                                        override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                                            ApplicationController1.getInstance().makeToast("통신 상태를 확인해주세요")
                                            Log.i("status", "check")
                                        }
                                    })

                                })
                                btn2.setOnClickListener(View.OnClickListener {
                                    alertDialog.cancel()
                                    val intent = Intent(this@Search_departure, Search_arrival::class.java)
                                    startActivity(intent)
                                })
                                btn1.setOnClickListener(View.OnClickListener {
                                    alertDialog.cancel()
                                })
                                alertDialog.show()
                            } else {
                                image!!.visibility = View.GONE
                                btn2!!.visibility = View.GONE
                                text.text = "검색된 역이 없습니다."
                                btn1.text = "닫기"
                                btn1.setOnClickListener(View.OnClickListener {
                                    alertDialog.cancel()
                                })
                                alertDialog.show()
                            }

                        }
                    } else {
                        Log.i("status", "fail")
                    }
                }

                override fun onFailure(call: Call<SearchStationResponse>?, t: Throwable?) {
                    ApplicationController1.getInstance()!!.makeToast("통신 상태를 확인해주세요")
                    Log.i("status", "check")
                }
            })
        })

    }
    fun getList() {

        val keyword= networkService!!.getFavoriteList(token)
        keyword!!.enqueue(object : Callback<GetFavoriteResponse> {
            override fun onResponse(call: Call<GetFavoriteResponse>?, response: Response<GetFavoriteResponse>?) {

                if (response!!.isSuccessful) {
                    if (response.body().message.equals("Successfully get favorite station")) {
                        val station = GetStationData()
                        favoriteStation = response.body().data
                        for (i: Int ?=0; i<favoriteStation!!.size;i++){

                        }
                    }
                } else {
                    Log.i("status", "fail")
                }
            }

            override fun onFailure(call: Call<GetFavoriteResponse>?, t: Throwable?) {
                ApplicationController1.getInstance()!!.makeToast("통신 상태를 확인해주세요")
                Log.i("status", "check")
            }
        })
    }
}

