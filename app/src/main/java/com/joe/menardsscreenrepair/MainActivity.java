package com.joe.menardsscreenrepair;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String message = "Screen size will take all the the measurements needed to calculate " +
            "the quantities needed as well as how much the screen will cost before tax.\n\n" +
            "Frame measurements will take the width and length measurements so that the worker " +
            "can accurately cut the frame to the necessary length based on the corner used.";

    Button screenSize, instructions, frameMeasurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenSize = findViewById(R.id.sizeBtn);
        instructions = findViewById(R.id.menuInstructions);
        frameMeasurement = findViewById(R.id.measurementBtn);

        screenSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, screenSize.class);
                startActivity(intent);
                finish();
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMessage();
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