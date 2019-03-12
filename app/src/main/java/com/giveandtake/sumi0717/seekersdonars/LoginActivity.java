package com.giveandtake.sumi0717.seekersdonars;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    Button signIn,signUp;
    EditText email,password;
    String Email,Password;
    int flag=0;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);


        signIn=(Button)findViewById(R.id.signin);
        signUp=(Button)findViewById(R.id.signup);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email=email.getText().toString();
                Password=password.getText().toString();
                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if(allentered(Email,Password))
                    {
                        final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
                        pd.setMessage("Verifying your details");pd.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                pd.dismiss();
                            }
                        }, 3000); // 3000 milliseconds delay



                        Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/Donors/");
                        mref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                    if (dsp.child("email").getValue(String.class).equals(Email) && dsp.child("Password").getValue(String.class).equals(Password))

                                    {
                                        flag=1;

                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                                        clearall();
                                        Intent i=new Intent(getApplicationContext(),DonarActivity.class);
                                        Bundle b=new Bundle();
                                        b.putString("email",Email);


                                        i.putExtras(b);
                                        startActivity(i);

                                    }


                                }
                                if(flag==0)
                                {
                                    Toast.makeText(getApplicationContext(), "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }

                }








            }
        });









signUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);
    }
});

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public  boolean allentered(String e,String p)
    {

        if(e.isEmpty())
        {
            email.requestFocus();
            email.setError("Please enter Email");
            return false;
        }
        if(p.isEmpty())

        {
            password.requestFocus();
            password.setError("Please enter password");
            return false;
        }
        if (!e.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Incorrect Email Address",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void clearall()
    {
        email.setText("");
        password.setText("");
    }
}
