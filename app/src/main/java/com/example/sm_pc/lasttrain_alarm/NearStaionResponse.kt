package com.example.sm_pc.lasttrain_alarm

data class NearStaionResponse (
        var status: Boolean,
        var message : String,
        var data : ArrayList<StationListData>?
)