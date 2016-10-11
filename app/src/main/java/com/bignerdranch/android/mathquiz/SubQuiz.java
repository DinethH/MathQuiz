package com.bignerdranch.android.mathquiz;

import android.app.Activity;
import java.util.Random;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Asvin on 10/1/2016.
 */
public class SubQuiz extends Activity implements OnClickListener{

    //elements
    private int level = 0, answer = 0, operator = 0, operand1 = 0, operand2 = 0;

    //op constants
    private final int SUBTRACT_OPERATOR = 0;


    //operators
    private String[] operators = {"-"};

    //operator range
    private int[][] levelMin = {
            {1, 5, 10}};

    private int[][] levelMax = {
            {10, 20, 30}};


    //random number gen
    private Random random;
    //ui
    private TextView question1, question2, operand, answerTxt, scoreTxt, questionTxt;
    private ImageView response;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playquiz);


        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        operand = (TextView) findViewById(R.id.operand);
        answerTxt = (TextView) findViewById(R.id.answer);
        response = (ImageView) findViewById(R.id.response);
        scoreTxt = (TextView) findViewById(R.id.score);
        questionTxt = (TextView) findViewById(R.id.questionno);

        //hides the tick cross initially
        response.setVisibility(View.INVISIBLE);

        //buttons
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        enterBtn = (Button) findViewById(R.id.enter);

        //listen for click
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        enterBtn.setOnClickListener(this);


        if(savedInstanceState!=null){
            //restore state
            level=savedInstanceState.getInt("level");
            int exScore = savedInstanceState.getInt("score");
            scoreTxt.setText("Score: "+exScore);
            int exQuestion = savedInstanceState.getInt("questionno");
            questionTxt.setText("Q: "+exQuestion);
        }
        else{
            Bundle extras = getIntent().getExtras();
            if(extras !=null)
            {
                int passedLevel = extras.getInt("level", -1);
                if(passedLevel>=0) level = passedLevel;
            }
        }

        //random gen for question
        random = new Random();
        chooseQuestion();
    }

    @Override
    public void onClick(View view)
    {
//button clicked
        if(view.getId()==R.id.enter){
            //enter button
            String answerContent = answerTxt.getText().toString();
            if(!answerContent.endsWith("?"))
            {
                //we have an answer
                int enteredAnswer = Integer.parseInt(answerContent.substring(0));
                int exScore = getScore();
                int exQuestion = getQuestion();
                //correct answer
                if(enteredAnswer==answer){
                    //correct
                    scoreTxt.setText("Score: "+(exScore+1));
                    response.setImageResource(R.drawable.tick);
                    response.setVisibility(View.VISIBLE);
                }
                else{
                    //incorrect
                    scoreTxt.setText("Score: "+(exScore));
                    response.setImageResource(R.drawable.cross);
                    response.setVisibility(View.VISIBLE);
                }
                questionTxt.setText("Q: "+(exQuestion+1));
                chooseQuestion();
            }
        }
        else {
            //number button
            response.setVisibility(View.INVISIBLE);
            int enteredNum = Integer.parseInt(view.getTag().toString());
            if(answerTxt.getText().toString().endsWith("?"))
                answerTxt.setText(""+enteredNum);
            else
                answerTxt.append(""+enteredNum);
        }
    }

    private int getScore(){
        String scoreStr = scoreTxt.getText().toString();
        return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ")+1));
    }

    private int getQuestion(){
        String questionStr = questionTxt.getText().toString();
        return Integer.parseInt(questionStr.substring(questionStr.lastIndexOf(" ")+1));
    }

    private void chooseQuestion(){
//get a question

        answerTxt.setText("?");
        operator = SUBTRACT_OPERATOR;
        operand1 = getOperand();
        operand2 = getOperand();

        //operand 1 is > than operand 2
        if(operator==SUBTRACT_OPERATOR){
            //no negative answers
            while(operand2>operand1){
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }
        //calculate answer
        switch(operator)
        {

            case SUBTRACT_OPERATOR:
                answer = operand1-operand2;
                break;

            default:
                break;
        }

        //show Q
        question1.setText(operand1+" ");
        question2.setText(operand2+" ");
        operand.setText(operators[operator]+" ");
    }


    private int getOperand(){
//return operand number
        return random.nextInt(levelMax[operator][level] - levelMin[operator][level] + 1)
                + levelMin[operator][level];
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
//save state
        int exScore = getScore();
        int exQuestion = getQuestion();
        savedInstanceState.putInt("score", exScore);
        savedInstanceState.putInt("level", level);
        savedInstanceState.putInt("questionno", exQuestion);
        super.onSaveInstanceState(savedInstanceState);
    }



}
