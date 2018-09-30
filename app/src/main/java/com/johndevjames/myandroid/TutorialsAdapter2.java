package com.johndevjames.myandroid;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class TutorialsAdapter2 extends Adapter<TutorialsAdapter2.MyViewHolder> {
    private List<Tutorial2> tutorialList;

    public class MyViewHolder extends ViewHolder {
        public TextView textView1;
        public TextView textView2;

        public MyViewHolder(View view) {
            super(view);
            this.textView1 = (TextView) view.findViewById(R.id.textView13);
            this.textView2 = (TextView) view.findViewById(R.id.textView12);
        }
    }

    public TutorialsAdapter2(List<Tutorial2> tutorialList) {
        this.tutorialList = tutorialList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_row2, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tutorial2 dataModel = (Tutorial2) this.tutorialList.get(position);
        String e = String.valueOf(dataModel.getOutstanding_bal().intValue());
        holder.textView1.setText(String.valueOf(dataModel.getCustomer_name()));
        holder.textView2.setText(e);
    }

    public int getItemCount() {
        return this.tutorialList.size();
    }
}
