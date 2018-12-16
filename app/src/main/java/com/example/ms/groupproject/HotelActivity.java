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

    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Hotel");
        //myRef.setValue("");

        if (v == buttonViewHotel) {
            String HotelDay = editTextHotelDay.getText().toString();
//CAN SOMEONE CHECK THIS PART?
            myRef.orderByChild("Day").equalTo(HotelDay).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String findkey = dataSnapshot.getKey();
                    Hotels findhotel = dataSnapshot.getValue(Hotels.class);

                    textViewHotelName.setText(" " + findhotel.HotelName);
                    textViewHotelDate.setText(" " + findhotel.HotelAddress);
                    textViewHotelLink.setText(" " + findhotel.h);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown_menu, menu);
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
                startActivity(intentEvent);
                return true;
            case R.id.menuitem_transportation:
                Intent intentTransportation = new Intent(HotelActivity.this, TransportationActivity.class);
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
