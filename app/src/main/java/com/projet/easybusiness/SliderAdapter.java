package com.projet.easybusiness;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;


public class SliderAdapter extends PagerAdapter {

    private Context context;
    private  String[] images;
    private TextView slideNumber;


    public  SliderAdapter(Context contextP, String[] imageUrl,TextView slideNum){
        this.context=contextP;
        this.images=imageUrl;
        this.slideNumber= slideNum;

    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView= new ImageView(context);


        slideNumber.setText(position+1+"/"+getCount());

        Picasso.get().load(images[position])
                .fit().centerCrop().error(R.drawable.laptop_hp).into(imageView);
        container.addView(imageView);

        Log.i("YKJ",images[position]+" indice after+ "+container.toString());

        return imageView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
