package com.johndevjames.myandroidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<DataModel> {
    private List<DataModel> Order_Details;
    customButtonListener customListner;
    customButtonListener customListner2;
    private Integer[] imageId;

    private static class ViewHolder {
        public Button btnEdit;
        public ImageView imageView;
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

    public void setCustomImageListner(customButtonListener listener2) {
        this.customListner2 = listener2;
    }

    public CustomListAdapter(Context context, int resource, List<DataModel> objects) {
        super(context, R.layout.custom_list, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DataModel dataModel = (DataModel) getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.label1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.label2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.label3);
            holder.textView4 = (TextView) convertView.findViewById(R.id.label4);
            holder.textView5 = (TextView) convertView.findViewById(R.id.label5);
            holder.btnEdit = (Button) convertView.findViewById(R.id.button1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(String.valueOf(dataModel.getItemName()));
        holder.textView2.setText("Item Code: " + String.valueOf(dataModel.getItemCode()));
        holder.textView3.setText("Qty: " + String.valueOf(dataModel.getQuantity()));
        holder.textView4.setText("Rate: " + String.valueOf(dataModel.getRate()));
        holder.textView5.setText("Mrp: " + String.valueOf(dataModel.getMrp()));
        holder.btnEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (CustomListAdapter.this.customListner != null) {
                    CustomListAdapter.this.customListner.onButtonClickListner(position);
                }
            }
        });
        return convertView;
    }
}
