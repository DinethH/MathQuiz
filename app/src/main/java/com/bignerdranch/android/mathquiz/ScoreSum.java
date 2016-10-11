package com.bignerdranch.android.mathquiz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Asvin on 10/10/2016.
 */

public class ScoreSum extends Activity implements OnClickListener {

    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_dialog);

        //get references
        okButton = (Button)findViewById(R.id.btnok);


        //listen for the click
        okButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//respond to clicks
        if(view.getId()==R.id.btnok){
            //ok button
            Intent addIntent = new Intent(this, QuizActivity.class);
            this.startActivity(addIntent);
        }


    }



}
