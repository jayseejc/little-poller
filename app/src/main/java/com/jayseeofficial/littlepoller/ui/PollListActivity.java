package com.jayseeofficial.littlepoller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Poll;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class PollListActivity extends ActionBarActivity {

    @InjectView(R.id.lv_polls)
    ListView lvPolls;
    PollAdapter adapter;

    @OnItemClick(R.id.lv_polls)
    void onItemClick(int position) {
        Poll poll = adapter.getItem(position);
        Intent i = new Intent(this, AnswerPollActivity.class);
        i.putExtra(AnswerPollActivity.POLL_DATA, poll);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_list);
        ButterKnife.inject(this);
        adapter = new PollAdapter(this);
        lvPolls.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poll_list, menu);
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
