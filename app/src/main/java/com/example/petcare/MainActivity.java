package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button buttonPW,buttonCG;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPW = findViewById(R.id.buttonW1);
        buttonPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();
            }
        });

        buttonCG =findViewById(R.id.buttonW2);
        buttonCG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, CareGiverLogin.class));

            }
        });
    }

    public void Login() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

}