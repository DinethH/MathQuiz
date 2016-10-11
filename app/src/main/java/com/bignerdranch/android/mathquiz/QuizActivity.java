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

public class QuizActivity extends Activity implements OnClickListener {

    private Button addButton;
    private Button subButton;
    private Button mulButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //get references
        addButton = (Button)findViewById(R.id.addButton);
        subButton = (Button)findViewById(R.id.subButton);
        mulButton = (Button)findViewById(R.id.mulButton);

        //listen for the click
        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//respond to clicks
        if(view.getId()==R.id.addButton){
            //play button
            Intent addIntent = new Intent(this, PlayQuiz.class);
            this.startActivity(addIntent);
        }
        else if(view.getId()==R.id.subButton){
            //how to play button
            Intent subIntent = new Intent(this, SubQuiz.class);
            this.startActivity(subIntent);
        }
        else if(view.getId()==R.id.mulButton){
            //high scores
            Intent mulIntent = new Intent(this, MulQuiz.class);
            this.startActivity(mulIntent);
        }






    }




}



