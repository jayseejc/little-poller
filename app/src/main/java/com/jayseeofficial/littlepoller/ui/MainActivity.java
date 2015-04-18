package com.jayseeofficial.littlepoller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Simple hack of a class to make starting the true main activity a little more easy to make dynamic
 */
public class MainActivity extends ActionBarActivity {

    private static final int ACTIVITY_RESULT_CODE = 8621;

    private Class startActivity = PollListActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivityForResult(new Intent(this, startActivity), ACTIVITY_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }
}
