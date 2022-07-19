package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class frameMeasurements extends AppCompatActivity {

    EditText screenWidth, screenLength;
    Button bigCornerBtn, smallCornerBtn, calcBtn, resetBtn, menuBtn;
    TextView totalWidth, totalLength;

    double thirteenOver = 1.625;
    double oneOverEight = 1.5;
    double width, length;
    double cornerToSubtract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_measurements);

        screenWidth = findViewById(R.id.screenWidth);
        screenLength = findViewById(R.id.screenLength);

        bigCornerBtn = findViewById(R.id.bigCornerBtn);
        smallCornerBtn = findViewById(R.id.smallCornerBtn);
        calcBtn = findViewById(R.id.calcBtn);
        resetBtn = findViewById(R.id.resetBtn);
        menuBtn = findViewById(R.id.menuBtn);

        totalWidth = findViewById(R.id.totalWidth);
        totalLength = findViewById(R.id.totalLength);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = 0;
                length = 0;
                screenWidth.setText("");
                screenLength.setText("");
                screenWidth.setHint("Enter Screen Width");
                screenLength.setHint("Enter Screen Length");
                totalWidth.setText("Cut width to: ");
                totalLength.setText("Cut Length to: ");
                cornerToSubtract = 0;
                smallCornerBtn.setTextColor(Color.WHITE);
                bigCornerBtn.setTextColor(Color.WHITE);
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = Double.parseDouble(screenWidth.getText().toString());
                length = Double.parseDouble(screenLength.getText().toString());

                totalWidth.setText("Cut width to: "+(width-cornerToSubtract));
                totalLength.setText("Cut Length to: "+(length-cornerToSubtract));
            }
        });

        bigCornerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerToSubtract = thirteenOver;
                bigCornerBtn.setEnabled(false);
                smallCornerBtn.setEnabled(true);
                smallCornerBtn.setTextColor(Color.WHITE);
                bigCornerBtn.setTextColor(0xFF03DAC5);
            }
        });

        smallCornerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerToSubtract = oneOverEight;
                bigCornerBtn.setEnabled(true);
                smallCornerBtn.setEnabled(false);
                smallCornerBtn.setTextColor(0xFF03DAC5);
                bigCornerBtn.setTextColor(Color.WHITE);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(frameMeasurements.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}