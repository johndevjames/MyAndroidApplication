package com.johndevjames.myandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter3 extends ArrayAdapter<DataModel3> {
    Createdetails Cd = new Createdetails(getContext());
    LoginDataBaseAdapter LB = new LoginDataBaseAdapter(getContext());
    private List<DataModel3> Order_Details;
    private ArrayList<DataModel3> arraylist;
    customButtonListener customListner;
    DataModel dataModelw;

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

    public CustomListAdapter3(Context context, int resource, List<DataModel3> tobjects) {
        super(context, R.layout.custom_list3, tobjects);
        this.Order_Details = tobjects;
        this.arraylist = new ArrayList();
        this.arraylist.addAll(tobjects);
    }

    public DataModel3 getItem(int position) {
        return (DataModel3) this.Order_Details.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DataModel3 dataModel = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list3, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.label1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.label2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.label3);
            holder.textView4 = (TextView) convertView.findViewById(R.id.textView36);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        this.Cd = this.Cd.open();
        this.LB = this.LB.open();
        String SHOPNAME = this.Cd.GETfinalOrder(dataModel.getCustomer_id());
        String prefix = this.LB.get_Prfeix(dataModel.getUser_Id());
        holder.textView1.setText(String.valueOf(SHOPNAME));
        holder.textView2.setText("Date:" + String.valueOf(dataModel.getOrder_date()));
        holder.textView3.setText("Order No:" + prefix + String.valueOf(dataModel.getOrder_no()));
        ArrayList item_id = this.Cd.getitem_id(dataModel.getOrder_no());
        ArrayList qty = this.Cd.getqty(dataModel.getOrder_no());
        float totalamount = 0.0f;
        for (int i = 0; i < item_id.size(); i++) {
            ArrayList rate = this.Cd.GETfinalOrder2(Integer.valueOf(((Integer) item_id.get(i)).intValue()));
            int q = ((Integer) qty.get(i)).intValue();
            this.dataModelw = (DataModel) rate.get(0);
            totalamount += ((float) q) * Float.parseFloat(this.dataModelw.getRate());
        }
        holder.textView4.setText("Total Amount:" + String.valueOf(totalamount));
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
                DataModel3 wp = (DataModel3) it.next();
                String SHOPNAME = this.Cd.GETfinalOrder(wp.getCustomer_id());
                if (wp.getOrder_no().toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                } else if (SHOPNAME.toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
