package com.giveandtake.sumi0717.seekersdonars;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMyItems extends AppCompatActivity {

    ListView mylistview;
    String emailMy;
    int posD;

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);

//        android.os.Process.killProcess(android.os.Process.myPid());
//        Intent a=new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(a);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);

    }

   // DialogInterface overalldialog;

//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if ((overalldialog != null) ) {
//            overalldialog.dismiss();
//            overalldialog = null;
//        }
//    }
//

//    @Override
//    protected void onPause() {
//        super.onPause();
//      finish();
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        finish();
//    }


//    @Override
//    public void onBackPressed() {
//        //    super.onBackPressed();
//
//
//        Intent a=new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(a);
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        this.finish();
//    }

//    public void onPause() {
//        super.onPause();
//        this.finish();
//    }
//    public void onStop() {
//        super.onStop();
//        finish();
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_items);
        Firebase.setAndroidContext(this);



        mylistview=(ListView)findViewById(R.id.mydonateditems);
        //mylistview.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        emailMy = extras.getString("email");
     //   Toast.makeText(getApplicationContext(), "got  "+emailMy, Toast.LENGTH_SHORT).show();



            letsdoit();





        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {

                posD=i;
                AlertDialog.Builder alt=new AlertDialog.Builder(ShowMyItems.this);
                alt.setMessage("Are you sure you want to mark them unavailable").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                String selectedFromList = (String) mylistview.getItemAtPosition(posD);

                                String lines[] = selectedFromList.split("\\r?\\n");


                                final String lineone=lines[0];
                                char got=lineone.charAt(0);
                                final String words_book_name[]=lineone.split(" ");




                                if(got=='B')
                                {
                                    final Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorTextBooks/");

                                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                                            {

                                                if(dsp.child("emailDonor").getValue(String.class).equals(emailMy) && dsp.child("bookName").getValue(String.class).equals(words_book_name[2]))
                                                {
                                                    try
                                                    {
                                                        dsp.getRef().child("available").setValue("no");
                                                        Toast.makeText(getApplicationContext(),"Your item has marked unavailable, Thanks for donating!!",Toast.LENGTH_LONG).show();
                                                        break;
                                                        //      adapterView.getChildAt(posD).setBackgroundResource(R.color.colorPrimary);


                                                    }
                                                    catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                                }
                                if(got=='T')
                                {
                                    final Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorToys/");

                                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                                            {

                                                if(dsp.child("emailDonor").getValue(String.class).equals(emailMy) && dsp.child("toyName").getValue(String.class).equals(words_book_name[2]))
                                                {
                                                    try
                                                    {
                                                        dsp.getRef().child("available").setValue("no");
                                                        Toast.makeText(getApplicationContext(),"Your item has marked unavailable, Thanks for donating!!",Toast.LENGTH_LONG).show();
                                                        break;

                                                    }
                                                    catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                                }


                                if(got=='C')
                                {
                                    final Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorColorPaints/");

                                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                                            {

                                                if(dsp.child("emailDonor").getValue(String.class).equals(emailMy))
                                                {
                                                    try
                                                    {
                                                        dsp.getRef().child("available").setValue("no");
                                                        Toast.makeText(getApplicationContext(),"Your item has marked unavailable, Thanks for donating!!",Toast.LENGTH_LONG).show();
                                                        break;

                                                    }
                                                    catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                                }
                                if(got=='N')
                                {
                                    final Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorNoteBooks/");

                                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                                            {

                                                if(dsp.child("emailDonor").getValue(String.class).equals(emailMy))
                                                {
                                                    try
                                                    {

                                                        dsp.getRef().child("available").setValue("no");
                                                        Toast.makeText(getApplicationContext(),"Your item has marked unavailable, Thanks for donating!!",Toast.LENGTH_LONG).show();
                                                        break;


                                                    }
                                                    catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                                }
                                if(got=='S')
                                {
                                    final Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorStationary/");

                                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                                            {

                                                if(dsp.child("emailDonor").getValue(String.class).equals(emailMy))
                                                {
                                                    try
                                                    {

                                                        dsp.getRef().child("available").setValue("no");
                                                        Toast.makeText(getApplicationContext(),"Your item has marked unavailable, Thanks for donating!!",Toast.LENGTH_LONG).show();
                                                        break;


                                                    }
                                                    catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
                                }//this if

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alertDialog=alt.create();
                alertDialog.setTitle("Dialog Header");
                alertDialog.show();






            }
        });


    }


    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    public void letsdoit()
    {
        final List<String> mylist=new ArrayList<String>();

        Firebase mref0=new Firebase("https://seekersdonars.firebaseio.com/DonorTextBooks/");
        mref0.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren())
                {
                    if(dsp.child("emailDonor").getValue(String.class).equalsIgnoreCase(emailMy) && dsp.child("available").getValue(String.class).equals("yes"))
                    {

                        mylist.add("Book Name "+dsp.child("bookName").getValue(String.class)+"\n"+"Book Author "+dsp.child("bookAuthor").getValue(String.class)+"\n"+"Book Std "+dsp.child("bookStd").getValue(String.class)+"\n");
                    }
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase mreft=new Firebase("https://seekersdonars.firebaseio.com/DonorToys/");
        mreft.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren())
                {
                    if(dsp.child("emailDonor").getValue(String.class).equalsIgnoreCase(emailMy) && dsp.child("available").getValue(String.class).equals("yes"))
                    {
                        mylist.add("Toy Name: "+ dsp.child("toyName").getValue(String.class)+"\n"+"Toy Color: "+dsp.child("toyColor").getValue(String.class)+"\n");
                    }
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        Firebase mref=new Firebase("https://seekersdonars.firebaseio.com/DonorColorPaints/");
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren())
                {
                    if(dsp.child("emailDonor").getValue(String.class).equalsIgnoreCase(emailMy) && dsp.child("available").getValue(String.class).equals("yes"))
                    {
                          mylist.add("Color Pencils and Paints");
                    }
                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mref1=new Firebase("https://seekersdonars.firebaseio.com/DonorNoteBooks/");
        mref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren())
                {
                    if(dsp.child("emailDonor").getValue(String.class).equalsIgnoreCase(emailMy) && dsp.child("available").getValue(String.class).equals("yes"))
                    {
                        mylist.add("Notebooks");
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mref2=new Firebase("https://seekersdonars.firebaseio.com/DonorStationary/");
        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren())
                {
                    if(dsp.child("emailDonor").getValue(String.class).equalsIgnoreCase(emailMy) && dsp.child("available").getValue(String.class).equals("yes"))
                    {
                        mylist.add("Stationary: Pencils Erasers Pens");
                    }
                }
                String values[]=new String[mylist.size()];
                values=mylist.toArray(values);


                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,values);

                mylistview.setAdapter(adapter);
                mylistview.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

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
