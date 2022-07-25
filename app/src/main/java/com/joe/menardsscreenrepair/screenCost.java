package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;

public class screenCost extends AppCompatActivity {

    double totalCost;

    TextView totalCostView;

    Button menuBtn3;

    Format myFormatter = new DecimalFormat("####.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_cost);

        totalCostView = findViewById(R.id.totalCost);
        menuBtn3 = findViewById(R.id.menuBtn3);

        getScreenCost();

        menuBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCost = 0;
                Intent intent = new Intent(screenCost.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getScreenCost()
    {
        Intent costIntent = getIntent();
        totalCost = costIntent.getDoubleExtra("totalCost", 0);
        totalCostView.setText("Total Cost: $"+myFormatter.format(totalCost));
    }

}