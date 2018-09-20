package com.johndevjames.myandroidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter6 extends ArrayAdapter<DataModel2> {
    Createdetails Cd = new Createdetails(getContext());
    private List<DataModel2> Order_Details;
    private ArrayList<DataModel2> arraylist;
    customButtonListener customListner;

    private static class ViewHolder {
        public Button btnEdit;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;

        private ViewHolder() {
        }
    }

    public interface customButtonListener {
        void onButtonClickListner(int i);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public CustomListAdapter6(Context context, int resource, List<DataModel2> tobjects) {
        super(context, R.layout.custom_list6, tobjects);
        this.Order_Details = tobjects;
        this.arraylist = new ArrayList();
        this.arraylist.addAll(tobjects);
    }

    public DataModel2 getItem(int position) {
        return (DataModel2) this.Order_Details.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DataModel2 dataModel = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list6, parent, false);
            holder.textView4 = (TextView) convertView.findViewById(R.id.label4);
            holder.textView5 = (TextView) convertView.findViewById(R.id.label5);
            holder.btnEdit = (Button) convertView.findViewById(R.id.button13);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        this.Cd = this.Cd.open();
        String SHOPNAME = this.Cd.GETfinalOrder(dataModel.getCustomer_id());
        holder.textView4.setText("Product Name:" + String.valueOf(this.Cd.getItemName(dataModel.getProduct_no())));
        holder.textView5.setText("Qty:" + String.valueOf(dataModel.getQty()));
        String last_update_check = this.Cd.getUPDATED_STATUS2();
        holder.btnEdit.setVisibility(View.GONE);
        ArrayList Order4_Details = (ArrayList) this.Cd.getorderdetaislafterdate(last_update_check);
        for (int i = 0; i < Order4_Details.size(); i++) {
            if (((DataModel2) Order4_Details.get(i)).getId().intValue() <= dataModel.getId().intValue()) {
                holder.btnEdit.setVisibility(View.VISIBLE);
            }
        }
        final int i2 = position;
        holder.btnEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (CustomListAdapter6.this.customListner != null) {
                    CustomListAdapter6.this.customListner.onButtonClickListner(i2);
                }
            }
        });
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
                DataModel2 wp = (DataModel2) it.next();
                if (this.Cd.getItemName(wp.getProduct_no()).toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                } else if (wp.getQty().toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
