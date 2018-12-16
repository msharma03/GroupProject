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

public class HotelActivity extends Activity implements View.OnClickListener {

    Button buttonViewHotel;
    EditText editTextHotelDay;
    TextView textViewHotelName,textViewHotelAddress,textViewHotelLink,textViewHotelDate;
   // private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        //   mAuth = FirebaseAuth.getInstance();

        buttonViewHotel = findViewById(R.id.buttonViewHotel);
        editTextHotelDay = findViewById(R.id.editTextHotelDay);

        textViewHotelName = findViewById(R.id.textViewHotelName);
        textViewHotelAddress = findViewById(R.id.textViewHotelAddress);
        textViewHotelDate = findViewById(R.id.textViewHotelDate);
        textViewHotelLink = findViewById(R.id.textViewHotelLink);

        buttonViewHotel.setOnClickListener(this);

        //getting "Day" from homeactivity and pulling data from firebase oncreate
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Hotel");

        //Hotel Key is the value of Day from HomeActivity
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("HotelKey");

        Bundle bundleHotelMenu = getIntent().getExtras();
        String calledMenu = bundleHotelMenu.getString("HotelKey");

        //Same statement to pull date from firebase, just using "stuff" which is the Day from homeactivity

        if (calledMenu == "0") {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hotel);
            //   mAuth = FirebaseAuth.getInstance();

            buttonViewHotel = findViewById(R.id.buttonViewHotel);
            editTextHotelDay = findViewById(R.id.editTextHotelDay);

            textViewHotelName = findViewById(R.id.textViewHotelName);
            textViewHotelAddress = findViewById(R.id.textViewHotelAddress);
            textViewHotelDate = findViewById(R.id.textViewHotelDate);
            textViewHotelLink = findViewById(R.id.textViewHotelLink);

            buttonViewHotel.setOnClickListener(this);



        }

        else {
            myRef.orderByChild("Day").equalTo(stuff).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Hotels findhotel = dataSnapshot.getValue(Hotels.class);

                    textViewHotelName.setText(findhotel.Name);
                    textViewHotelDate.setText(findhotel.Date);
                    textViewHotelAddress.setText(findhotel.Address);
                    textViewHotelLink.setText(findhotel.Link);
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
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Hotel");

        if (v == buttonViewHotel) {
            final String HotelDay = editTextHotelDay.getText().toString();
//CAN SOMEONE CHECK THIS PART?
            myRef.orderByChild("Day").equalTo(HotelDay).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        myRef.orderByChild("Day").equalTo(HotelDay).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                Hotels findhotel = dataSnapshot.getValue(Hotels.class);

                                textViewHotelName.setText(findhotel.Name);
                                textViewHotelDate.setText(findhotel.Date);
                                textViewHotelAddress.setText(findhotel.Address);
                                textViewHotelLink.setText(findhotel.Link);
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
                        textViewHotelName.setText("Oops! There is no accommodation on that day");
                        textViewHotelDate.setText("N/A");
                        textViewHotelAddress.setText("N/A");
                        textViewHotelLink.setText("N/A");
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
        getMenuInflater().inflate(R.menu.dropdown_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuitem_home:
                Intent intentHome = new Intent(HotelActivity.this, HomeActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.menuitem_hotel:
                return true;
            case R.id.menuitem_event:
                Intent intentEvent = new Intent(HotelActivity.this, EventActivity.class);

                Bundle bundleEventMenu = new Bundle();
                bundleEventMenu.putString("EventKey", "0");
                intentEvent.putExtras(bundleEventMenu);

                startActivity(intentEvent);
                return true;
            case R.id.menuitem_transportation:
                Intent intentTransportation = new Intent(HotelActivity.this, TransportationActivity.class);

                Bundle bundleTransportMenu = new Bundle();
                bundleTransportMenu.putString("TransportKey", "0");
                intentTransportation.putExtras(bundleTransportMenu);

                startActivity(intentTransportation);
                return true;
            case R.id.menuitem_logout:
                Intent intentLogout = new Intent(HotelActivity.this, MainActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intentLogout);
                return true;
            default:
                return false;
        }
    }
}
