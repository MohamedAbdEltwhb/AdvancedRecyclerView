package com.example.mm.advancedrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class RecycleDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<RecycleBen> data;

    public RecycleDataAdapter(Context mContext, ArrayList<RecycleBen> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if(viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_first_item, parent, false);
            return new SimpleText(itemView);

        } else if(viewType==2) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_fsecond_item, parent, false);
            return new SimpleImage(itemView);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_third_item, parent, false);
            return new SimpleImageWithText(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecycleBen bean = data.get(position);

        if(holder.getItemViewType()==1)
        {

            ((SimpleText)holder).first_data_tv.setText(bean.getName());

        }
        else if(holder.getItemViewType()==2)
        {
            final SimpleImage simple_holder = (SimpleImage)holder;



            simple_holder.second_pb.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(bean.getImage_url())
                    .fitCenter()
                    .crossFade()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            if (e instanceof UnknownHostException)
                                simple_holder.second_pb.setVisibility(View.VISIBLE);

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            simple_holder.second_pb.setVisibility(View.GONE);
                            simple_holder.second_data_iv.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(simple_holder.second_data_iv);;

        }
        else {

            final SimpleImageWithText third_holder = (SimpleImageWithText)holder;

            third_holder.third_data_tv.setText(bean.getName());

            third_holder.third_pb.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(bean.getImage_url())
                    .fitCenter()
                    .crossFade()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            if (e instanceof UnknownHostException)
                                third_holder.third_pb.setVisibility(View.VISIBLE);

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            third_holder.third_pb.setVisibility(View.GONE);
                            third_holder.third_iv.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(third_holder.third_iv);;



        }

    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(data.get(position).getType_row());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class SimpleText extends RecyclerView.ViewHolder {
        TextView first_data_tv;

        public SimpleText(View v) {
            super(v);
            this.first_data_tv = (TextView) v.findViewById(R.id.first_data_tv);
        }
    }

    public   class SimpleImage extends  RecyclerView.ViewHolder {
        ImageView second_data_iv;
        ProgressBar second_pb;

        public SimpleImage(View v) {
            super(v);
            this.second_data_iv = (ImageView) v.findViewById(R.id.second_data_iv);
            this.second_pb = (ProgressBar)v.findViewById(R.id.second_pb);
        }
    }

    public  class SimpleImageWithText extends  RecyclerView.ViewHolder {
        TextView third_data_tv;
        ImageView third_iv;
        ProgressBar third_pb;

        public SimpleImageWithText(View v) {
            super(v);
            this.third_data_tv = (TextView) v.findViewById(R.id.third_data_tv);
            this.third_iv = (ImageView) v.findViewById(R.id.third_iv);
            this.third_pb = (ProgressBar)v.findViewById(R.id.third_pb);
        }
    }
}
