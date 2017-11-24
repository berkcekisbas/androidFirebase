package com.berkcekisbas.firebase.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.PipedOutputStream;

public class MainActivity extends AppCompatActivity {

    Switch led1Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Integer[] LEDStatus = new Integer[1];

        led1Switch = (Switch) findViewById(R.id.switch2);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LEDStatus");


        led1Switch.setChecked(true);
        led1Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    myRef.setValue(0);
                } else {
                    myRef.setValue(1);
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Integer value = dataSnapshot.getValue(Integer.class);
                LEDStatus[0] = value;

                if(LEDStatus[0] == 1)
                {
                    led1Switch.setChecked(false);
                } else {
                    led1Switch.setChecked(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }

        });

    }
}
