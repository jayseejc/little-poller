package com.jayseeofficial.littlepoller.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PollResultDetailsActivity extends ActionBarActivity {

    public static final String POLL_DATA = "poll data";

    @InjectView(R.id.txt_Title)
    TextView txtTitle;
    @InjectView(R.id.layout_answers)
    LinearLayout layoutAnswers;

    Poll poll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_result_details);
        ButterKnife.inject(this);
        Serializable s = getIntent().getExtras().getSerializable(POLL_DATA);
        if (s == null || !(s instanceof Poll)) {
            throw new IllegalArgumentException("Must provide a valid poll");
        }
        poll = (Poll) s;
        txtTitle.setText(poll.getTitle() + " by " + poll.getCreator());
        showAnswers();
    }

    private void showAnswers() {
        for (Answer answer : poll.getAnswers()) {
            TextView text = new TextView(this);
            text.setText("● " + answer.getText() + " - " + answer.getCount());
            layoutAnswers.addView(text);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poll_result_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
