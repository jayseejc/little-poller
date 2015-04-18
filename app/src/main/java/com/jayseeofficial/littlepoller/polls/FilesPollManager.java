package com.jayseeofficial.littlepoller.polls;

import android.content.Context;

import com.google.gson.Gson;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 18/04/15.
 */
public class FilesPollManager implements PollManager {

    private Context context;
    private Gson gson = new Gson();
    private ArrayList<Poll> polls;

    public FilesPollManager(Context context) {
        this.context = context.getApplicationContext();
        try {
            loadPolls();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadPolls() throws FileNotFoundException {
        polls = new ArrayList<>();
        File[] allFiles = context.getFilesDir().listFiles();
        for (File file : allFiles) {
            if (file.getAbsolutePath().endsWith(".json")) {
                polls.add(gson.fromJson(new FileReader(file), Poll.class));
            }
        }

    }

    @Override
    public List<Poll> getAllPolls() {
        return polls;
    }

    @Override
    public Poll getPoll(int id) {
        for (Poll poll : polls) if (poll.getId() == id) return poll;
        return null;
    }

    @Override
    public void savePoll(Poll poll) {
        Writer writer=null;
        try {
            FileOutputStream out = context.openFileOutput(poll.getId() + ".json", Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(gson.toJson(poll));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer!=null){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    // God help us...
                    e.printStackTrace();
                }
            }
        }
        try {
            loadPolls();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePoll(int id) {
        context.deleteFile(id + ".json");
        try {
            loadPolls();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePoll(Poll poll) {
        // savePoll implementation overwrites if exists.
        savePoll(poll);
        try {
            loadPolls();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
