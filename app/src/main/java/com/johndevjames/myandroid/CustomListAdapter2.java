package com.johndevjames.myandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CustomListAdapter2 extends ArrayAdapter<DataModel> {
    private List<DataModel> Order_Details;
    private ArrayList<DataModel> arraylist = new ArrayList();
    customImageListener2 customListner3;
    customListener2 customListner4;
    private int imageId;
    private ArrayList<String> st;
    String imURL="http://invoiz.in/SOApp/uploads/Thumb";
    ViewHolder holder;

    private static class ViewHolder {
        public EditText editText;
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;

        private ViewHolder() {
        }
    }

    public interface customImageListener2 {
        void onImageClickListner2(int i);
    }

    public interface customListener2 {
        void onClickListner2(int i);
    }

    public void setCustomImageListner2(customImageListener2 listener3) {
        this.customListner3 = listener3;
    }

    public void setCustomListner2(customListener2 Listner4) {
        this.customListner4 = Listner4;
    }

    public CustomListAdapter2(Context context, int resource, List<DataModel> tobjects) {
        super(context, R.layout.custom_list2, tobjects);
        this.Order_Details = tobjects;
        this.arraylist.addAll(tobjects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        DataModel dataModel = (DataModel) getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list2, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.label1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.label2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.label3);
            holder.textView4 = (TextView) convertView.findViewById(R.id.label4);
            holder.textView5 = (TextView) convertView.findViewById(R.id.label5);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(String.valueOf(dataModel.getItemName()));
        holder.textView2.setText(" Item Code: " + String.valueOf(dataModel.getItemCode()));
        holder.textView3.setText(" Qty: " + String.valueOf(dataModel.getQuantity()));
        holder.textView4.setText(" Rate: " + String.valueOf(dataModel.getRate()));
        holder.textView5.setText(" Mrp: " + String.valueOf(dataModel.getMrp()));

       /* RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);*/

        Glide.with(getContext())
                .load(imURL+"_1_"+dataModel.getItemId()+".jpg")
                //.thumbnail(0.5f)
                //.apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image))
                //.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .transition(withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into( holder.imageView);




        holder.imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (CustomListAdapter2.this.customListner3 != null) {
                    CustomListAdapter2.this.customListner3.onImageClickListner2(position);
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
                DataModel wp = (DataModel) it.next();
                if (wp.getItemName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                } else if (wp.getItemCode().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.Order_Details.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
