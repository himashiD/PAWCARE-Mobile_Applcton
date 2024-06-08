package com.example.petcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class Register_pet extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        databaseHelperClass = new DatabaseHelperClass(Register_pet.this);
        Button delete = findViewById(R.id.delete_data);
        Button insert = findViewById(R.id.insert_data);
        Button update = findViewById(R.id.update_data);
        Button read = findViewById(R.id.refresh_data);
        datalist = findViewById(R.id.all_data_list);
        datalist_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
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

    private void showDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petdata_delete_dialog,null);
        al.setView(view);
        final EditText id_input = view.findViewById(R.id.id_input);
        Button delete_btn = view.findViewById(R.id.delete_btn);

        final AlertDialog alertDialog=al.show();
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deletepet(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });

    }


    public void showUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petdata_update_id_dialog,null);
        al.setView(view);
        final EditText id_input;
        id_input = view.findViewById(R.id.id_input);
        Button fetch_btn = view.findViewById(R.id.update_id_btn);
        final AlertDialog alertDialog=al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });

    }


    private void showDataDialog(final String id) {
        PetRegisterClass registerpetClass = databaseHelperClass.getpet(Integer.parseInt(id));
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petdata_update_dialog,null);
        final EditText petcategory = view.findViewById(R.id.petcategory);
        final EditText petname = view.findViewById(R.id.petname);
        final EditText petage = view.findViewById(R.id.petage);
        final EditText petbread = view.findViewById(R.id.petbread);
        final EditText petgender = view.findViewById(R.id.petgender);
        final EditText petcolour = view.findViewById(R.id.petcolour);
        final EditText petnote = view.findViewById(R.id.petnote);
        Button update_Btn =view.findViewById(R.id.update_btn);
        al.setView(view);

        petcategory.setText(registerpetClass.getPetcategory());
        petname.setText(registerpetClass.getPetname());
        petage.setText(registerpetClass.getPetage());
        petbread.setText(registerpetClass.getPetbread());
        petgender.setText(registerpetClass.getPetgender());
        petcolour.setText(registerpetClass.getPetcolour());
        petnote.setText(registerpetClass.getPetnote());

        final AlertDialog alertDialog=al.show();

        update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetRegisterClass registerpetClass = new PetRegisterClass();
                registerpetClass.setPetcategory(petcategory.getText().toString());
                registerpetClass.setId(id);
                registerpetClass.setPetname(petname.getText().toString());
                registerpetClass.setPetage(petage.getText().toString());
                registerpetClass.setPetbread(petbread.getText().toString());
                registerpetClass.setPetgender(petgender.getText().toString());
                registerpetClass.setPetcolour(petcolour.getText().toString());
                registerpetClass.setPetnote(petnote.getText().toString());
                databaseHelperClass.updatepet(registerpetClass);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }



    private void showInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petdata_insert_dialog,null);
        final EditText petcategory = view.findViewById(R.id.petcategory);
        final EditText petname = view.findViewById(R.id.petname);
        final EditText petage = view.findViewById(R.id.petage);
        final EditText petbread = view.findViewById(R.id.petbread);
        final EditText petgender = view.findViewById(R.id.petgender);
        final EditText petcolour = view.findViewById(R.id.petcolour);
        final EditText petnote = view.findViewById(R.id.petnote);
        Button insertBtn =view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetRegisterClass registerpetClass = new PetRegisterClass();
                registerpetClass.setPetcategory(petcategory .getText().toString());
                registerpetClass.setPetname(petname.getText().toString());
                registerpetClass.setPetage(petage.getText().toString());
                registerpetClass.setPetbread(petbread.getText().toString());
                registerpetClass.setPetgender(petgender.getText().toString());
                registerpetClass.setPetcolour(petcolour.getText().toString());
                registerpetClass.setPetnote(petnote.getText().toString());
                Date date = new Date();
                registerpetClass.setCreated_at(""+date.getTime());
                databaseHelperClass.addpet(registerpetClass);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}