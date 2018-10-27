package com.johndevjames.myandroid;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class cardview extends AppCompatActivity {
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private RecyclerView recyclerView;

    public class GridSpacingItemDecoration extends ItemDecoration {
        private boolean includeEdge;
        private int spacing;
        private int spanCount;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % this.spanCount;
            if (this.includeEdge) {
                outRect.left = this.spacing - ((this.spacing * column) / this.spanCount);
                outRect.right = ((column + 1) * this.spacing) / this.spanCount;
                if (position < this.spanCount) {
                    outRect.top = this.spacing;
                }
                outRect.bottom = this.spacing;
                return;
            }
            outRect.left = (this.spacing * column) / this.spanCount;
            outRect.right = this.spacing - (((column + 1) * this.spacing) / this.spanCount);
            if (position >= this.spanCount) {
                outRect.top = this.spacing;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.albumList = new ArrayList();
        this.adapter = new AlbumsAdapter(this, this.albumList);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(this.adapter);
        prepareAlbums();
        try {
            //Glide.with(this).load(Integer.valueOf(R.drawable.cover)).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareAlbums() {
        int[] covers = new int[]{R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3, R.mipmap.image3};
        this.albumList.add(new Album("True Romance", 13, covers[0]));
        this.albumList.add(new Album("Xscpae", 8, covers[1]));
        this.albumList.add(new Album("Maroon 5", 11, covers[2]));
        this.albumList.add(new Album("Born to Die", 12, covers[3]));
        this.albumList.add(new Album("Honeymoon", 14, covers[4]));
        this.albumList.add(new Album("I Need a Doctor", 1, covers[5]));
        this.albumList.add(new Album("Loud", 11, covers[6]));
        this.albumList.add(new Album("Legend", 14, covers[7]));
        this.albumList.add(new Album("Hello", 11, covers[8]));
        this.albumList.add(new Album("Greatest Hits", 17, covers[9]));
        this.adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(1, (float) dp, getResources().getDisplayMetrics()));
    }
}
