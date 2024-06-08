package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewCaregivers extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist_care;
    TextView datalist_count_care;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_caregivers);

        databaseHelperClass = new DatabaseHelperClass(ViewCaregivers.this);
        Button read = findViewById(R.id.viewrefresh_data);
        datalist_care = findViewById(R.id.viewcall_data_list);
        datalist_count_care = findViewById(R.id.viewcaregiverdata_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carefreshData();
            }
        });


        Button bookapp = findViewById(R.id.viewcaregiverappointment_btn);
        bookapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewCaregivers.this, Book_Appointment.class));
            }
        });

        Button findback = findViewById(R.id.viewback_btn);
        findback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewCaregivers.this, Home.class));
            }
        });


    }

    private void carefreshData() {
        datalist_count_care.setText("All Account Count : "+databaseHelperClass.getTotalcaregiverjob());
        List<CaregiverMakeJobClass> caregiverMakeJobClassList = databaseHelperClass.getAllcaregiverjob();
        datalist_care.setText("");
        for (CaregiverMakeJobClass caregiverMakeJobClass : caregiverMakeJobClassList){
            datalist_care.append(" No : "+caregiverMakeJobClass.getCid()+ "\n" +
                    " Caregiver ID : "+caregiverMakeJobClass.getCareid()+ "\n" +
                    " Caregiver Name : "+caregiverMakeJobClass.getCarename()+ "\n" +
                    " Caregiver Contact No : "+caregiverMakeJobClass.getCarecontact()+ "\n" +
                    " Caregiver Location : "+caregiverMakeJobClass.getCarelocation()+ "\n" +
                    " Caregiver Payment : "+caregiverMakeJobClass.getCarepayment()+ "\n" +
                    " Experience : "+caregiverMakeJobClass.getCareexperience()+" \n\n");
        }
    }
}