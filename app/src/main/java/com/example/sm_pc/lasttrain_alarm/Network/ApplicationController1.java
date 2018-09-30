package com.example.sm_pc.lasttrain_alarm.Network;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationController1 extends Application {

    private static ApplicationController1 instance = null;
    NetworkService networkService= null;
    public Context mcontext = null;
    private String baseUrl= "http://18.235.225.253:3264";

    @Override
    public void onCreate(){
        super.onCreate();
        mcontext = getApplicationContext();
        ApplicationController1.instance = this;//처음 어플리케이션 실행될 때 인스턴스 생성
        buildNetworkService();
    }
    public static ApplicationController1 getInstance() {return instance;}

    public NetworkService getNetworkService() {return networkService;}


    public void buildNetworkService(){
        Gson gson = new GsonBuilder().create();
        //서버에서 json형식으로 데이터 보내고 이를 파싱해서 받아옴
        GsonConverterFactory factory = GsonConverterFactory.create(gson);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(factory)
                .build();
        networkService = retrofit.create(NetworkService.class);
//        synchronized (ApplicationController.class){
//            if(networkService == null){
//                //baseUrl = String.format("http://%s:%d","18.235.225.253",3210);
//                //baseUrl = String.format("http://%s:%d",ip,port);
//                Gson gson = new GsonBuilder().create();
//                //서버에서 json형식으로 데이터 보내고 이를 파싱해서 받아옴
//                GsonConverterFactory factory = GsonConverterFactory.create(gson);
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(factory)
//                        .build();
//                networkService = retrofit.create(NetworkService.class);
//
//            }
//        }
    }
    public void makeToast(String message){
        Toast.makeText(mcontext,message,Toast.LENGTH_SHORT).show();
    }

}
