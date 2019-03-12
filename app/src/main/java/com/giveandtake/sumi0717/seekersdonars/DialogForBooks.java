package com.giveandtake.sumi0717.seekersdonars;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogForBooks extends AppCompatActivity {

    EditText bname,bauthor,bclass;
    Button save;
    String bookname,bookauthor,bookstd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_for_books);

       // setContentView(R.layout.activity_dialog_for_books);
        getWindow().setLayout(775,910);

        bname=(EditText)findViewById(R.id.bookname);
        bauthor=(EditText)findViewById(R.id.bookauthor);
        bclass=(EditText)findViewById(R.id.std);
        save=(Button)findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             bookname=bname.getText().toString();
             bookauthor=bauthor.getText().toString();
             bookstd=bclass.getText().toString();


                boolean value= isNetworkAvailable();
                if(value==false)
                {
                    Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(allentered(bookname,bookauthor,bookstd))
                    {

                        Intent intent =new Intent();
                        intent.putExtra("bookName", bookname);
                        intent.putExtra("bookAuthor", bookauthor);
                        intent.putExtra("bookStd", bookstd);
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

    private boolean allentered(String a,String b,String c)
    {


        if(a.isEmpty())
        {
            bname.requestFocus();
            bname.setError("Please enter this detail");
            return false;
        }
        if(b.isEmpty())
        {
            bauthor.requestFocus();
            bclass.setError("Please enter this detail");
            return false;
        }
        if(c.isEmpty())
        {
            bclass.requestFocus();
            bclass.setError("Please enter this detail");
            return false;
        }
        return true;
    }
}
