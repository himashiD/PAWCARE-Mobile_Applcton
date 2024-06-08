package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class View_pet extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet);

        databaseHelperClass = new DatabaseHelperClass(View_pet.this);
        Button read = findViewById(R.id.refresh_data);
        datalist = findViewById(R.id.all_data_list);
        datalist_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
    }

    private void refreshData() {
        datalist_count.setText("All Data Count : "+databaseHelperClass.getTotal());
        List<PetRegisterClass> registerpetClassList = databaseHelperClass.getAllpet();
        datalist.setText("");
        for (PetRegisterClass registerpetClass : registerpetClassList){
            datalist.append("ID : "+registerpetClass.getId()+" | Pet Category : "+registerpetClass.getPetcategory()+" | Pet Name : "+registerpetClass.getPetname()+ " | Pet Age : "+registerpetClass.getPetage()+" | Bread : "+registerpetClass.getPetbread()+ " | Gender : "+registerpetClass.getPetgender()+" | Colour : "+registerpetClass.getPetcolour()+" | Special Note : "+registerpetClass.getPetnote()+"\n\n");
        }
    }
}