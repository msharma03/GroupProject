package com.example.ms.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends Activity implements View.OnClickListener {

    TextView textViewTripTitle, textViewTripDate;
    Button buttonDay1Transportation, buttonDay1Hotel, buttonDay2Event, buttonDay2Hotel, buttonDay3Transportation, buttonDay3Event, buttonDay3Hotel, buttonDay4Hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonDay1Transportation = findViewById(R.id.buttonDay1Transportation);
        buttonDay1Hotel = findViewById(R.id.buttonDay1Hotel);
        buttonDay2Event = findViewById(R.id.buttonDay2Event);
        buttonDay2Hotel = findViewById(R.id.buttonDay2Hotel);
        buttonDay3Transportation = findViewById(R.id.buttonDay3Transportation);
        buttonDay3Event = findViewById(R.id.buttonDay3Event);
        buttonDay3Hotel = findViewById(R.id.buttonDay3Hotel);
        buttonDay4Hotel = findViewById(R.id.buttonDay4Hotel);

        buttonDay1Transportation.setOnClickListener(this);
        buttonDay1Hotel.setOnClickListener(this);
        buttonDay2Event.setOnClickListener(this);
        buttonDay2Hotel.setOnClickListener(this);
        buttonDay3Transportation.setOnClickListener(this);
        buttonDay3Event.setOnClickListener(this);
        buttonDay3Hotel.setOnClickListener(this);
        buttonDay4Hotel.setOnClickListener(this);

    }
    // menu created below

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
                return true;
            case R.id.menuitem_hotel:
                Intent intentHotel = new Intent(HomeActivity.this, HotelActivity.class);
                startActivity(intentHotel);
                return true;
            case R.id.menuitem_event:
                Intent intentEvent = new Intent(HomeActivity.this, EventActivity.class);
                startActivity(intentEvent);
                return true;
            case R.id.menuitem_transportation:
                Intent intentTransportation = new Intent(HomeActivity.this, TransportationActivity.class);
                startActivity(intentTransportation);
                return true;
            case R.id.menuitem_logout:
                Intent intentLogout = new Intent(HomeActivity.this, MainActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intentLogout);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View view) {

        // Switch screen to activities
        // "||" means "or" in if clauses

        if (view == buttonDay1Transportation) {
            Intent intentTransportation = new Intent (this, TransportationActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("TransportKey", "1");
            intentTransportation.putExtras(bundle);

            startActivity(intentTransportation);

        } else if (view == buttonDay1Hotel){

            Intent intentHotel = new Intent (this, HotelActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("HotelKey", "1");
            intentHotel.putExtras(bundle);

            startActivity(intentHotel);

        } else if (view == buttonDay2Event){

            Intent intentEvent = new Intent (this, EventActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("EventKey", "2");
            intentEvent.putExtras(bundle);

            startActivity(intentEvent);

        }else if (view == buttonDay2Hotel){

            Intent intentHotel = new Intent (this, HotelActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("HotelKey", "2");
            intentHotel.putExtras(bundle);

            startActivity(intentHotel);

        } else if (view == buttonDay3Transportation){
            Intent intentTransportation = new Intent (this, TransportationActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("TransportKey", "3");
            intentTransportation.putExtras(bundle);

            startActivity(intentTransportation);
        }
        else if (view == buttonDay3Event){
            Intent intentEvent = new Intent(this, EventActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("EventKey", "3");
            intentEvent.putExtras(bundle);

            startActivity(intentEvent);

        } else if (view == buttonDay3Hotel){
            Intent intentHotel = new Intent(this, HotelActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("HotelKey", "3");
            intentHotel.putExtras(bundle);

            startActivity(intentHotel);

        } else if (view == buttonDay4Hotel){
            Intent intentHotel = new Intent(this, HotelActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("HotelKey", "4");
            intentHotel.putExtras(bundle);

            startActivity(intentHotel);
        }
    }
}
