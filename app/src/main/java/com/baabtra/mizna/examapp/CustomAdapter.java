package com.baabtra.mizna.examapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by MIZNA on 15-06-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<Countrydata> list;

    public CustomAdapter(HomePage homePage, List<Countrydata> list) {
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public List<Countrydata> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        System.out.println("Message:During Adapter:On create");
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.RankView.setText(list.get(position).getRank());
        holder.NameView.setText(list.get(position).getCountry());
        holder.PopView.setText(list.get(position).getPopulation());
        System.out.println("Message:During Adapter:On Bind");
        Glide.with(context).load(list.get(position).getImageurl()).into(holder.ImageView);
        System.out.println("Message:During Adapter:On Bind after Glide");

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView ImageView;
            public TextView RankView;
            public TextView PopView;
            public TextView NameView;

            public ViewHolder(View itemView) {
                super(itemView);
                ImageView = (ImageView) itemView.findViewById(R.id.flagView);
                RankView = (TextView) itemView.findViewById(R.id.rankView);
                PopView = (TextView) itemView.findViewById(R.id.popView);
                NameView = (TextView) itemView.findViewById(R.id.nameView);
            }
        }

}
