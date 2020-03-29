package com.example.quiznos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private static final String KEY_INDEX = "index";

    private static final int REQUEST_CODE_CHEAT = 0;


    private boolean mIsCheater;

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mCheatButton;
   // private TextView mAnswerView;

    private TextView QuestionTextView;
    private Button mNextButton;

    private Question[] mQuestionBank = new Question[]
            {

                    new Question(R.string.question_australia, true),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_asia, true),
                    new Question(R.string.question_oceans, true)


            };

    private int mCurrentIndex = 0;
    private int messageResId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
//
//                Toast.makeText(MainActivity.this,R.string.correct_value,
//                        Toast.LENGTH_LONG).show();

            }
        });


        mCheatButton=findViewById(R.id.cheat_button_view);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(MainActivity.this, CheatActivity.class);
//                startActivity(intent);
            boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswers();
            Intent intent=CheatActivity.newIntent(MainActivity.this,answerIsTrue);

                startActivityForResult(intent, REQUEST_CODE_CHEAT);


            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);

//                Toast.makeText(MainActivity.this,R.string.incorrect_value,
//                        Toast.LENGTH_LONG).show();

            }
        });


        QuestionTextView = (TextView) findViewById(R.id.question_text_view);
//        int question=mQuestionBank[mCurrentIndex].getTextResId();
//        QuestionTextView.setText(question);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
//                int question=mQuestionBank[mCurrentIndex].getTextResId();
//                QuestionTextView.setText(question);
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater=false;
                updateQuestion();


            }
        });

        updateQuestion;


    }


    private void updateQuestion() {

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        QuestionTextView.setText(question);

    }

    private void checkAnswer(boolean userPressedAnswer) {
        boolean isAnswer = mQuestionBank[mCurrentIndex].isAnswers();

        if (mIsCheater) {
            Toast.makeText(MainActivity.this,R.string.judgment_toast,Toast.LENGTH_SHORT).show();
        } else {

            if (userPressedAnswer == isAnswer)
                Toast.makeText(MainActivity.this, R.string.correct_value, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, R.string.incorrect_value, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);

    }




    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) { return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) { if (data == null) {
            return; }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        } }
}








