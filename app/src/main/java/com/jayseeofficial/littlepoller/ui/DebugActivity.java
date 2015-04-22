package com.jayseeofficial.littlepoller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class DebugActivity extends ActionBarActivity {

    @InjectView(R.id.txt_debug_dump)
    TextView txtDump;

    @OnClick(R.id.btn_debug_show_all_polls)
    void showAllPolls() {
        List<Poll> polls = Program.pollManager.getAllPolls();
        for (Poll poll : polls) txtDump.append(poll.getTitle() + " by " + poll.getCreator() + "\n");
    }

    @OnClick(R.id.btn_debug_show_Poll_activity)
    void showPollActivity() {
        startActivity(new Intent(this, PollListActivity.class));
    }

    @OnClick(R.id.btn_debug_add_poll_activity)
    void addPoll() {
        startActivity(new Intent(this, EditPollActivity.class));
    }

    @OnClick(R.id.btn_debug_add_sample_polls)
    void addSamplePolls() {
        int numPolls = 30;
        int numAnswers = 5;
        for (int i = 1; i <= numPolls; i++) {
            Poll poll = new Poll();
            poll.setTitle("Sample poll " + i);
            poll.setCreator("Tester");
            for (int j = 1; j <= numAnswers; j++) {
                Answer answer = new Answer();
                answer.setText("Sample answer " + j);
                poll.addAnswer(answer);
            }
            Program.pollManager.savePoll(poll);
            txtDump.append("Added poll \"" + poll.getTitle() + "\"\n");
        }
    }

    @OnClick(R.id.btn_debug_clear_polls)
    void clearPolls() {
        List<Poll> polls = Program.pollManager.getAllPolls();
        for (Poll poll : polls) {
            Program.pollManager.deletePoll(poll.getId());
            txtDump.append("Removed poll \"" + poll.getTitle() + "\"\n");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        ButterKnife.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debug, menu);
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
