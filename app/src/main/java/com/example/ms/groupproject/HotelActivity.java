package com.example.ms.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HotelActivity extends Activity implements View.OnClickListener {

    Button buttonViewHotel;
    EditText editTextHotelDay;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        mAuth = FirebaseAuth.getInstance();

        buttonViewHotel = findViewById(R.id.buttonViewHotel);
        editTextHotelDay = findViewById(R.id.editTextHotelDay);

        buttonViewHotel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        //myRef.setValue("");

        if (v == buttonViewHotel)
        {
            String getDay = editTextHotelDay.getText().toString();

    }
}
