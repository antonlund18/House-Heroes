package com.example.househeroes;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.ViewHolderOne> {
    private static final String TAG = "RecyclerViewAdapter";

    private Hero hero;
    private AdventureList alist;

    private ArrayList<String> questImages;
    private ArrayList<String> aname;
    private ArrayList<String> aboss;
    private ArrayList<String> bosshp;
    private ArrayList<String> advxp;
    private ArrayList<String> advgold;
    private ArrayList<String> reqlvl;
    private CountDownTimer timer;

    private Context mContext;

    public AdventureAdapter(Context mContext, Hero hero, ArrayList<String> aname, ArrayList<String> aboss, ArrayList<String> bosshp, ArrayList<String> advxp, ArrayList<String> advgold, ArrayList<String> reqlvl) {
        this.hero = hero;
        alist = hero.getAlist();
        //this.questImages = questImages;
        this.aname = aname;
        this.aboss = aboss;
        this.bosshp = bosshp;
        this.advxp = advxp;
        this.advgold = advgold;
        this.mContext = mContext;
        this.reqlvl = reqlvl;
    }

    @NonNull
    @Override
    public ViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adventurelist, parent, false);
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

        holder.adventure_title.setText(aname.get(position));
        holder.adventure_boss.setText("Boss: " + aboss.get(position));
        holder.adventure_bosshp.setText("HP: " + bosshp.get(position) + " / " + alist.getAlist().get(position).getMaxhp());
        holder.adventure_xp.setText(advxp.get(position));
        holder.adventure_gold.setText(advgold.get(position));
        if (hero.getLvl() < alist.getAlist().get(position).getReqlvl()) {
            holder.adventure_reqlvl.setText("You need to be level " + reqlvl.get(position) + " or higher");
        }

        holder.layout_adventure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(alist.getAlist().get(position).isAvail() && hero.getLvl() > alist.getAlist().get(position).getReqlvl()) {
                    alist.getAlist().get(position).setAvail(false);

                    timer = new CountDownTimer(1000 * 60 * alist.getAlist().get(position).getMaxhp() / hero.getDmg(), 1000 * 60) {

                        int hp = alist.getAlist().get(position).getMaxhp();
                        String counter;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            hp = hp - hero.getDmg();
                            if(hp < 0) { hp = 0; }
                            counter = "HP: " + hp + " / " + bosshp.get(position);
                            holder.adventure_bosshp.setText(counter);
                        }

                        @Override
                        public void onFinish() {
                            alist.getAlist().get(position).setAvail(true);
                            alist.getAlist().get(position).completeAdv(hero);
                        }


                    }.start();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return aname.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        //ImageView image;
        TextView adventure_title;
        TextView adventure_boss;
        TextView adventure_bosshp;
        TextView adventure_xp;
        TextView adventure_gold;
        TextView adventure_reqlvl;

        RelativeLayout layout_adventure;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.image);
            adventure_title = itemView.findViewById(R.id.adventure_title);
            adventure_boss = itemView.findViewById(R.id.adventure_boss);
            adventure_bosshp = itemView.findViewById(R.id.bosshp);
            adventure_xp = itemView.findViewById(R.id.questxp);
            adventure_gold = itemView.findViewById(R.id.questgold);
            adventure_reqlvl = itemView.findViewById(R.id.adventure_reqlvl);
            layout_adventure = itemView.findViewById(R.id.adventurelist);

        }
    }
}

