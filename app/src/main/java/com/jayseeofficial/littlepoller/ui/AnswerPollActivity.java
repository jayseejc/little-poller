package com.jayseeofficial.littlepoller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AnswerPollActivity extends ActionBarActivity {

    public static final String POLL_DATA = "poll data";

    @InjectView(R.id.txt_Title)
    TextView txtTitle;
    @InjectView(R.id.layout_answers)
    RadioGroup layoutAnswers;

    private Poll mPoll;
    private int answerId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_poll);
        ButterKnife.inject(this);
        Serializable s = getIntent().getExtras().getSerializable(POLL_DATA);
        // Double check that we got a proper poll object
        if (s == null || !(s instanceof Poll)) {
            throw new IllegalArgumentException("Must provide poll to present to user.");
        }
        mPoll = (Poll) s;
        txtTitle.setText(mPoll.getTitle());
        generateAnswerViews();
    }

    private void generateAnswerViews() {
        for (Answer answer : mPoll.getAnswers()) {
            RadioButton btn = new RadioButton(this);
            btn.setText(answer.getText());
            btn.setTag(answer.getId());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answerId = (Integer) view.getTag();
                }
            });
            layoutAnswers.addView(btn);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_poll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
            submitPoll();
        }

        return super.onOptionsItemSelected(item);
    }

    private void submitPoll() {
        // TODO make sure answer id will never be -1
        if (answerId != -1) {
            for (Answer answer : mPoll.getAnswers())
                if (answer.getId() == answerId) answer.addToCount();
            Program.pollManager.updatePoll(mPoll);
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "You must select an answer to submit.", Toast.LENGTH_LONG).show();
        }
    }
}
