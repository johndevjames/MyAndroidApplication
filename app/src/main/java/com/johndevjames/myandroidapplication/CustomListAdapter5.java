package com.johndevjames.myandroidapplication;

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

public class CustomListAdapter5 extends ArrayAdapter<Tutorial3> {
    private List<Tutorial3> Order_Details;
    private ArrayList<Tutorial3> arraylist = new ArrayList();

    private static class ViewHolder {
        public TextView textView1;
        public TextView textView2;

        private ViewHolder() {
        }
    }

    public CustomListAdapter5(Context context, int resource, List<Tutorial3> tobjects) {
        super(context, R.layout.custom_list5, tobjects);
        this.Order_Details = tobjects;
        this.arraylist.addAll(tobjects);
    }

    public Tutorial3 getItem(int position) {
        return (Tutorial3) this.Order_Details.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Tutorial3 dataModel5 = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list5, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.label3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(String.valueOf(dataModel5.getCustomer_name()));
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
                Tutorial3 wp = (Tutorial3) it.next();
                if (wp.getCustomer_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
