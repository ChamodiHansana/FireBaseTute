package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editTextid,editTextname,editTextadress,editTextcntact;
    Button buttonsave,buttonshow,buttonupdate,buttondelete;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextid=findViewById(R.id.editTextid);
        editTextname=findViewById(R.id.editTextname);
        editTextadress=findViewById(R.id.editTextadress);
        editTextcntact=findViewById(R.id.editTextcntact);

        buttonsave=findViewById(R.id.buttonsave);
        buttonshow=findViewById(R.id.buttonshow);
        buttonupdate=findViewById(R.id.buttonupdate);
        buttondelete=findViewById(R.id.buttondelete);

        std = new Student();

        buttonsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Student");
                try{
                    if(TextUtils.isEmpty(editTextid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(editTextname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(editTextadress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
                    else{
                        std.setId(editTextid.getText().toString().trim());
                        std.setName(editTextname.getText().toString().trim());
                        std.setAddress(editTextadress.getText().toString().trim());
                        std.setContNo(Integer.parseInt(editTextid.getText().toString().trim()));

                        dbRef.push().setValue(std);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        clearControls();


                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void clearControls(){
        editTextid.setText("");
        editTextname.setText("");
        editTextadress.setText("");
        editTextcntact.setText("");
    }



}

