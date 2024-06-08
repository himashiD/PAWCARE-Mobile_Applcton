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

public class Caregiver_Make_Job extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist_care;
    TextView datalist_count_care;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caregiver_make_job);

        databaseHelperClass = new DatabaseHelperClass(Caregiver_Make_Job.this);
        Button cdelete = findViewById(R.id.care_delete_data);
        Button cinsert = findViewById(R.id.care_insert_data);
        Button cupdate = findViewById(R.id.care_update_data);
        Button cread = findViewById(R.id.care_refresh_data);
        datalist_care = findViewById(R.id.care_all_data_list);
        datalist_count_care = findViewById(R.id.care_data_list_count);

        cread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carejob_refreshData();
            }
        });

        cinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carejob_showInputDialog();
            }
        });

        cupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carejob_showUpdateIdDialog();
            }
        });

        cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carejob_showDeleteDialog();
            }
        });
    }

    private void carejob_refreshData() {
        datalist_count_care.setText("All Data Count : "+databaseHelperClass.getTotalcaregiverjob());
        List<CaregiverMakeJobClass> caregiverMakeJobClassesList = databaseHelperClass.getAllcaregiverjob();
        datalist_care.setText("");
        for (CaregiverMakeJobClass caregiverMakeJobClass : caregiverMakeJobClassesList)
            datalist_care.append("CID : " + caregiverMakeJobClass.getCid() + " | Caregiver ID : " + caregiverMakeJobClass.getCareid() + " | Caregiver Name : " + caregiverMakeJobClass.getCarename() + " | Contact No : " + caregiverMakeJobClass.getCarecontact() + " | Location : " + caregiverMakeJobClass.getCarelocation() + " | Payment : " + caregiverMakeJobClass.getCarepayment() + " | Experiance : " + caregiverMakeJobClass.getCareexperience() + "\n\n");
    }

    private void carejob_showDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Caregiver_Make_Job.this);
        View view = getLayoutInflater().inflate(R.layout.makejob_delete_dialog,null);
        al.setView(view);
        final EditText care_id_input = view.findViewById(R.id.care_id_input);
        Button carejob_delete_btn = view.findViewById(R.id.care_delete_btn);

        final AlertDialog alertDialog=al.show();
        carejob_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deletecaregiverjob(care_id_input.getText().toString());
                alertDialog.dismiss();
                carejob_refreshData();
            }
        });

    }


    public void carejob_showUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Caregiver_Make_Job.this);
        View view = getLayoutInflater().inflate(R.layout.makejob_update_id_dialog,null);
        al.setView(view);
        final EditText care_id_input;
        care_id_input = view.findViewById(R.id.care_id_input);
        Button carejob_carefetch_btn = view.findViewById(R.id.care_update_id_btn);
        final AlertDialog alertDialog=al.show();
        carejob_carefetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carejob_showDataDialog(care_id_input.getText().toString());
                alertDialog.dismiss();
                carejob_refreshData();
            }
        });

    }


    private void carejob_showDataDialog(final String cid) {
        CaregiverMakeJobClass caregiverMakeJobClass = databaseHelperClass.getcaregiverjob(Integer.parseInt(cid));
        AlertDialog.Builder al = new AlertDialog.Builder(Caregiver_Make_Job.this);
        View view = getLayoutInflater().inflate(R.layout.makejob_update_dialog,null);
        final EditText careid = view.findViewById(R.id.careid);
        final EditText carename = view.findViewById(R.id.carename);
        final EditText carecontact = view.findViewById(R.id.carecontact);
        final EditText carelocation = view.findViewById(R.id.carelocation);
        final EditText carepayment = view.findViewById(R.id.carepayment);
        final EditText careexperience = view.findViewById(R.id.careexperince);
        Button update_Btn =view.findViewById(R.id.care_update_btn);
        al.setView(view);

        careid.setText(caregiverMakeJobClass.getCareid());
        carename.setText(caregiverMakeJobClass.getCarename());
        carecontact.setText(caregiverMakeJobClass.getCarecontact());
        carelocation.setText(caregiverMakeJobClass.getCarelocation());
        carepayment.setText(caregiverMakeJobClass.getCarepayment());
        careexperience.setText(caregiverMakeJobClass.getCareexperience());

        final AlertDialog alertDialog=al.show();

        update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaregiverMakeJobClass caregiverMakeJobClass = new CaregiverMakeJobClass();
                caregiverMakeJobClass.setCareid(careid.getText().toString());
                caregiverMakeJobClass.setCid (cid);
                caregiverMakeJobClass.setCarename(carename.getText().toString());
                caregiverMakeJobClass.setCarecontact(carecontact.getText().toString());
                caregiverMakeJobClass.setCarelocation(carelocation.getText().toString());
                caregiverMakeJobClass.setCarepayment(carepayment.getText().toString());
                caregiverMakeJobClass.setCareexperience(careexperience.getText().toString());
                databaseHelperClass.updatecaregiverjob(caregiverMakeJobClass);
                alertDialog.dismiss();
                carejob_refreshData();
            }
        });
    }



    private void carejob_showInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Caregiver_Make_Job.this);
        View view = getLayoutInflater().inflate(R.layout.makejob_insert_dialog,null);
        final EditText careid = view.findViewById(R.id.careid);
        final EditText carename = view.findViewById(R.id.carename);
        final EditText carecontact = view.findViewById(R.id.carecontact);
        final EditText carelocation = view.findViewById(R.id.carelocation);
        final EditText carepayment = view.findViewById(R.id.carepayment);
        final EditText careexperience = view.findViewById(R.id.careexperience);
        Button carejob_insertBtn =view.findViewById(R.id.care_insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        carejob_insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaregiverMakeJobClass caregiverMakeJobClass = new CaregiverMakeJobClass();
                caregiverMakeJobClass.setCareid(careid.getText().toString());
                caregiverMakeJobClass.setCarename(carename.getText().toString());
                caregiverMakeJobClass.setCarecontact(carecontact.getText().toString());
                caregiverMakeJobClass.setCarelocation(carelocation.getText().toString());
                caregiverMakeJobClass.setCarepayment(carepayment.getText().toString());
                caregiverMakeJobClass.setCareexperience(careexperience.getText().toString());
                Date date = new Date();
                caregiverMakeJobClass.setCreated_at_care(""+date.getTime());
                databaseHelperClass.addcaregiverjob(caregiverMakeJobClass);
                alertDialog.dismiss();
                carejob_refreshData();
            }
        });
    }
}