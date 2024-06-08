package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CareGiverHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome"+username,Toast.LENGTH_SHORT).show();

        CardView logout =findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(CareGiverHome.this, MainActivity.class));
            }
        });
        CardView caregiverjob= findViewById(R.id.Caregiver_Mjob);
        caregiverjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CareGiverHome.this, Caregiver_Make_Job.class));
            }
        });

        CardView viewpet= findViewById(R.id.viewpet);
        viewpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CareGiverHome.this, View_pet.class));
            }
        });

        CardView viewapp= findViewById(R.id.Viewappoinments);
        viewapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CareGiverHome.this, View_appointment.class));
            }
        });


        CardView about = findViewById(R.id.About);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CareGiverHome.this, About.class));
            }
        });

        CardView feed = findViewById(R.id.Feedbacks);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CareGiverHome.this, View_feedback.class));
            }
        });

    }
}