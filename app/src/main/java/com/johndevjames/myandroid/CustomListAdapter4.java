package com.johndevjames.myandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter4 extends ArrayAdapter<Tutorial2> {
    private List<Tutorial2> Order_Details;
    private ArrayList<Tutorial2> arraylist = new ArrayList();

    private static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;

        private ViewHolder() {
        }
    }

    public CustomListAdapter4(Context context, int resource, List<Tutorial2> tobjects) {
        super(context, R.layout.custom_list4, tobjects);
        this.Order_Details = tobjects;
        this.arraylist.addAll(tobjects);
    }

    public Tutorial2 getItem(int position) {
        return (Tutorial2) this.Order_Details.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Tutorial2 dataModel = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list4, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.label3);
            holder.textView2 = (TextView) convertView.findViewById(R.id.label5);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(String.valueOf(dataModel.getCustomer_name()));
        holder.textView2.setText("Out-Standing Balance: " + String.valueOf(dataModel.getOutstanding_bal()));
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        this.Order_Details.clear();
        if (charText.length() == 0) {
            this.Order_Details.addAll(this.arraylist);
        } else {
            Iterator it = this.arraylist.iterator();
            while (it.hasNext()) {
                Tutorial2 wp = (Tutorial2) it.next();
                if (wp.getCustomer_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
