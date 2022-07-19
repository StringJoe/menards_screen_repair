package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button screenSize, screenMaterials, screenCost, frameMeasurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenSize = findViewById(R.id.sizeBtn);
        screenMaterials = findViewById(R.id.materialsBtn);
        screenCost = findViewById(R.id.costBtn);
        frameMeasurement = findViewById(R.id.measurementBtn);

        screenSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, screenSize.class);
                startActivity(intent);
                finish();
            }
        });

        screenMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, screenMaterials.class);
                startActivity(intent);
                finish();
            }
        });

        screenCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, screenCost.class);
                startActivity(intent);
                finish();
            }
        });

        frameMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, frameMeasurements.class);
                startActivity(intent);
                finish();
            }
        });
    }
}