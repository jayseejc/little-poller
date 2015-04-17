package com.jayseeofficial.littlepoller.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jayseeofficial.littlepoller.Program;
import com.jayseeofficial.littlepoller.R;
import com.jayseeofficial.littlepoller.objects.Poll;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jon on 17/04/15.
 */
public class PollAdapter extends ArrayAdapter<Poll> {

    private static final int VIEW_ITEM_LAYOUT = R.layout.item_poll;

    private Context context;
    private ArrayList<Poll> polls = new ArrayList<>();

    private LayoutInflater inflater;

    public PollAdapter(Context context) {
        this(context, Program.pollManager.getAllPolls());
    }

    public PollAdapter(Context context, List<Poll> polls) {
        super(context, VIEW_ITEM_LAYOUT);
        this.context = context;
        this.polls.addAll(polls);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return polls.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) holder = (ViewHolder) view.getTag();
        else {
            view = inflater.inflate(VIEW_ITEM_LAYOUT, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Poll poll = polls.get(position);

        holder.txtTitle.setText(poll.getTitle());
        holder.txtCreator.setText(poll.getCreator());

        return view;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_poll.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.txt_Title)
        TextView txtTitle;
        @InjectView(R.id.txt_creator)
        TextView txtCreator;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
