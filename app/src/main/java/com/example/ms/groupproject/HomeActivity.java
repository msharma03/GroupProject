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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onClick(View v) {

    }


        // menu created below

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

            @Override
            public boolean onOptionsItemSelected (MenuItem item){
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


}
