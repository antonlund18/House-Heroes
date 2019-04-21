package com.example.househeroes;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolderOne> {
    private static final String TAG = "RecyclerViewAdapter";

    private Hero hero = Hero.getInstance();
    private QuestLog qlog = hero.getQuestLog();

    private CountDownTimer timer;

    private ArrayList<String> questImages;
    private ArrayList<String> questNames;
    private ArrayList<String> questDesc;
    private ArrayList<String> questxp;
    private ArrayList<String> questGold;

    private Context mContext;

    public MissionAdapter(Context mContext, ArrayList<String> questNames, ArrayList<String> questDesc, ArrayList<String> questxp, ArrayList<String> questGold) {
        //this.questImages = questImages;
        this.questNames = questNames;
        this.questDesc = questDesc;
        this.questxp = questxp;
        this.questGold = questGold;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.missionlist, parent, false);
        ViewHolderOne holder = new ViewHolderOne(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderOne holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        //Glide.with(mContext)
         //       .asBitmap()
         //       .load(questImages.get(position))
           //     .into(holder.image);

        holder.quest_title.setText(questNames.get(position));
        holder.quest_desc.setText(questDesc.get(position));
        holder.quest_xp.setText(questxp.get(position));
        holder.quest_gold.setText(questGold.get(position));
        holder.countdown.setTextColor(Color.GREEN);

        holder.test123.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!qlog.getQuestLog().get(position).isAvail()) {
                    Toast.makeText(mContext, questNames.get(position) + " is not ready yet", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(mContext, questNames.get(position) + " completed", Toast.LENGTH_SHORT).show();
                holder.countdown.setTextColor(Color.BLACK);

                qlog.getQuestLog().get(position).complete();
                qlog.getQuestLog().get(position).setAvail(false);

                timer = new CountDownTimer(qlog.getQuestLog().get(position).getCooldownms(), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String counter =  millisUntilFinished / 1000 / 60 / 60 + "H " + millisUntilFinished / 1000 / 60 % 60 + "M";

                        if(millisUntilFinished / 1000 / 60 / 60 <= 0) {
                            counter = millisUntilFinished / 1000 / 60 % 60 + "M " + millisUntilFinished / 1000 % 60 + "S";
                            if(millisUntilFinished / 1000 / 60 % 10 <= 0) {
                                counter = millisUntilFinished / 1000 / 60 % 60 + "M 0" + millisUntilFinished / 1000 % 60 + "S";
                            }
                        }
                        holder.countdown.setText(counter);
                    }

                    @Override
                    public void onFinish() {
                        qlog.getQuestLog().get(position).setAvail(true);
                        holder.countdown.setText(String.valueOf("Available"));
                        holder.countdown.setTextColor(Color.GREEN);
                    }
                }.start();

            }
        });
    }

    @Override
    public int getItemCount() {
        return questNames.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        //ImageView image;
        TextView quest_title;
        TextView quest_desc;
        TextView quest_xp;
        TextView quest_gold;
        TextView countdown;

        RelativeLayout test123;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.image);
            quest_title = itemView.findViewById(R.id.mission_title);
            quest_desc = itemView.findViewById(R.id.mission_description);
            quest_xp = itemView.findViewById(R.id.questxp);
            quest_gold = itemView.findViewById(R.id.questgold);
            countdown = itemView.findViewById(R.id.countdown);
            test123 = itemView.findViewById(R.id.test123);
        }
    }
}

