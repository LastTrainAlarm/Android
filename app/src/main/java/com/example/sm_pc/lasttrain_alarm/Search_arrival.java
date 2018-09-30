package com.example.sm_pc.lasttrain_alarm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sm_pc.lasttrain_alarm.Network.ApplicationController1;
import com.example.sm_pc.lasttrain_alarm.Network.NetworkService;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Search_arrival extends AppCompatActivity {

    private NetworkService networkService;
    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MSwiaWF0IjoxNTM3ODAyMjU3LCJleHAiOjE1NDAzOTQyNTd9.MUdWLVRRg1H0YZQDTJb4Hh3rCGR7rWCdPviagoWId_4";

    private String arr_name;
    private ArrayList<StationListData> searchList;
    private CustomStationData adapter;
    private ListView listView;
    private Button btn_arr;
    private EditText edit_arr;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_arrival);

        Intent intent = getIntent();
        String depart_name = intent.getStringExtra("depart_name");
        Log.d("출발역",depart_name);
        networkService = ApplicationController1.getInstance().getNetworkService();

        listView = (ListView)findViewById(R.id.station_list);

        btn_arr = (Button)findViewById(R.id.btn_search_arr);
        edit_arr = (EditText)findViewById(R.id.edit_search_arr);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View view) {
                finish();
            } });

        btn_arr.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View view) {
                arr_name = edit_arr.getText().toString();
                Log.d("도착역",arr_name);
                Log.d("통신시도",arr_name);
                getList();
            } });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView v = (TextView) view.findViewById(R.id.station_name);
                String click = (String)parent.getItemAtPosition(position);
                Log.d("클릭",click);
            }
        });
    }
    public void getList(){
        final Call<SearchStationResponse> keyword= networkService.getSearchList(CommonData.INSTANCE.getToken(),arr_name);
        Log.d("통신시도1",arr_name);

        keyword.enqueue(new Callback<SearchStationResponse>() {
            @Override
            public void onResponse(Call<SearchStationResponse> call, Response<SearchStationResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("Successfully search subway")){
                        searchList = response.body().getData();
                        if(searchList.size()!=0){
                            adapter = new CustomStationData();
                            Log.d("통신성공",String.valueOf(searchList.size()));
                            for(int i=0 ; i<searchList.size(); i++){
                                Log.d("결과",searchList.get(i).getStation_name());
                                GetStationData station = new GetStationData();

                                if(searchList.get(i).getFav_onoff()==1){
                                    station.setFavorite(R.drawable.favorite_star_fill);
                                }
                                else{
                                    station.setFavorite(R.drawable.favorite_star_empty);
                                }
                                station.setContent(searchList.get(i).getStation_name());
                                adapter.addItem(station);

                            }
                            listView.setAdapter(adapter);
                            Log.d("클릭시도","시도");
                        } else{
                            GetStationData station = new GetStationData();
                            adapter = new CustomStationData();
                            station.setFavorite(0);
                            station.setContent("검색된 역이 없습니다.");
                            adapter.addItem(station);
                            listView.setAdapter(adapter);
                        }

                    }
                }
            }
            @Override
            public void onFailure(Call<SearchStationResponse> call, Throwable t) {
                ApplicationController1.getInstance().makeToast("통신 상태를 확인해주세요");
                Log.i("status", "check");
            }
        });
    }
}
