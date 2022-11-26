package com.example.problem_submit;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

public class Homeactivity extends AppCompatActivity {
    Button insertdatadb,viewdata,updatedata,deletedata;
    EditText location,date,time,problem;
    DBHelper2 DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        location = findViewById(R.id.location);
        problem = findViewById(R.id.problem);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);


        insertdatadb=findViewById(R.id.insertdata);
        viewdata=findViewById(R.id.viewdata);
        updatedata = findViewById(R.id.update);
        deletedata = findViewById(R.id.deletedata);


        DB = new DBHelper2(this);


        insertdatadb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String entrylocation = location.getText().toString();
                String entryproblem = problem.getText().toString();
                String entrydate = date.getText().toString();
                String entrytime = time.getText().toString();




                Boolean insertdatatodb = DB.insertusersData(entrylocation,entryproblem,entrydate,entrytime);

                if(insertdatatodb==true){
                    Toast.makeText(Homeactivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Homeactivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });


        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Homeactivity.this,"No entery exists",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("location : "+res.getString(0)+"\n");
                    buffer.append("problem : "+res.getString(1)+"\n");
                    buffer.append("date : "+res.getString(2)+"\n");
                    buffer.append("time : "+res.getString(3)+"\n"+"***********************************"+"\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Homeactivity.this);
                builder.setCancelable(true);
                builder.setTitle("user Entries");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });

        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String entrylocation = location.getText().toString();
                String entryproblem = problem.getText().toString();
                String entrydate = date.getText().toString();
                String entrytime = time.getText().toString();

                Boolean checkupdatedata = DB.updateusersData(entrylocation,entryproblem,entrydate,entrytime);
                
                if(checkupdatedata==true){
                    Toast.makeText(Homeactivity.this, "Entry updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Homeactivity.this, "entry not updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String entrylocation = location.getText().toString();
                String entryproblem = problem.getText().toString();
                String entrydate = date.getText().toString();
                String entrytime = time.getText().toString();


                Boolean checkdeletedata = DB.deleteuserData(entrylocation,entryproblem,entrydate,entrytime);

                if(checkdeletedata==true){
                    Toast.makeText(Homeactivity.this, "Problem Solved and deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Homeactivity.this, "problem not solved not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}






