package com.johndevjames.myandroidapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class TutorialsAdapter extends Adapter<TutorialsAdapter.MyViewHolder> {
    private List<Tutorial> tutorialList;

    public class MyViewHolder extends ViewHolder {
        public TextView Added_by;
        public TextView Address;
        public TextView Area;
        public TextView District;
        public TextView Email;
        public TextView Landmark;
        public TextView MobNum;
        public TextView PhoneNum;
        public TextView Pin;
        public TextView Shop_name;
        public TextView Tin;
        public TextView owner_name;
        public TextView place;

        public MyViewHolder(View view) {
            super(view);
            this.Shop_name = (TextView) view.findViewById(R.id.textView10);
            this.owner_name = (TextView) view.findViewById(R.id.textView24);
            this.place = (TextView) view.findViewById(R.id.textView44);
            this.Address = (TextView) view.findViewById(R.id.textView46);
            this.PhoneNum = (TextView) view.findViewById(R.id.textView58);
            this.MobNum = (TextView) view.findViewById(R.id.textView59);
            this.Area = (TextView) view.findViewById(R.id.textView48);
            this.District = (TextView) view.findViewById(R.id.textView55);
            this.Email = (TextView) view.findViewById(R.id.textView61);
            this.Pin = (TextView) view.findViewById(R.id.textView62);
            this.Tin = (TextView) view.findViewById(R.id.textView63);
            this.Landmark = (TextView) view.findViewById(R.id.textView72);
            this.Added_by = (TextView) view.findViewById(R.id.textView60);
        }
    }

    public TutorialsAdapter(List<Tutorial> tutorialList) {
        this.tutorialList = tutorialList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_row, parent, false));
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tutorial dataModel = (Tutorial) this.tutorialList.get(position);
        holder.Shop_name.setText(String.valueOf(dataModel.getShopname()));
        holder.owner_name.setText(String.valueOf(dataModel.getOwnerName()));
        holder.place.setText(String.valueOf(dataModel.getPlace()));
        holder.Address.setText(String.valueOf(dataModel.getAddress()));
        holder.PhoneNum.setText(String.valueOf(dataModel.getMobile1()));
        holder.MobNum.setText(String.valueOf(dataModel.getMobile2()));
        holder.Area.setText(String.valueOf(dataModel.getShopArea()));
        holder.District.setText(String.valueOf(dataModel.getDistrict()));
        holder.Email.setText(String.valueOf(dataModel.getEmail()));
        holder.Pin.setText(String.valueOf(dataModel.getPin()));
        holder.Tin.setText(String.valueOf(dataModel.getTin()));
        holder.Landmark.setText(String.valueOf(dataModel.getLand_mark()));
        holder.Added_by.setText(String.valueOf(dataModel.getAdded_by()));
    }

    public int getItemCount() {
        return this.tutorialList.size();
    }
}
