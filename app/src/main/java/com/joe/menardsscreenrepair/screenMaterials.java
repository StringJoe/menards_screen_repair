package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class screenMaterials extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button resetBtn, costScreen, menuBtn2;

    // buttons to grab sku numbers
    Button screenBtn, frameBtn, splineBtn, cornerBtn, springBtn, plungerBtn,
            spreaderBarBtn, spreaderBarClipsBtn, pullTabsBtn;

    // text views for screen material quantities
    TextView screenQty, frameQty, splineQty;

    Button cornerQty, springQty, plungerQty, spreaderBarQty,
            spreaderBarClipsQty, pullTabsQty;

    // variables to hold count of corner spring spreader etc, quantities
    int cornerCount, springCount, plungerCount, spreaderBarCount, spreaderBarClipCount, pullTabCount;

    double getLargestValue, materialCost, laborCost, totalCost;
    int perimeter;

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

        calculateScreenFrameSplineQty();

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
                totalCost = laborCost + materialCost;
                Intent intent = new Intent(screenMaterials.this, screenCost.class);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
                finish();
            }
        });

        cornerQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerCount += 1;
                cornerQty.setText(""+cornerCount);
                materialCost += 0.2;
            }
        });

        springQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                springCount += 1;
                springQty.setText(""+springCount);
                materialCost += 0.2;
            }
        });

        plungerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plungerBtn.setText("575-8093");
            }
        });

        plungerQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plungerCount += 1;
                plungerQty.setText(""+plungerCount);
                materialCost += 0.2;
            }
        });

        spreaderBarQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarCount += 1;
                spreaderBarQty.setText(""+spreaderBarCount);
                materialCost += 3.99;
            }
        });

        spreaderBarClipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarClipsBtn.setText("575-8048");
            }
        });

        spreaderBarClipsQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarClipCount += 1;
                spreaderBarClipsQty.setText(""+spreaderBarClipCount);
                materialCost += 0.2;
            }
        });

        pullTabsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullTabsBtn.setText("575-8027");
            }
        });

        pullTabsQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullTabCount += 1;
                pullTabsQty.setText(""+pullTabCount);
                materialCost += 0.2;
            }
        });
    }

    public void calculateScreenFrameSplineQty()
    {
        Intent intent = getIntent();
        getLargestValue = intent.getDoubleExtra("largestValue", 0);
        perimeter = intent.getIntExtra("perimeter", 0);
        laborCost = intent.getDoubleExtra("laborCost", 0);
        //splineQty.setText(""+perimeter);
    }

    public void screenMaterial(View v) {
        android.widget.PopupMenu popup = new android.widget.PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.screen_materials);
        popup.show();
    }

    public void frameMaterial(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.frame_materials);
        popup.show();
    }

    public void splineMaterial (View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.spline_materials);
        popup.show();
    }

    public void cornerMaterial(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.corner_material);
        popup.show();
    }

    public void springMaterial(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.spring_materials);
        popup.show();
    }

    public void spreaderBarMaterial(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.spreader_bar_materials);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.screen1:
                screenBtn.setText("575-8098");
                screenQty.setText(""+Math.round(getLargestValue/12));
                materialCost += Math.round(getLargestValue/12.0) * 1.99;
                return true;
            case R.id.screen2:
                screenBtn.setText("575-8099");
                screenQty.setText(""+Math.round(getLargestValue/12));
                materialCost += Math.round(getLargestValue/12) * 2.99;
                return true;
            case R.id.screen3:
                screenBtn.setText("575-8100");
                screenQty.setText(""+Math.round(getLargestValue/12));
                materialCost += Math.round(getLargestValue/12) * 3.29;
                return true;
            case R.id.screen4:
                screenBtn.setText("575-8101");
                screenQty.setText(""+Math.round(getLargestValue/12));
                materialCost += Math.round(getLargestValue/12) * 5.99;
                return true;
            case R.id.frame1:
                frameBtn.setText("575-8033");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.frame2:
                frameBtn.setText("575-8067");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.frame3:
                frameBtn.setText("575-8034");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.frame4:
                frameBtn.setText("575-8038");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.frame5:
                frameBtn.setText("575-8035");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.frame6:
                frameBtn.setText("575-8029");
                frameQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*1.19;
                return true;
            case R.id.black1:
                splineBtn.setText("575-8053");
                splineQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*0.1;
                return true;
            case R.id.black2:
                splineBtn.setText("575-8086");
                splineQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*0.1;
                return true;
            case R.id.black3:
                splineBtn.setText("575-8054");
                splineQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*0.1;
                return true;
            case R.id.black4:
                splineBtn.setText("575-8052");
                splineQty.setText(""+(perimeter/12));
                materialCost += Math.round(perimeter/12)*0.1;
                return true;
            case R.id.corner1:
                cornerBtn.setText("575-8047");
                materialCost += cornerCount*0.2;
                return true;
            case R.id.corner2:
                cornerBtn.setText("575-8031");
                return true;
            case R.id.corner3:
                cornerBtn.setText("575-8045");
                return true;
            case R.id.corner4:
                cornerBtn.setText("575-8070");
                return true;
            case R.id.corner5:
                cornerBtn.setText("575-8046");
                return true;
            case R.id.corner6:
                cornerBtn.setText("575-8039");
                return true;
            case R.id.flat:
                springBtn.setText("575-8094");
                //materialCost += 0.2*springCount;
                return true;
            case R.id.spring:
                springBtn.setText("575-8028");
                return true;
            case R.id.bar1:
                spreaderBarBtn.setText("575-8036");
                //materialCost += 3.99*spreaderBarCount;
                return true;
            case R.id.bar2:
                spreaderBarBtn.setText("575-8037");
                return true;
            case R.id.bar3:
                spreaderBarBtn.setText("575-8038");
                return true;
            default:
                return false;
        }

    }
}