package com.example.ms.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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


        //getting "Day" from homeactivity and pulling data from firebase oncreate
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Event");

        //Event Key is the value of Day from HomeActivity
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("EventKey");

        Bundle bundleEventMenu = getIntent().getExtras();
        String calledMenu = bundleEventMenu.getString("HotelKey");

        if (calledMenu == "0") {

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


        } else {


            //Same statement to pull date from firebase, just using "stuff" which is the Day from homeactivity
            myRef.orderByChild("Day").equalTo(stuff).addChildEventListener(new ChildEventListener() {
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


        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuitem_home:
                Intent intentHome = new Intent(EventActivity.this, HomeActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.menuitem_hotel:
                Intent intentHotel = new Intent(EventActivity.this, HotelActivity.class);

                //setting bundle to a dummy value of 0 when called from dropdown menu
                Bundle bundleHotelMenu = new Bundle();
                bundleHotelMenu.putString("HotelKey", "0");
                intentHotel.putExtras(bundleHotelMenu);


                startActivity(intentHotel);
                return true;
            case R.id.menuitem_event:
                return true;
            case R.id.menuitem_transportation:
                Intent intentTransportation = new Intent(EventActivity.this, TransportationActivity.class);

                Bundle bundleTransportMenu = new Bundle();
                bundleTransportMenu.putString("TransportKey", "0");
                intentTransportation.putExtras(bundleTransportMenu);

                startActivity(intentTransportation);
                return true;
            case R.id.menuitem_logout:
                Intent intentLogout = new Intent(EventActivity.this, MainActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intentLogout);
                return true;
            default:
                return false;
        }
    }

}
