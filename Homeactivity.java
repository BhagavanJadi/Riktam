package com.example.problem_submit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Homeactivity extends AppCompatActivity {
    ImageView image;
    Button upload,insertdata,viewdata,updatedata;
    EditText loaction,date,time;

    DBHelper DB;
    DBhelper2 db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        image = findViewById(R.id.image);
        upload = findViewById(R.id.upload);
        loaction = findViewById(R.id.location);


        insertdata=findViewById(R.id.insertdata);
        viewdata=findViewById(R.id.viewdata);
        updatedata = findViewById(R.id.update);

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        DB = new DBHelper(this);


        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationentry = loaction.getText().toString();
                String dateentry =  date.getText().toString();
                String timeentry = time.getText().toString();

                Boolean insertdata = db.insertusersData(locationentry,dateentry,timeentry);

                if(insertdata==true){
                    Toast.makeText(Homeactivity.this,"new entery inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Homeactivity.this,"new entery not  inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Homeactivity.this,"No entery exists",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("location : "+res.getString(0)+"\n");
                    buffer.append("date : "+res.getString(1)+"\n");
                    buffer.append("time : "+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Homeactivity.this);
                builder.setCancelable(true);
                builder.setTitle("user Entries");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });



    }
}






