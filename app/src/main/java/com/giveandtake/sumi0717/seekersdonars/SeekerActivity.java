package com.giveandtake.sumi0717.seekersdonars;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeekerActivity extends AppCompatActivity {

    String category,citygot ,itemtoget;
    Button search;
    EditText city;

    Spinner seekerspin;
    ListView mylistview;


    String bookN,bookA,bookS;
    String toyN,toyC;
    String availableforthreeitems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker);
        Firebase.setAndroidContext(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        search=(Button)findViewById(R.id.search);
    //    wewant=(EditText)findViewById(R.id.itemwewant);
        seekerspin=(Spinner)findViewById(R.id.seekerspinner);
        mylistview=(ListView)findViewById(R.id.mylistView);
        city=(EditText)findViewById(R.id.seekercity);
   //     wewant.setVisibility(View.INVISIBLE);
        mylistview.setVisibility(View.INVISIBLE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                category =seekerspin.getSelectedItem().toString();
                citygot=city.getText().toString();
                final List<String> alldonors=new ArrayList<String>();
                final List<String> allbooksofDonors=new ArrayList<String>();
                final List<String> alltoysofDonors=new ArrayList<String>();
                final List<String> allitemavailavblecount=new ArrayList<String>();

                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    final ProgressDialog pd=new ProgressDialog(SeekerActivity.this);
                    pd.setMessage("Searching");pd.show();

                    final List<String> mylist=new ArrayList<String>();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                        }
                    }, 3000); // 3000 milliseconds delay


                    Firebase mref=null;
                    if(!citygot.isEmpty())
                    {
                        if(!category.equals("Choose from below list")) {
                            switch (category) {
                                case "Color Pencils and paints":
                                    mref = new Firebase("https://seekersdonars.firebaseio.com/DonorColorPaints/");
                                    break;
                                case "Note Books":
                                    mref = new Firebase("https://seekersdonars.firebaseio.com/DonorNoteBooks/");
                                    break;
                                case "Pencil Eraser Pens":
                                    mref = new Firebase("https://seekersdonars.firebaseio.com/DonorStationary/");
                                    break;
                                case "Text Books":
                                    mref = new Firebase("https://seekersdonars.firebaseio.com/DonorTextBooks/");
                                    break;
                                case "Toys":
                                    mref = new Firebase("https://seekersdonars.firebaseio.com/DonorToys/");


                            }
                            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        String g = dsp.child("emailDonor").getValue(String.class);


                                        if (dsp.child("available").getValue(String.class).equals("yes")) {
                                            alldonors.add(g);

                                            allitemavailavblecount.add("yes");
                                            if (category.equalsIgnoreCase("Toys")) {
                                                toyC = dsp.child("toyColor").getValue(String.class);
                                                toyN = dsp.child("toyName").getValue(String.class);

                                                String a = "Toy Name: " + toyN + "\nToy Color: " + toyC;
                                                alltoysofDonors.add(a);

                                            }
                                            if (category.equalsIgnoreCase("Text Books")) {
                                                bookN = dsp.child("bookName").getValue(String.class);
                                                bookA = dsp.child("bookAuthor").getValue(String.class);
                                                bookS = dsp.child("bookStd").getValue(String.class);

                                                String a = "Book Name: " + bookN + "\nBook Author: " + bookA + "\nBook Std: " + bookS;
                                                allbooksofDonors.add(a);
                                            }
                                        }


                                    }

                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

                            Firebase mref1 = new Firebase("https://seekersdonars.firebaseio.com/Donors/");
                            mref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                int i = 0;

                                //                    int j=0;
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        for (String a : alldonors) {
                                            //     if(allitemavailavblecount.get(j).equals("yes"))
                                            //{
                                            if (dsp.child("email").getValue(String.class).equalsIgnoreCase(a) && dsp.child("city").getValue(String.class).equalsIgnoreCase(citygot) && allitemavailavblecount.get(i).equalsIgnoreCase("yes")) {
                                                if (category.equalsIgnoreCase("Text Books")) {

                                                    mylist.add(allbooksofDonors.get(i) + "\nDonor Name: " + dsp.child("name").getValue(String.class) + "\nDonor Email: " + dsp.child("email").getValue(String.class) + "\nPhone No: " + dsp.child("phno").getValue(String.class) + "\nStreet: " + dsp.child("street").getValue(String.class) + "\nCity: " + dsp.child("city").getValue(String.class) + "\nCountry: " +dsp.child("country").getValue(String.class) +"\n");


                                                }
                                                if (category.equalsIgnoreCase("Toys")) {
                                                    mylist.add(alltoysofDonors.get(i) + "\nDonor Name: " + dsp.child("name").getValue(String.class) + "\nDonor Email: " + dsp.child("email").getValue(String.class) + "\nPhone No: " + dsp.child("phno").getValue(String.class) + "\nStreet: " + dsp.child("street").getValue(String.class) + "\nCity: " + dsp.child("city").getValue(String.class) + "\nCountry: "+dsp.child("country").getValue(String.class)+"\n");

                                                } else if (category.equalsIgnoreCase("Color Pencils and paints") || category.equalsIgnoreCase("Note Books") || category.equalsIgnoreCase("Pencil Eraser Pens")) {

                                                    mylist.add("Donor Name: " + dsp.child("name").getValue(String.class) + "\nDonor Email: " + dsp.child("email").getValue(String.class) + "\nPhone No: " + dsp.child("phno").getValue(String.class) + "\nStreet: " + dsp.child("street").getValue(String.class) + "\nCity: " + dsp.child("city").getValue(String.class) + "\nCountry: "+dsp.child("country").getValue(String.class)+"\n");


                                                }
                                                i++;

                                            }
                                            //}
                                            //   j++;


                                        }

                                    }

                                    String values[] = new String[mylist.size()];
                                    values = mylist.toArray(values);

                                    if (!mylist.isEmpty())

                                    {


                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, values);

                                        mylistview.setAdapter(adapter);
                                        mylistview.setVisibility(View.VISIBLE);

                                    } else {
                                        mylistview.setVisibility(View.INVISIBLE);

                                        Toast.makeText(getApplicationContext(), "No matches found for this item in your city", Toast.LENGTH_SHORT).show();
                                    }


                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

                        }//if cat
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Please select the correct item from the list", Toast.LENGTH_SHORT).show();
                        }

                    }//if

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please enter your city", Toast.LENGTH_SHORT).show();
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
}
