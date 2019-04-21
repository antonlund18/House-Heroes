package com.example.househeroes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolderOne> {
    private static final String TAG = "RecyclerViewAdapter";

    private Hero hero = Hero.getInstance();
    private Shop shop = hero.getShop();
    private Inventory inv = hero.getInv();

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mItemNames = new ArrayList<>();
    private ArrayList<String> mItemDamages = new ArrayList<>();
    private Context mContext;

    public InventoryAdapter(Context mContext, ArrayList<String> mImages, ArrayList<String> mItemNames, ArrayList<String> mItemDamages) {
        this.mImages = mImages;
        this.mItemNames = mItemNames;
        this.mItemDamages = mItemDamages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_invitems, parent, false);
        ViewHolderOne holder = new ViewHolderOne(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOne holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.item_name.setText(mItemNames.get(position));
        holder.item_dmg.setText(mItemDamages.get(position));

        holder.test123.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                inv.getInventory().get(position).equipItem();
                Toast.makeText(mContext, mItemNames.get(position) + " equipped", Toast.LENGTH_SHORT).show();
                }
        });
    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        ImageView image;
        TextView item_name;
        TextView item_dmg;

        RelativeLayout test123;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            item_name = itemView.findViewById(R.id.item_name);
            item_dmg = itemView.findViewById(R.id.item_damage);
            test123 = itemView.findViewById(R.id.test123);
        }
    }
}

