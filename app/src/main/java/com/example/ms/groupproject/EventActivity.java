package com.example.ms.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends Activity implements View.OnClickListener {

    Button buttonSearchEvents;
    EditText editTextSearchEventsDay;
    TextView textViewEventsType, textViewNameOfEvent, textViewEventTime, textViewEventAddress, textSearchEventType, textSearchEventName, textSearchEventTime, textSearchEventAddresss, textViewEventDate, textSearchEventDate, textViewEventDescription, textSearchDescription;
    String currentKey;
    Events currentEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        buttonSearchEvents = findViewById(R.id.buttonSearchEventsDay);
        editTextSearchEventsDay = findViewById(R.id.editTextSearchEventsDay);
        textSearchEventAddresss = findViewById(R.id.textSearchEventAddresss);
        textSearchEventName = findViewById(R.id.textSearchEventName);
        textSearchEventTime = findViewById(R.id.textSearchEventTime);
        textSearchEventType = findViewById(R.id.textSearchEventType);
        textViewEventAddress = findViewById(R.id.textViewEventAddress);
        textViewEventTime = findViewById(R.id.textViewEventTime);
        textViewNameOfEvent = findViewById(R.id.textViewNameOfEvent);
        textViewEventsType = findViewById(R.id.textViewEventsType);
        textViewEventDate = findViewById(R.id.textViewEventDate);
        textSearchEventDate = findViewById(R.id.textSearchEventDate);
        textViewEventDescription = findViewById(R.id.textViewEventDescription);
        textSearchDescription = findViewById(R.id.textSearchEventDescription);

        buttonSearchEvents.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Event");

        if (view == buttonSearchEvents) {
            final String createDay = editTextSearchEventsDay.getText().toString();
            myRef.orderByChild("Day").equalTo(createDay).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        myRef.orderByChild("Day").equalTo(createDay).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                Events findDay = dataSnapshot.getValue(Events.class);

                                textSearchEventType.setText(findDay.EventType);
                                textSearchEventName.setText(findDay.EventName);
                                textSearchEventTime.setText(findDay.EventTime);
                                textSearchEventDate.setText(findDay.EventDate);
                                textSearchDescription.setText(findDay.EventDescription);
                                textSearchEventAddresss.setText(findDay.EventAddress);
                                currentKey = dataSnapshot.getKey();
                                currentEvent = findDay;

                                // keep working from here
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {
                        textSearchEventType.setText("error");
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }
}
