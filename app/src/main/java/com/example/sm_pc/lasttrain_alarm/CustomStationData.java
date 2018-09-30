package com.example.sm_pc.lasttrain_alarm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sm_pc.lasttrain_alarm.Network.ApplicationController1;
import com.example.sm_pc.lasttrain_alarm.Network.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomStationData extends BaseAdapter {

    private ArrayList<GetStationData> listCustom = new ArrayList<>();
//    private NetworkService networkService;
//    String token =
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MSwiaWF0IjoxNTM3ODAyMjU3LCJleHAiOjE1NDAzOTQyNTd9.MUdWLVRRg1H0YZQDTJb4Hh3rCGR7rWCdPviagoWId_4";
//

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_list, null, false);

            holder = new CustomViewHolder();
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.favorite);
            holder.textContent = (TextView) convertView.findViewById(R.id.station_name);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        final GetStationData station = listCustom.get(position);

        holder.imageButton.setBackgroundResource(station.getFavorite());
//        holder.imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Call<FavoriteResponse> keyword= networkService.putFavorite(token,station.getContent());
//                keyword.enqueue(new Callback<FavoriteResponse>() {
//                    @Override
//                    public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
//                        //즐찾 등록
//                        if(response.isSuccessful()){
//                            if(response.body().getMessage().equals("Successfully register favorite station")){
//                                holder.imageButton.setBackgroundResource(R.drawable.favorite_star_fill);
//                            }
//                            //즐찾 해제
//                            else if(response.body().getMessage().equals("Successfully clear favorite station")){
//                                holder.imageButton.setBackgroundResource(R.drawable.favorite_star_empty);
//                            }
//                    }
//                        else {
//                            Log.i("status", "failfailfailfailfailfailfailfailfailfailfail");
////                          ApplicationController.instance!!.makeToast("북마크 실패.")
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<FavoriteResponse> call, Throwable t) {
//                        ApplicationController1.getInstance().makeToast("통신 상태를 확인해주세요");
//                        Log.i("status", "check");
//                    }
//                });
//            }
//        });
        holder.textContent.setText(station.getContent());

        return convertView;
    }

    class CustomViewHolder {
        ImageButton imageButton;
        TextView textContent;
    }

    // Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(GetStationData station) {
        listCustom.add(station);
    }
    public void favorite(){

    }
}
