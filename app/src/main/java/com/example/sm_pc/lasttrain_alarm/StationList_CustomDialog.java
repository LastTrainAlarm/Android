package com.example.sm_pc.lasttrain_alarm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class StationList_CustomDialog extends Dialog {
    private static final int LAYOUT = R.layout.search_list_dialog;



    public StationList_CustomDialog(Context context) {

        super(context);

    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);

    }

}
