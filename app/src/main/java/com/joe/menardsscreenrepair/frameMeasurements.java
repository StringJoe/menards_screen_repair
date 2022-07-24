package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;

public class frameMeasurements extends AppCompatActivity {

    String message = "5/16 corner is = to 13/16 in long.\n7/16 corner is = to 3/4 in long.\n\nFirst the user should enter a width and length for the frame, and tap the button next to the width and length for the appropriate fraction.\n\nDo not enter a decimal number as the width or length. Then choose a corner size and either press the calculate button or reset the values\n\nTap Main Menu if you want to go back to start screen";

    EditText screenWidth, screenLength;
    Button bigCornerBtn, smallCornerBtn, calcBtn, resetBtn, menuBtn, lengthCountFraction, widthCountFraction, instructionsBtn;
    TextView totalWidth, totalLength;

    double lengthCount, widthCount;

    double thirteenOver = 1.625;
    double oneOverEight = 1.5;
    double width, length, widthFraction, lengthFraction;
    double cornerToSubtract;

    Format myFormatter = new DecimalFormat("##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_measurements);

        screenWidth = findViewById(R.id.screenWidth);
        screenLength = findViewById(R.id.screenLength);
        lengthCountFraction = findViewById(R.id.lengthFractionCount);
        widthCountFraction = findViewById(R.id.widthFractionCount);

        bigCornerBtn = findViewById(R.id.bigCornerBtn);
        smallCornerBtn = findViewById(R.id.smallCornerBtn);
        calcBtn = findViewById(R.id.calcBtn);
        resetBtn = findViewById(R.id.resetBtn);
        menuBtn = findViewById(R.id.menuBtn4);
        instructionsBtn = findViewById(R.id.instructions);

        totalWidth = findViewById(R.id.totalWidth);
        totalLength = findViewById(R.id.totalLength);

        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMessage();
            }
        });

        lengthCountFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lengthCount <= 14)
                {
                    lengthCount += 1;
                }

                lengthCountFraction.setText(myFormatter.format(lengthCount)+"/16\"");
            }
        });

        widthCountFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(widthCount <= 14)
                {
                    widthCount += 1;
                }

                widthCountFraction.setText(myFormatter.format(widthCount)+"/16\"");
            }
        });

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

                lengthCountFraction.setText("0/16");
                widthCountFraction.setText("0/16");

                lengthCount = 0;
                widthCount = 0;
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width = Double.parseDouble(screenWidth.getText().toString());
                length = Double.parseDouble(screenLength.getText().toString());
                /*
                width = width - cornerToSubtract;

                widthFraction = width - (long) width;

                width = width - widthFraction;
                widthFraction = widthFraction / 0.0625;
                widthFraction = (int)widthFraction;*/

                calculateLengthWidth();

                totalWidth.setText("Width: " + myFormatter.format(width) + " " + myFormatter.format(widthFraction) + "/16\"");
                totalLength.setText("Length: " + myFormatter.format(length) + " " + myFormatter.format(lengthFraction) + "/16\"");
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

    public void calculateLengthWidth()
    {
        // gather data from the editText components
        width = Double.parseDouble(screenWidth.getText().toString());
        length = Double.parseDouble(screenLength.getText().toString());

        addFractionToWidthLength();

        // subtract the corner pieces
        width = width - cornerToSubtract;
        length = length - cornerToSubtract;

        // separate decimal values from width and length
        widthFraction = width - (long) width;
        lengthFraction = length - (long) length;

        // convert width and length back to full number
        width = width - widthFraction;
        length = length - lengthFraction;

        // divide the decimals by 0.0625 to get the numerator
        if(widthFraction > 0)
        {
            widthFraction = widthFraction / 0.0625;
        }
        else
        {
            widthFraction = 0;
        }

        if(lengthFraction > 0)
        {
            lengthFraction = lengthFraction / 0.0625;
        }
        else
        {
            lengthFraction = 0;
        }

    }

    public void addFractionToWidthLength()
    {
        // convert the button data to decimal form
        lengthCount = lengthCount / 16;
        widthCount = widthCount / 16;

        // add the decimals to the width and length values
        width = width + widthCount;
        length = length + lengthCount;
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
}