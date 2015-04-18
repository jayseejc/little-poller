package com.jayseeofficial.littlepoller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Poll;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

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

    @OnItemLongClick(R.id.lv_polls)
    boolean onItemLongClick(int position) {
        final Poll poll = adapter.getItem(position);
        new MaterialDialog.Builder(this)
                .title("Delete poll " + poll.getTitle() + "?")
                .positiveText("Delete")
                .negativeText("Cancel")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        deletePoll(poll);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
        return true;
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

    private void deletePoll(Poll poll) {
        // Show an empty adapter temporarily, to avoid the headache of asynchronous edits to the
        // list's contents throwing exceptions.
        lvPolls.setAdapter(new EmptyAdapter());
        Program.pollManager.deletePoll(poll.getId());
        adapter = new PollAdapter(this);
        lvPolls.setAdapter(adapter);
    }

    /**
     * Empty list adapter to enable when updating the polls list
     */
    private class EmptyAdapter extends ArrayAdapter<Void> {
        public EmptyAdapter() {
            super(PollListActivity.this, android.R.layout.simple_list_item_1);
        }
    }

}
