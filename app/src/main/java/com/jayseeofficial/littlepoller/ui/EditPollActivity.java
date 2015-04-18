package com.jayseeofficial.littlepoller.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditPollActivity extends ActionBarActivity {

    public static final String POLL_DATA = "poll data";

    @InjectView(R.id.txt_Title)
    EditText txtTitle;
    @InjectView(R.id.txt_creator)
    EditText txtCreator;
    @InjectView(R.id.txt_answer)
    EditText txtAnswer;
    @InjectView(R.id.txt_answer_list)
    TextView txtAnswerList;

    private Poll poll;

    @OnClick(R.id.btn_add_answer)
    void addAnswer() {
        Answer answer = new Answer();
        answer.setText(txtAnswer.getText().toString());
        poll.addAnswer(answer);
        updateAnswerList();
    }

    private void updateAnswerList() {
        txtAnswerList.setText("Answers: \n");
        for (Answer answer : poll.getAnswers())
            txtAnswerList.append("- " + answer.getText() + "\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poll);
        ButterKnife.inject(this);
        Serializable s = getIntent().getExtras().getSerializable(POLL_DATA);
        if (s == null || !(s instanceof Poll)) {
            Log.d(getClass().getSimpleName(), "Did not receive valid poll data." + s);
            poll = new Poll();
        } else {
            poll = (Poll) s;
            txtTitle.setText(poll.getTitle());
            txtCreator.setText(poll.getCreator());
            updateAnswerList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_poll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_poll) {
            savePoll();
            Intent returnData = new Intent();
            returnData.putExtra("poll", poll);
            setResult(Activity.RESULT_OK, returnData);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void savePoll() {
        poll.setTitle(txtTitle.getText().toString());
        poll.setCreator(txtCreator.getText().toString());
        Program.pollManager.updatePoll(poll);
    }
}
