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

import com.firebase.client.Firebase;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {


    Button submit;
    EditText pass,cpass;
    String Pass,Cpass;
    EditText name,email,no,street,city,country;

    String donorName,Email,donorNumber,donorCity,donorStreet,donorCountry;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    int flag=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);


        submit=(Button)findViewById(R.id.signup);
        pass=(EditText)findViewById(R.id.password);
        cpass=(EditText)findViewById(R.id.cpsw);

        name=(EditText)findViewById(R.id.donarname);
        email=(EditText)findViewById(R.id.email);
        no=(EditText)findViewById(R.id.donarno);

        street=(EditText)findViewById(R.id.donorstreet) ;
        city=(EditText)findViewById(R.id.donorcity);
        country=(EditText)findViewById(R.id.donorcountry);

        flag=0;


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pass=pass.getText().toString();
                Cpass=cpass.getText().toString();

                donorName=name.getText().toString();
                Email=email.getText().toString();
                donorNumber=no.getText().toString();

                donorCity=city.getText().toString();
                donorStreet=street.getText().toString();
                donorCountry=country.getText().toString();

                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }

                else{
                    final ProgressDialog pd=new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("Adding your details");pd.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                        }
                    }, 3000); // 3000 milliseconds delay


                    if(allentered(donorName,Email,donorNumber,donorCity,donorStreet,Pass,Cpass))
                    {



                        if(Pass.equals(Cpass))
                        {




                            String otp_big= UUID.randomUUID().toString();
                            String unid=otp_big.substring(0, 6);

                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/Donors/");
                            Firebase mainEle=firebase.child(unid);
                            Firebase firebasechild=mainEle.child("name");
                            firebasechild.setValue(donorName);
                            Firebase firebasechild1=mainEle.child("phno");
                            firebasechild1.setValue(donorNumber);
                            Firebase firebasechild2=mainEle.child("street");
                            firebasechild2.setValue(donorStreet);
                            Firebase firebasechild3=mainEle.child("city");
                            firebasechild3.setValue(donorCity);
                            Firebase firebasechild4=mainEle.child("email");
                            firebasechild4.setValue(Email);
                            Firebase firebasechild5=mainEle.child("Password");
                            firebasechild5.setValue(Pass);
                            Firebase firebasechild6=mainEle.child("country");
                            firebasechild6.setValue(donorCountry);

                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);

                        }

                        else
                        {
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }//this
                }





            }
        });





    }
    public boolean allentered(String n,final String e,String num,String incity,String str,String p,String c)
    {
//      flag=0;
//        Firebase mref = new Firebase("https://seekersdonars.firebaseio.com/Donors/");
//        mref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot dsp:dataSnapshot.getChildren())
//                {
//                    if(dsp.child("email").getValue(String.class).equals(e))
//                    {
//                        Toast.makeText(getApplicationContext(), "You are already registered!", Toast.LENGTH_SHORT).show();
//                       flag=1;
//                       break;
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        if(flag==1)
//        {
//            return false;
//        }

        if(n.isEmpty())
        {
            name.requestFocus();
            name.setError("Please enter this detail");
            return false;
        }
        if(e.isEmpty())
        {
            email.requestFocus();
            email.setError("Please enter this detail");
            return false;
        }
        if(num.isEmpty())
        {
            no.requestFocus();
            no.setError("Please enter this detail");
            return false;
        }

        if(incity.isEmpty())
        {
            city.requestFocus();
            city.setError("Please enter this detail");
            return false;
        }
        if(str.isEmpty())
        {
            street.requestFocus();
            street.setError("Please enter this detail");
            return false;
        }
        if(p.isEmpty())
        {
            pass.requestFocus();
            pass.setError("Please enter this detail");
            return false;
        }
        if(c.isEmpty())
        {
            cpass.requestFocus();
            cpass.setError("Please enter this detail");
            return false;
        }

        if (!e.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Incorrect Email Address",Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
