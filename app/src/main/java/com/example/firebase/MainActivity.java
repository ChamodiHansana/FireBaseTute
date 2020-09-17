package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextid, editTextname, editTextadress, editTextcntact;
    Button buttonsave, buttonshow, buttonupdate, buttondelete;
    DatabaseReference dbRef;
    Student std;

    private void clearControls() {
        editTextid.setText("");
        editTextname.setText("");
        editTextadress.setText("");
        editTextcntact.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextid = findViewById(R.id.editTextid);
        editTextname = findViewById(R.id.editTextname);
        editTextadress = findViewById(R.id.editTextadress);
        editTextcntact = findViewById(R.id.editTextcntact);

        buttonsave = findViewById(R.id.buttonsave);
        buttonshow = findViewById(R.id.buttonshow);
        buttonupdate = findViewById(R.id.buttonupdate);
        buttondelete = findViewById(R.id.buttondelete);

        std = new Student();


        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
                try {
                    if (TextUtils.isEmpty(editTextid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(editTextname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(editTextadress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
                    else {
                        std.setId(editTextid.getText().toString().trim());
                        std.setName(editTextname.getText().toString().trim());
                        std.setAddress(editTextadress.getText().toString().trim());
                        std.setContNo(Integer.parseInt(editTextcntact.getText().toString().trim()));

                        //dbRef.push().setValue(std);
                        dbRef.child("std1").setValue(std);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            editTextid.setText(dataSnapshot.child("id").getValue().toString());
                            editTextname.setText(dataSnapshot.child("name").getValue().toString());
                            editTextadress.setText(dataSnapshot.child("address").getValue().toString());
                            editTextcntact.setText(dataSnapshot.child("contNo").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Sourse to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });


        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("std1")) {
                            try {
                                std.setId(editTextid.getText().toString().trim());
                                std.setName(editTextname.getText().toString().trim());
                                std.setAddress(editTextadress.getText().toString().trim());
                                std.setContNo(Integer.parseInt(editTextcntact.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                                dbRef.setValue(std);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to update", Toast.LENGTH_SHORT).show();

                            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("std1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });


    }
}
