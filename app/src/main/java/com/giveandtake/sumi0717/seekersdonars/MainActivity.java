package com.giveandtake.sumi0717.seekersdonars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity {

    Button seeker,donar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

         seeker=(Button)findViewById(R.id.seekers);
         donar=(Button)findViewById(R.id.donars);


        seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SeekerActivity.class);
                startActivity(i);
            }
        });

        donar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });


    }
}
