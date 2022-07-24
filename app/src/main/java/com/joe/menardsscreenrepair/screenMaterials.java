package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class screenMaterials extends AppCompatActivity {

    Button resetBtn, costScreen, menuBtn2;

    // buttons to grab sku numbers
    Button screenBtn, frameBtn, splineBtn, cornerBtn, springBtn, plungerBtn,
            spreaderBarBtn, spreaderBarClipsBtn, pullTabsBtn;

    // text views for screen material quantities
    TextView screenQty, frameQty, splineQty;

    Button cornerQty, springQty, plungerQty, spreaderBarQty,
            spreaderBarClipsQty, pullTabsQty;

    // variables to hold count of corner spring spreader etc, quantities
    int cornerCount, springCount, spreaderBarCount, spreaderBarClipCount, pullTabCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_materials);

        resetBtn = findViewById(R.id.resetBtn);
        costScreen = findViewById(R.id.costScreen);
        menuBtn2 = findViewById(R.id.menuBtn2);

        screenBtn = findViewById(R.id.screenBtn);
        frameBtn = findViewById(R.id.frameBtn);
        splineBtn = findViewById(R.id.splineBtn);
        cornerBtn = findViewById(R.id.cornerBtn);
        springBtn = findViewById(R.id.springBtn);
        plungerBtn = findViewById(R.id.plungerBtn);
        spreaderBarBtn = findViewById(R.id.spreaderBarBtn);
        spreaderBarClipsBtn = findViewById(R.id.spreaderClipsBtn);
        pullTabsBtn = findViewById(R.id.pullTabsBtn);

        screenQty = findViewById(R.id.screenQty);
        frameQty = findViewById(R.id.frameQty);
        splineQty = findViewById(R.id.splineQty);
        cornerQty = findViewById(R.id.cornersQty);
        springQty = findViewById(R.id.springQty);
        plungerQty = findViewById(R.id.plungerQty);
        spreaderBarQty = findViewById(R.id.spreaderBarQty);
        spreaderBarClipsQty = findViewById(R.id.spreaderClipsQty);
        pullTabsQty = findViewById(R.id.pullTabsQty);

        menuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(screenMaterials.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        costScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(screenMaterials.this, screenCost.class);
                startActivity(intent);
                finish();
            }
        });

    }
}