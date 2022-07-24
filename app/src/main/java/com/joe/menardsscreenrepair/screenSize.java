package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class screenSize extends AppCompatActivity {

    EditText screenWidth, screenLength;
    Button equalsBtn, resetBtn, mainMenuBtn, materialsBtn;
    TextView squareFt;

    int squareFootage, perimeter, width, length;

    double largestValue;

    int laborCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_size);

        screenWidth = findViewById(R.id.width);
        screenLength = findViewById(R.id.length);

        equalsBtn = findViewById(R.id.equals);
        resetBtn = findViewById(R.id.resetBtn);
        mainMenuBtn = findViewById(R.id.menuBtn1);
        materialsBtn = findViewById(R.id.screenMaterials);

        squareFt = findViewById(R.id.squareFootage);

        materialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(screenSize.this, screenMaterials.class);
                intent.putExtra("largestValue", largestValue);
                intent.putExtra("perimeter", perimeter);
                intent.putExtra("laborCost", laborCost);
                startActivity(intent);
                finish();
            }
        });

        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
                Intent intent = new Intent(screenSize.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScreenFootage();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }

    public void calculateScreenFootage()
    {
        // get width and length from editText components
        width = Integer.parseInt(screenWidth.getText().toString());
        length = Integer.parseInt(screenLength.getText().toString());

        //calculate perimeter for spline values
        perimeter = (width*2) + (length*2);

        // calculate whether screen is < or > 49 inches
        if(width < 49 && length < 49)
        {
            if(width < length)
            {
                largestValue = width;
            }
            else
            {
                largestValue = length;
            }
        }
        else
        {
            // if either side is longer than 48" than calculate qty based on largest value
            if(width > length)
            {
                largestValue = width;
            }
            else if(length > width)
            {
                largestValue = length;
            }
            else if (width == length)
            {
                largestValue = width;
            }
        }

        // calculate square footage of screen
        squareFootage = (width * length) / 144;

        // calculate labor cost based on square footage
        if(squareFootage > 16)
        {
            laborCost += 20;
        }
        else if(squareFootage < 16)
        {
            laborCost += 10;
        }

        squareFt.setText("Sq ft: "+Math.round(squareFootage));

    }

    public void resetValues()
    {
        screenWidth.setText("");
        screenLength.setText("");
        screenWidth.setHint("Enter Width");
        screenLength.setHint("Enter Length");

        squareFt.setText("Sq ft: ");

        width = 0;
        length = 0;
        squareFootage = 0;
    }
}