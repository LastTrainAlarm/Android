package com.example.sm_pc.lasttrain_alarm

import retrofit2.http.*


interface NetworkService1 {
    //역이름검색
    @GET("search")
    fun getStationList(
            @Header("user_token") user_token: String)
            : retrofit2.Call<SearchStationResponse>
    //주변 역 검색
    @GET("here")
    fun getNearStationList(
            @Header("user_token") user_token: String)
            : retrofit2.Call<NearStaionResponse>

//    //스크랩
//    @GET("mypage/boardScrapList")
//    fun getScrapList(
//            @Header("user_token") user_token: String)
//            : Call<CommunityResponse>
//
//    //스크랩
//    @FormUrlEncoded
//    @PUT("board/scrap")
//    fun communityScrap(
//            @Header("user_token") user_token: String,
//            @Field("board_id") board_id: Int)
//            : Call<CommunityLikeResponse>
//
//
//    //로그인
//    @FormUrlEncoded
//    @POST("home/signin")
//    fun signIn(
//            @Field("user_email") user_email: String,
//            @Field("user_uid") user_uid: String
//    ): Call<SignInResponse>
//
//
//    //회원탈퇴
//    @HTTP(method = "DELETE", path = "mypage/signout", hasBody = true)
//    fun signOut(
//            @Header("user_token") user_token: String)
//            : Call<MyPageSignOutResponse>//response 재사용

}