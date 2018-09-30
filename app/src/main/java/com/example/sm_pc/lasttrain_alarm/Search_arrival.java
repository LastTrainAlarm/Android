package com.example.sm_pc.lasttrain_alarm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;


public class Search_arrival extends AppCompatActivity {


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_arrival);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View view) {
                finish();
            } });

    }
}
