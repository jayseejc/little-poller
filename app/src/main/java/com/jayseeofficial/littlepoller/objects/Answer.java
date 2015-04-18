package com.jayseeofficial.littlepoller.objects;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by jon on 17/04/15.
 */
public class Answer implements Serializable {

    private static final Random random = new Random();

    private String text;
    private int count;
    private int id;

    public Answer() {
        // as with poll, should look into checking for conflicts eventually
        id = random.nextInt();
    }

    public Answer(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void addToCount() {
        count++;
    }

    public int getId() {
        return id;
    }
}
