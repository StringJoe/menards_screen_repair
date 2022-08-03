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
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

public class screenMaterials extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    // cost of materials
    double charcoalFiber = 1.99;
    double brightAluminum = 2.99;
    double charcoalAluminum = 3.29;
    double petDefense = 5.99;

    double storeScreenCost, storeFrameCost, storeSplineCost;

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
            " for the other materials, just tap the 0 underneath QTY and enter the quantity you want.\n\n" +
            "Once everything is chosen, you can either reset the materials, or tap TOTAL COST to see the cost of the screen";

    Button resetBtn, costScreen, instructionsBtn;

    // buttons to grab sku numbers
    Button screenBtn, frameBtn, splineBtn, cornerBtn, springBtn, plungerBtn,
            spreaderBarBtn, spreaderBarClipsBtn, pullTabsBtn;

    // text views for screen material quantities
    TextView screenQty, frameQty, splineQty;

    EditText cornerQty, springQty, plungerQty, spreaderBarQty,
            spreaderBarClipsQty, pullTabsQty;

    // variables to hold count of corner spring spreader etc, quantities
    int cornerCount, springCount, plungerCount, spreaderBarCount, spreaderBarClipCount, pullTabCount;

    double getLargestValue, materialCost, laborCost, totalCost;
    int perimeter;

    Boolean plunger = false;
    Boolean spreader = false;
    Boolean tab = false;

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
                grabMaterialQuantities();
                totalCost = laborCost + materialCost;
                Intent intent = new Intent(screenMaterials.this, screenCost.class);
                intent.putExtra("totalCost", totalCost);
                startActivity(intent);
                finish();
            }
        });

        plungerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(plunger == false)
                {
                    plungerBtn.setText("Plunger Latch");
                    plungerBtn.setBackgroundColor(Color.GRAY);
                    plunger = true;
                }
                else
                {
                    plungerBtn.setText("Material");
                    plunger = false;
                    plungerBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
                }

            }
        });

        spreaderBarClipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spreader == false)
                {
                    spreaderBarClipsBtn.setText("Spreader Bar Clips");
                    spreaderBarClipsBtn.setBackgroundColor(Color.GRAY);
                    spreader = true;
                }
                else
                {
                    spreaderBarClipsBtn.setText("Material");
                    spreaderBarClipsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
                    spreader = false;
                }

            }
        });

        pullTabsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(tab == false)
                {
                    pullTabsBtn.setText("Pull Tabs");
                    pullTabsBtn.setBackgroundColor(Color.GRAY);
                    tab = true;
                }
                else
                {
                    pullTabsBtn.setText("Material");
                    pullTabsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
                    tab = false;
                }

            }
        });

    }

    public void grabMaterialQuantities()
    {
        //width = Integer.parseInt(screenWidth.getText().toString());
        //length = Integer.parseInt(screenLength.getText().toString());

        if(!cornerQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(cornerQty.getText().toString()) * cornerCost;
        }

        if(!springQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(springQty.getText().toString()) * springCost;
        }

        if(!plungerQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(plungerQty.getText().toString()) * plungerCost;
        }

        if(!spreaderBarQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(spreaderBarQty.getText().toString()) * spreaderBarCost;
        }

        if(!spreaderBarClipsQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(spreaderBarClipsQty.getText().toString()) * spreaderClipsCost;
        }

        if(!pullTabsQty.getText().toString().equals(""))
        {
            materialCost += Integer.parseInt(pullTabsQty.getText().toString()) * pullTabsCost;
        }

        materialCost += storeScreenCost + storeFrameCost + storeSplineCost;
    }

    public void resetValues()
    {
        // reset the cost of materials
        materialCost = 0;

        // resetting screen values
        screenBtn.setText("Material");

        // resetting frame values
        frameBtn.setText("Material");

        // resetting spline values
        splineBtn.setText("Material");

        // resetting corner values
        cornerBtn.setText("Material");
        cornerQty.setText("");
        cornerQty.setHint("0");

        // resetting spring values
        springQty.setText("");
        springQty.setHint("0");
        springBtn.setText("Material");

        // resetting plunger latch values
        plungerBtn.setText("Material");
        plungerBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
        plungerQty.setText("");
        plungerQty.setHint("0");
        plunger = false;

        // spreader bar values
        spreaderBarQty.setText("");
        spreaderBarQty.setHint("0");
        spreaderBarBtn.setText("Material");

        //spreader bar clips values
        spreaderBarClipsQty.setText("");
        spreaderBarClipsQty.setHint("0");
        spreaderBarClipsBtn.setText("Material");
        spreaderBarClipsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
        spreader = false;

        // pull tabs values
        pullTabsQty.setText("");
        pullTabsQty.setHint("0");
        pullTabsBtn.setText("Material");
        pullTabsBtn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.second_color, null));
        tab = false;
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
                screenMenuValues("Charcoal Fiberglass", charcoalFiber);
                return true;
            case R.id.screen2:
                screenMenuValues("Bright Aluminum", brightAluminum);
                return true;
            case R.id.screen3:
                screenMenuValues("Charcoal Aluminum", charcoalAluminum);
                return true;
            case R.id.screen4:
                screenMenuValues("Pet Defense", petDefense);
                return true;
            case R.id.frame1:
                frameMenuValues("5/16 Aluminum", frameCost);
                return true;
            case R.id.frame2:
                frameMenuValues("7/16 Aluminum", frameCost);
                return true;
            case R.id.frame3:
                frameMenuValues("5/16 Bronze", frameCost);
                return true;
            case R.id.frame4:
                frameMenuValues("7/16 Bronze", frameCost);
                return true;
            case R.id.frame5:
                frameMenuValues("5/16 White", frameCost);
                return true;
            case R.id.frame6:
                frameMenuValues("7/16 White", frameCost);
                return true;
            case R.id.black1:
                splineMenuValues("Black Spline", splineCost);
                return true;
            case R.id.gray1:
                splineMenuValues("Gray Spline", splineCost);
                return true;
            case R.id.corner1:
                cornerMenuValues("5/16 Aluminum");
                return true;
            case R.id.corner2:
                cornerMenuValues("7/16 Aluminum");
                return true;
            case R.id.corner3:
                cornerMenuValues("5/16 Bronze");
                return true;
            case R.id.corner4:
                cornerMenuValues("7/16 Bronze");
                return true;
            case R.id.corner5:
                cornerMenuValues("5/16 White");
                return true;
            case R.id.corner6:
                cornerMenuValues("7/16 White");
                return true;
            case R.id.flat:
                springMenuValues("3 1/8\" Flat Spring");
                return true;
            case R.id.spring:
                springMenuValues("2 3/4 Corner Spring");
                return true;
            case R.id.bar1:
                spreaderBarMenuValues("5/8\" Aluminum");
                return true;
            case R.id.bar2:
                spreaderBarMenuValues("5/8\" Bronze");
                return true;
            case R.id.bar3:
                spreaderBarMenuValues("5/8\" White");
                return true;
            default:
                return false;
        }

    }
    public void screenMenuValues(String sku, double cost)
    {
        screenBtn.setText(sku);
        screenQty.setText(""+Math.round(getLargestValue/12));
        storeScreenCost = Math.round(getLargestValue/12.0) * cost;
    }

    public void frameMenuValues(String sku, double cost)
    {
        frameBtn.setText(sku);
        frameQty.setText(""+(perimeter/12));
        storeFrameCost = Math.round(perimeter/12)*cost;
    }

    public void splineMenuValues(String sku, double cost)
    {
        splineBtn.setText(sku);
        splineQty.setText(""+(perimeter/12));
        storeSplineCost = Math.round(perimeter/12)*cost;
    }

    public void cornerMenuValues(String sku)
    {
        cornerBtn.setText(sku);
    }

    public void springMenuValues(String sku)
    {
        springBtn.setText(sku);
    }

    public void spreaderBarMenuValues(String sku)
    {
        spreaderBarBtn.setText(sku);
    }
}