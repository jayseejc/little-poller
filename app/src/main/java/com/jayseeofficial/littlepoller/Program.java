package com.jayseeofficial.littlepoller;

import android.app.Application;

import com.jayseeofficial.littlepoller.polls.FilesPollManager;
import com.jayseeofficial.littlepoller.polls.PollManager;

/**
 * Created by jon on 17/04/15.
 */
public class Program extends Application {

    public static PollManager pollManager;

    public void onCreate() {
        pollManager = new FilesPollManager(this);
    }

}
