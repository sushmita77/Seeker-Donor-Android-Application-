package com.giveandtake.sumi0717.seekersdonars;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogForToys extends AppCompatActivity {

    EditText color,tname;
    Button savetoydetails;

    String toycolor,toyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_for_toys);


        getWindow().setLayout(697,910);


        color=(EditText)findViewById(R.id.toycolour);
        tname=(EditText)findViewById(R.id.toyname);
        savetoydetails=(Button)findViewById(R.id.toysave) ;

        savetoydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toycolor=color.getText().toString();
                toyname=tname.getText().toString();
                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(allentered(toycolor,toyname))
                    {
                        Intent intent =new Intent();
                        intent.putExtra("toyName", toyname);
                        intent.putExtra("toyColor", toycolor);

                        setResult(RESULT_OK, intent);
                        finish();
                    }


                }




            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private boolean allentered(String a,String b)
    {


        if(a.isEmpty())
        {
            color.requestFocus();
            color.setError("Please enter this detail");
            return false;
        }
        if(b.isEmpty()) {
            tname.requestFocus();
            tname.setError("Please enter this detail");
            return false;
        }
        return true;
    }
}
