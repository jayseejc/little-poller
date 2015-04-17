package com.jayseeofficial.littlepoller.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jon on 17/04/15.
 */
public class Poll implements Serializable{

    private static final Random random = new Random();

    private int id;
    private String title;
    private String creator;
    private String question;
    private ArrayList<Answer> answers = new ArrayList<>();

    public Poll() {
        // Odds are pretty low of a collision. Maybe add detection later.
        this.id = random.nextInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

}
