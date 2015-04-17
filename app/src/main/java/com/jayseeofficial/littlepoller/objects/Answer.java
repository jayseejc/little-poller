package com.jayseeofficial.littlepoller.objects;

import java.io.Serializable;

/**
 * Created by jon on 17/04/15.
 */
public class Answer implements Serializable{
    private String text;
    private int count;

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
}
