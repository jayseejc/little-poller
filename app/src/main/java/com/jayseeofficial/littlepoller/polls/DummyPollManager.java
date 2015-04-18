package com.jayseeofficial.littlepoller.polls;

import com.jayseeofficial.littlepoller.objects.Answer;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 17/04/15.
 */
public class DummyPollManager implements PollManager {

    private static final int numPolls = 20;
    private static final int numAnswers = 5;

    private List<Poll> polls = new ArrayList<>();

    public DummyPollManager() {
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
        polls.add(poll);
    }

    @Override
    public void deletePoll(int id) {
        polls.remove(getPoll(id));
    }

    @Override
    public void updatePoll(Poll newPoll) {
        for (int i = 0; i < polls.size(); i++) {
            if (polls.get(i).getId() == newPoll.getId()) {
                polls.remove(i);
                polls.add(i, newPoll);
                return;
            }
        }
        savePoll(newPoll);

    }
}
