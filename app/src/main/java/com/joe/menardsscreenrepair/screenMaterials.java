package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class screenMaterials extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    // cost of materials
    double charcoalFiber = 1.99;
    double brightAluminum = 2.99;
    double charcoalAluminum = 3.29;
    double petDefense = 5.99;

    double frameCost = 1.19;

    double splineCost = 0.1;

    double cornerCost = 0.2;

    double springCost = 0.2;

    double plungerCost = 0.2;

    double spreaderBarCost = 3.99;

    double spreaderClipsCost = 0.2;

    double pullTabsCost = 0.2;

    // variable for instructions message
    String message = "To add a material, just tap on one of the SKU numbers underneath SKU. This will pull up" +
            " either a menu of different materials to choose from, or just change it to the appropriate SKU number\n\n" +
            "The Screen, Frame and Spline materials will automatically be filled after the material is chosen." +
            " for the other materials, just tap the 0 underneath QTY and it will increase the count by 1.\n\n" +
            "Once everything is chosen, you can either reset the materials, or tap TOTAL COST to see the cost of the screen";

    Button resetBtn, costScreen, instructionsBtn;

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

        instructionsBtn = findViewById(R.id.instructionsBtn);
        costScreen = findViewById(R.id.costScreen);
        resetBtn = findViewById(R.id.resetBtn);

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

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMessage();
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
                materialCost += cornerCost;
            }
        });

        springQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                springCount += 1;
                springQty.setText(""+springCount);
                materialCost += springCost;
            }
        });

        plungerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plungerBtn.setText("575-8093");
                plungerBtn.setEnabled(false);
                plungerBtn.setBackgroundColor(Color.GRAY);
            }
        });

        plungerQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plungerCount += 1;
                plungerQty.setText(""+plungerCount);
                materialCost += plungerCost;
            }
        });

        spreaderBarQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarCount += 1;
                spreaderBarQty.setText(""+spreaderBarCount);
                materialCost += spreaderBarCost;
            }
        });

        spreaderBarClipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarClipsBtn.setText("575-8048");
                spreaderBarClipsBtn.setEnabled(false);
                spreaderBarClipsBtn.setBackgroundColor(Color.GRAY);
            }
        });

        spreaderBarClipsQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreaderBarClipCount += 1;
                spreaderBarClipsQty.setText(""+spreaderBarClipCount);
                materialCost += spreaderClipsCost;
            }
        });

        pullTabsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullTabsBtn.setText("575-8027");
                pullTabsBtn.setEnabled(false);
                pullTabsBtn.setBackgroundColor(Color.GRAY);
            }
        });

        pullTabsQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullTabCount += 1;
                pullTabsQty.setText(""+pullTabCount);
                materialCost += pullTabsCost;
            }
        });
    }

    public void resetValues()
    {
        // reset the cost of materials
        materialCost = 0;

        // resetting screen values
        screenBtn.setText("575-");
        screenBtn.setEnabled(true);
        screenBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // resetting frame values
        frameBtn.setText("575-");
        frameBtn.setEnabled(true);
        frameBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // resetting spline values
        splineBtn.setText("575-");
        splineBtn.setEnabled(true);
        splineBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // resetting corner values
        cornerBtn.setText("575-");
        cornerQty.setText("0");
        cornerCount = 0;
        cornerBtn.setEnabled(true);
        cornerBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // resetting spring values
        springCount = 0;
        springQty.setText(""+springCount);
        springBtn.setText("575-");
        springBtn.setEnabled(true);
        springBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // resetting plunger latch values
        plungerCount = 0;
        plungerBtn.setText("575-");
        plungerQty.setText(""+plungerCount);
        plungerBtn.setEnabled(true);
        plungerBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // spreader bar values
        spreaderBarCount = 0;
        spreaderBarQty.setText(""+spreaderBarCount);
        spreaderBarBtn.setText("575-");
        spreaderBarBtn.setEnabled(true);
        spreaderBarBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        //spreader bar clips values
        spreaderBarClipCount = 0;
        spreaderBarClipsQty.setText(""+spreaderBarClipCount);
        spreaderBarClipsBtn.setText("575-");
        spreaderBarClipsBtn.setEnabled(true);
        spreaderBarClipsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));

        // pull tabs values
        pullTabCount = 0;
        pullTabsQty.setText(""+pullTabCount);
        pullTabsBtn.setText("575-");
        pullTabsBtn.setEnabled(true);
        pullTabsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.menards_green, null));
    }

    public void calculateScreenFrameSplineQty()
    {
        Intent intent = getIntent();
        getLargestValue = intent.getDoubleExtra("largestValue", 0);
        perimeter = intent.getIntExtra("perimeter", 0);
        laborCost = intent.getIntExtra("laborCost", 0);
    }

    public void showDialogMessage()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Instructions")
                .setMessage(message)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        alertDialog.create();
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
                screenMenuValues("575-8098", charcoalFiber);
                return true;
            case R.id.screen2:
                screenMenuValues("575-8099", brightAluminum);
                return true;
            case R.id.screen3:
                screenMenuValues("575-8100", charcoalAluminum);
                return true;
            case R.id.screen4:
                screenMenuValues("575-8101", petDefense);
                return true;
            case R.id.frame1:
                frameMenuValues("575-8033", frameCost);
                return true;
            case R.id.frame2:
                frameMenuValues("575-8067", frameCost);
                return true;
            case R.id.frame3:
                frameMenuValues("575-8034", frameCost);
                return true;
            case R.id.frame4:
                frameMenuValues("575-8038", frameCost);
                return true;
            case R.id.frame5:
                frameMenuValues("575-8035", frameCost);
                return true;
            case R.id.frame6:
                frameMenuValues("575-8029", frameCost);
                return true;
            case R.id.black1:
                splineMenuValues("575-8053", splineCost);
                return true;
            case R.id.black2:
                splineMenuValues("575-8086", splineCost);
                return true;
            case R.id.black3:
                splineMenuValues("575-8054", splineCost);
                return true;
            case R.id.black4:
                splineMenuValues("575-8052", splineCost);
                return true;
            case R.id.black5:
                splineMenuValues("575-8017", splineCost);
                return true;
            case R.id.black6:
                splineMenuValues("575-8088", splineCost);
                return true;
            case R.id.black7:
                splineMenuValues("575-8025", splineCost);
                return true;
            case R.id.black8:
                splineMenuValues("575-8090", splineCost);
                return true;
            case R.id.black9:
                splineMenuValues("575-8078", splineCost);
                return true;
            case R.id.black10:
                splineMenuValues("575-8079", splineCost);
                return true;
            case R.id.black11:
                splineMenuValues("575-8055", splineCost);
                return true;
            case R.id.black12:
                splineMenuValues("575-7019", splineCost);
                return true;
            case R.id.gray1:
                splineMenuValues("575-8018", splineCost);
                return true;
            case R.id.gray2:
                splineMenuValues("575-8087", splineCost);
                return true;
            case R.id.gray3:
                splineMenuValues("575-8019", splineCost);
                return true;
            case R.id.gray4:
                splineMenuValues("575-8020", splineCost);
                return true;
            case R.id.gray5:
                splineMenuValues("575-8021", splineCost);
                return true;
            case R.id.gray6:
                splineMenuValues("575-8089", splineCost);
                return true;
            case R.id.gray7:
                splineMenuValues("575-8026", splineCost);
                return true;
            case R.id.gray8:
                splineMenuValues("575-8091", splineCost);
                return true;
            case R.id.gray9:
                splineMenuValues("575-8081", splineCost);
                return true;
            case R.id.gray10:
                splineMenuValues("575-8092", splineCost);
                return true;
            case R.id.gray11:
                splineMenuValues("575-8023", splineCost);
                return true;
            case R.id.gray12:
                splineMenuValues("575-8024", splineCost);
                return true;
            case R.id.corner1:
                cornerMenuValues("575-8047");
                return true;
            case R.id.corner2:
                cornerMenuValues("575-8031");
                return true;
            case R.id.corner3:
                cornerMenuValues("575-8045");
                return true;
            case R.id.corner4:
                cornerMenuValues("575-8070");
                return true;
            case R.id.corner5:
                cornerMenuValues("575-8046");
                return true;
            case R.id.corner6:
                cornerMenuValues("575-8039");
                return true;
            case R.id.flat:
                springMenuValues("575-8094");
                return true;
            case R.id.spring:
                springMenuValues("575-8028");
                return true;
            case R.id.bar1:
                spreaderBarMenuValues("575-8036");
                return true;
            case R.id.bar2:
                spreaderBarMenuValues("575-8037");
                return true;
            case R.id.bar3:
                spreaderBarMenuValues("575-8038");
                return true;
            default:
                return false;
        }

    }
    public void screenMenuValues(String sku, double cost)
    {
        screenBtn.setText(sku);
        screenBtn.setEnabled(false);
        screenBtn.setBackgroundColor(Color.GRAY);
        screenQty.setText(""+Math.round(getLargestValue/12));
        materialCost += Math.round(getLargestValue/12.0) * cost;
    }

    public void frameMenuValues(String sku, double cost)
    {
        frameBtn.setText(sku);
        frameBtn.setEnabled(false);
        frameBtn.setBackgroundColor(Color.GRAY);
        frameQty.setText(""+(perimeter/12));
        materialCost += Math.round(perimeter/12)*cost;
    }

    public void splineMenuValues(String sku, double cost)
    {
        splineBtn.setText(sku);
        splineQty.setText(""+(perimeter/12));
        splineBtn.setEnabled(false);
        splineBtn.setBackgroundColor(Color.GRAY);
        materialCost += Math.round(perimeter/12)*cost;
    }

    public void cornerMenuValues(String sku)
    {
        cornerBtn.setText(sku);
        cornerBtn.setEnabled(false);
        cornerBtn.setBackgroundColor(Color.GRAY);
    }

    public void springMenuValues(String sku)
    {
        springBtn.setText(sku);
        springBtn.setEnabled(false);
        springBtn.setBackgroundColor(Color.GRAY);
    }

    public void spreaderBarMenuValues(String sku)
    {
        spreaderBarBtn.setText(sku);
        spreaderBarBtn.setEnabled(false);
        spreaderBarBtn.setBackgroundColor(Color.GRAY);
    }
}