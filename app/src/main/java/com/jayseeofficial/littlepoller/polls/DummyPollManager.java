package com.jayseeofficial.littlepoller.polls;

import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 17/04/15.
 */
public class DummyPollManager implements PollManager {

    int numPolls = 20;
    int numAnswers = 5;

    @Override
    public List<Poll> getAllPolls() {
        List<Poll> polls = new ArrayList<>(numPolls);
        for (int i = 0; i < numPolls; i++) {
            Poll poll = new Poll();
            poll.setTitle("Sample poll " + (i + 1));
            poll.setCreator("John Doe");
            for (int j = 0; j < numAnswers; j++) {
                Answer answer = new Answer();
                answer.setText("Sample answer " + (j + 1));
                poll.addAnswer(answer);
            }
            polls.add(poll);
        }
        return polls;
    }

    @Override
    public Poll getPoll(int id) {
        Poll poll = new Poll();
        poll.setTitle("Sample poll");
        poll.setCreator("John Doe");
        for (int j = 0; j < numAnswers; j++) {
            Answer answer = new Answer();
            answer.setText("Sample answer " + (j + 1));
            poll.addAnswer(answer);
        }
        return poll;
    }

    @Override
    public void savePoll(Poll poll) {
        // Do nothing. Dummy method.
    }
}
