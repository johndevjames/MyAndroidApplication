package com.johndevjames.myandroidapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class addImage extends Activity {
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_add_image);
         imageView = (ImageView) findViewById(R.id.imageView2);
        String  key_id = String.valueOf(getIntent().getExtras().getInt("Item_id"));
        String imgUrl = null;
        imgUrl="http://invoiz.in/SOApp/uploads/Large_1_"+key_id+".jpg";
        //

        //imgUrl=key_id;
     /*   switch (0) {
            case R.styleable.View_android_theme *//*0*//*:
                imgUrl = "https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png";
                break;
            case R.styleable.View_paddingStart *//*2*//*:
                imgUrl = "https://cdn4.iconfinder.com/data/icons/cloud-computing-solid-icons-vol-2/72/75-512.png";
                break;
            case R.styleable.View_paddingEnd *//*3*//*:
                imgUrl = "https://cdn4.iconfinder.com/data/icons/cloud-computing-solid-icons-vol-2/72/75-512.png";
                break;
        }*/
        Glide.with(this)
                .load(imgUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image)
                .listener(new RequestListener<String, GlideDrawable>() {
                              @Override
                              public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                                         boolean isFirstResource)
                              {
                                  Glide.with(getApplicationContext())
                                          .load("https://upload.wikimedia.org/wikipedia/en/d/d1/Image_not_available.png")
                                          .thumbnail(0.5f)
                                          .crossFade()
                                          .into(imageView);
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                             boolean isFromMemoryCache, boolean isFirstResource)
                              {

                                  return false;
                              }
                          }
                ).into(imageView);


    }
}
