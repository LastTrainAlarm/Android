package com.example.sm_pc.lasttrain_alarm

data class SearchStationResponse(
        var status: Boolean,
        var message : String,
        var data : ArrayList<StationListData>?
)