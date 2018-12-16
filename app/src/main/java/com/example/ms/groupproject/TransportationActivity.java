package com.example.ms.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TransportationActivity extends Activity implements View.OnClickListener {
    Button buttonSearch;
    TextView textViewTransportation;
    EditText editTextDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        buttonSearch = findViewById(R.id.buttonSearch);
        textViewTransportation = findViewById(R.id.textViewTransportation);
        editTextDay = findViewById(R.id.editTextDay);

        buttonSearch.setOnClickListener(this);

       // FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference myRef = database.getReference("transport");

        //String Day = "1";
        //String Type = "flight";
        //String Date = "05/16/2018";
        //String Time = "5:30PM";


        //transport newTransport = new transport (Day, Type, Date ,Time);
        //myRef.push().setValue(newTransport);

        //getting "Day" from homeactivity and pulling data from firebase oncreate
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("transport");

        //transport Key is the value of Day from HomeActivity
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("TransportKey");

        //Same statement to pull date from firebase, just using "stuff" which is the Day from homeactivity
        myRef.orderByChild("Day").equalTo(stuff).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                transport findTransport = dataSnapshot.getValue(transport.class);
                textViewTransportation.setText("You have a " + findTransport.Type + " on " + findTransport.Date + " at " + findTransport.Time);

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
                Intent intentHome = new Intent(TransportationActivity.this, HomeActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.menuitem_hotel:
                Intent intentHotel = new Intent(TransportationActivity.this, HotelActivity.class);
                startActivity(intentHotel);
                return true;
            case R.id.menuitem_event:
                Intent intentEvent = new Intent(TransportationActivity.this, EventActivity.class);
                startActivity(intentEvent);
                return true;
            case R.id.menuitem_transportation:
                return true;
            case R.id.menuitem_logout:
                Intent intentLogout = new Intent(TransportationActivity.this, MainActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intentLogout);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("transport");

    if (v == buttonSearch) {
        final String findDayString = editTextDay.getText().toString();
        myRef.orderByChild("Day").equalTo(findDayString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    myRef.orderByChild("Day").equalTo(findDayString).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            transport findTransport = dataSnapshot.getValue(transport.class);
                            textViewTransportation.setText("You have a " + findTransport.Type + " on " + findTransport.Date + " at " + findTransport.Time);

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
                    textViewTransportation.setText("There is no transportation on that day");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    }
}
