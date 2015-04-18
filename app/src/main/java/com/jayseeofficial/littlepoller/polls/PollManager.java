package com.jayseeofficial.littlepoller.polls;

import com.jayseeofficial.littlepoller.objects.Poll;

import java.util.List;

/**
 * Created by jon on 17/04/15.
 */
public interface PollManager {

    public abstract List<Poll> getAllPolls();

    public abstract Poll getPoll(int id);

    public abstract void savePoll(Poll poll);

    public abstract void deletePoll(int id);

    public abstract void updatePoll(Poll poll);
}
