package com.example.sm_pc.lasttrain_alarm.Network;

import com.example.sm_pc.lasttrain_alarm.FavoriteResponse;
import com.example.sm_pc.lasttrain_alarm.GetFavoriteResponse;
import com.example.sm_pc.lasttrain_alarm.NearStaionResponse;
import com.example.sm_pc.lasttrain_alarm.Search;
import com.example.sm_pc.lasttrain_alarm.SearchStationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkService {
    @GET("/search/{keyword}")
    Call<SearchStationResponse> getSearchList(
            @Header("token") String token,
            @Path("keyword") String keyword);

    @GET("/search/here/{x}/{y}")
    Call<NearStaionResponse> getNearStaionList(
            @Header("token") String token,
            @Path("x") Float x,
            @Path("y") Float y);
    @FormUrlEncoded
    @PUT("/fav")
    Call<FavoriteResponse> putFavorite(
            @Header("user_token") String token,
            @Field("keyword") String keyword
    );
    @GET("/fav")
    Call<GetFavoriteResponse> getFavoriteList(
            @Header("token") String token);
}
