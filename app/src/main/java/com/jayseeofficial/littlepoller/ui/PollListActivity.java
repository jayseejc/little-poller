package com.jayseeofficial.littlepoller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jayseeofficial.littlepoller.BuildConfig;
import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Poll;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class PollListActivity extends ActionBarActivity {

    private static final int EDIT_REQUEST_CODE = 4562;

    private int debugItemId = -1;

    @InjectView(R.id.lv_polls)
    ListView lvPolls;
    PollAdapter adapter;

    @OnClick(R.id.btn_add_poll)
    void onAddButtonClick() {
        editPoll(null);
    }

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
                .title(poll.getTitle())
                .content("What would you like to do?")
                .positiveText("Delete")
                .neutralText("Edit")
                .negativeText("Cancel")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        deletePoll(poll);
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        editPoll(poll);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
        return true;
    }

    void editPoll(Poll poll) {
        Intent i = new Intent(this, EditPollActivity.class);
        i.putExtra(EditPollActivity.POLL_DATA, poll);
        startActivityForResult(i, EDIT_REQUEST_CODE);
    }

    void deletePoll(Poll poll) {
        // Show an empty adapter temporarily, to avoid the headache of asynchronous edits to the
        // list's contents throwing exceptions.
        lvPolls.setAdapter(new EmptyAdapter());
        Program.pollManager.deletePoll(poll.getId());
        adapter = new PollAdapter(this);
        lvPolls.setAdapter(adapter);
    }

    void refreshPolls() {
        // Ugly solution. Gotta figure out something better.
        lvPolls.setAdapter(new EmptyAdapter());
        adapter = new PollAdapter(this);
        lvPolls.setAdapter(adapter);
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
    protected void onStart() {
        super.onStart();
        refreshPolls();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poll_list, menu);
        if (BuildConfig.DEBUG) {
            MenuItem debugItem = menu.add("Debug");
            debugItemId = debugItem.getItemId();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_view_results) {
            showResultsActivity();
            return true;
        } else if (id == debugItemId) {
            startActivity(new Intent(this, DebugActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void showResultsActivity() {
        startActivity(new Intent(this, PollResultListActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EDIT_REQUEST_CODE:
                refreshPolls();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
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
