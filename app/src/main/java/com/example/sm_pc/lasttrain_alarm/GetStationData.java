package com.example.sm_pc.lasttrain_alarm;

import android.widget.ImageButton;

public class GetStationData {
    private int favorite;
    private String station_name;

    public int getFavorite(){
        return favorite;
    }
    public void  setFavorite(int favorite){
        this.favorite = favorite;
    }
    public String getContent(){
        return station_name;
    }
    public void  setContent(String station_name){
        this.station_name = station_name;
    }

}
