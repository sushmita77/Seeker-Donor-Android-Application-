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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.UUID;

public class DonarActivity extends AppCompatActivity {


    String bname,bauthor,bstd,tname,tcolor;
    String category;
    Spinner donar_items;
    Button submit,home,myitems;
    String emailMy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        Firebase.setAndroidContext(this);


        donar_items=(Spinner) findViewById(R.id.donaritems);

        submit=(Button)findViewById(R.id.donarsubmit);
        home=(Button)findViewById(R.id.home) ;
        myitems=(Button)findViewById(R.id.viewitems) ;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
//
        emailMy = extras.getString("email");
      //  Toast.makeText(getApplicationContext(), "got  "+emailMy, Toast.LENGTH_SHORT).show();




        donar_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Text Books"))
                {
                    // do your stuff
                 //   Toast.makeText(getApplicationContext(),"hotay",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),DialogForBooks.class);
                    startActivityForResult(intent,1);
                //    donar_items.setSelection(0);
                }
                if(selectedItem.equals("Toys"))
                {


                    Intent intent=new Intent(getApplicationContext(),DialogForToys.class);
                    startActivityForResult(intent,1);
                  //  donar_items.setSelection(0);



                }
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }
                else {
                    category=donar_items.getSelectedItem().toString();


                    final ProgressDialog pd=new ProgressDialog(DonarActivity.this);
                    pd.setMessage("Submitting your details");pd.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                        }
                    }, 1000); // 3000 milliseconds delay



                    String otp_big= UUID.randomUUID().toString();
                    String unid=otp_big.substring(0, 6);

                    allentered(category);
                    {


                        if(category.equals("Text Books"))
                        {
                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/DonorTextBooks/");
                            Firebase mainEle=firebase.child(unid);

                            Firebase firebasechild6=mainEle.child("bookName");
                            firebasechild6.setValue(bname);
                            Firebase firebasechild7=mainEle.child("bookAuthor");
                            firebasechild7.setValue(bauthor);
                            Firebase firebasechild8=mainEle.child("bookStd");
                            firebasechild8.setValue(bstd);
                            Firebase firebasechild9=mainEle.child("emailDonor");
                            firebasechild9.setValue(emailMy);
                            Firebase firebasechild10=mainEle.child("available");
                            firebasechild10.setValue("yes");
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();

                            // Firebase firebasechild7=mainEle.child("category");
                            //  firebasechild7.setValue(category);

                        }

                        if(category.equals("Toys"))
                        {
                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/DonorToys/");
                            Firebase mainEle=firebase.child(unid);

                            Firebase firebasechild6=mainEle.child("toyName");
                            firebasechild6.setValue(tname);
                            Firebase firebasechild7=mainEle.child("toyColor");
                            firebasechild7.setValue(tcolor);
                            Firebase firebasechild9=mainEle.child("emailDonor");
                            firebasechild9.setValue(emailMy);
                            Firebase firebasechild10=mainEle.child("available");
                            firebasechild10.setValue("yes");
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                        }
                        if(category.equals("Note Books"))
                        {
                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/DonorNoteBooks/");
                            Firebase mainEle=firebase.child(unid);

                            Firebase firebasechild9=mainEle.child("emailDonor");
                            firebasechild9.setValue(emailMy);
                            Firebase firebasechild10=mainEle.child("available");
                            firebasechild10.setValue("yes");
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                        }
                        if(category.equals("Pencil Eraser Pens"))
                        {
                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/DonorStationary/");
                            Firebase mainEle=firebase.child(unid);


                            Firebase firebasechild9=mainEle.child("emailDonor");
                            firebasechild9.setValue(emailMy);
                            Firebase firebasechild10=mainEle.child("available");
                            firebasechild10.setValue("yes");
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();

                        }
                        if(category.equals("Color Pencils and paints"))
                        {
                            Firebase firebase=new Firebase("https://seekersdonars.firebaseio.com/DonorColorPaints/");
                            Firebase mainEle=firebase.child(unid);

                            Firebase firebasechild9=mainEle.child("emailDonor");
                            firebasechild9.setValue(emailMy);
                            Firebase firebasechild10=mainEle.child("available");
                            firebasechild10.setValue("yes");
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                        }
                        donar_items.setSelection(0);

                    }//this


                }




            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        myitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(getApplicationContext(),ShowMyItems.class);
                Bundle b=new Bundle();
                b.putString("email",emailMy);


                i.putExtras(b);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                 bname=data.getStringExtra("bookName");
                bauthor=data.getStringExtra("bookAuthor");
                bstd=data.getStringExtra("bookStd");
                tname=data.getStringExtra("toyName");
                tcolor=data.getStringExtra("toyColor");
               // Toast.makeText(getApplicationContext(),"getting "+bname,Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean allentered(String cat)
    {


        if(cat.equals("Choose from below list"))
        {

            Toast.makeText(getApplicationContext(), "Please select item type to donate", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
