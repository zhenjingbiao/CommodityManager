package com.example.jingbiaozhen.commoditymanager;

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide.with(context).load(path).into(imageView);   此项目不建议用Glide，会产生冲突
        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        imageView.setImageResource((Integer) path);
    }
    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
