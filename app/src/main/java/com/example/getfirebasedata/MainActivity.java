package com.example.getfirebasedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    String userid = "1";

    ArrayList<getdatamodel> list = new ArrayList<>();
    adapter dataadapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");


        EditText name =findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        Button button = findViewById(R.id.button);
        dataadapter =new adapter(list,this);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String name = snapshot.getValue().toString();
                TextView showdata =findViewById(R.id.textshowdata);
                showdata.setText(name);

//                for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                {
//                    getdatamodel data = dataSnapshot.getValue(getdatamodel.class);
////                    list.add(data);
//                    showdata.setText(data);
//
//
//                }
//                dataadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty())
                {
                    adddata add = new adddata(name.getText().toString(),email.getText().toString());
                    myRef.child(userid).setValue(add).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            userid = userid +1;
                            displayAlertbox();

                        }
                    });

                }
            }
        });

    }

    public void displayAlertbox()
    {
        new AlertDialog.Builder(this).setMessage("Using for Debt Freedom").setTitle("Thank You ")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                            }
                        })
                .show();

    }
}

class  getdatamodel{
    String sname, semail;

    getdatamodel()
    {

    }

    public getdatamodel(String sname, String semail) {
        this.sname = sname;
        this.semail = semail;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }
}

class adddata
{
    String name,Email;
    adddata()
    {

    }

    public adddata(java.lang.String name, java.lang.String string) {
        this.name = name;
        this.Email = string;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getString() {
        return Email;
    }

    public void setString(java.lang.String string) {
        Email = string;
    }
}