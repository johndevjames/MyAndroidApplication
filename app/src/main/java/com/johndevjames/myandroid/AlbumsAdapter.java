package com.johndevjames.myandroid;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumsAdapter extends Adapter<AlbumsAdapter.MyViewHolder> {
    private List<Album> albumList;
    private Context mContext;

    class MyMenuItemClickListener implements OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
               /* case R.id.action_add_favourite *//*2131493247*//*:
                    Toast.makeText(AlbumsAdapter.this.mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next *//*2131493248*//*:
                    Toast.makeText(AlbumsAdapter.this.mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;*/
                default:
                    return false;
            }
        }
    }

    public class MyViewHolder extends ViewHolder {
        public TextView count;
        public ImageView overflow;
        public ImageView thumbnail;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.count = (TextView) view.findViewById(R.id.count);
            this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = (Album) this.albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " songs");
        Glide.with(this.mContext).load(Integer.valueOf(album.getThumbnail())).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AlbumsAdapter.this.showPopupMenu(holder.overflow);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this.mContext, view);
        popup.getMenuInflater().inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    public int getItemCount() {
        return this.albumList.size();
    }
}
