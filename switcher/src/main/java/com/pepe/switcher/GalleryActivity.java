package com.pepe.switcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

/**
 * Created by wang on 2017/9/6.
 * 参考博客：
 * Gallery与Imageswitch完美结合 做相册一绝啊 - ncg_1 android应用 - CSDN博客
 * http://blog.csdn.net/ncg_1/article/details/7932251
 */
public class GalleryActivity extends Activity implements ViewSwitcher.ViewFactory, AdapterView.OnItemSelectedListener {

    private Gallery gallery;
    private ImageAdaper imageAdaper;
    private int[] imageIds = new int[]{R.mipmap.bg1, R.mipmap.bg2,
            R.mipmap.bg3, R.mipmap.bg4};
    private ImageSwitcher imageSwitcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageAdaper = new ImageAdaper(this);
        gallery = (Gallery) findViewById(R.id.gallery_id);
        gallery.setOnItemSelectedListener(this);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher_id);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        gallery.setAdapter(imageAdaper);
    }

    public class ImageAdaper extends BaseAdapter {

        Context context;
        int mGalleryItemBackground;

        public ImageAdaper(Context context) {
            this.context = context;
//            TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
//            mGalleryItemBackground = typedArray.getResourceId(
//                    R.styleable.Gallery_android_galleryItemBackground, 0);
        }


        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return imageIds[position];
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView imageview = new ImageView(this.context);
            imageview.setBackgroundResource(imageIds[position % imageIds.length]);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            imageview.setLayoutParams(new Gallery.LayoutParams(136, 88));
            return imageview;
        }

    }

    public View makeView() {
        // TODO Auto-generated method stub
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(imageView.getScaleType().FIT_CENTER);
        imageView.setBackgroundColor(0xFF000000);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        return imageView;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // TODO Auto-generated method stub
        imageSwitcher.setImageResource(imageIds[position % imageIds.length]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }


}
