package com.example.jingbiaozhen.commoditymanager;
/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment
{
    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.source_product_tv)
    TextView mSourceProductTv;

    @BindView(R.id.recommend_tv)
    TextView mRecommendTv;

    @BindView(R.id.log_query_tv)
    TextView mLogQueryTv;

    @BindView(R.id.store_tv)
    TextView mStoreTv;

    Unbinder unbinder;
    
    private List<Integer>images=new ArrayList<>();

    private Activity mContext;
    private CommodityBean mCommodityBean;
    private List<String> titles=new ArrayList<>();

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mContext = (Activity) context;
        Fresco.initialize(context);
        mCommodityBean= (CommodityBean) mContext.getIntent().getSerializableExtra("commodity");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initImages();
        initBanner();
        return view;
    }

    /**
     * 添加轮播图片
     */
    private void initImages() {
        images.add(R.drawable.banner1);
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner3);
        titles.add("标题你自己设置1");
        titles.add("标题你自己设置2");
        titles.add("标题你自己设置3");
    }

    private void initBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.source_product_tv, R.id.recommend_tv, R.id.log_query_tv, R.id.store_tv})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.source_product_tv:
                Intent intent=new Intent(mContext,ProductSourceActivity.class);
                intent.putExtra("commodity",mCommodityBean);
                mContext.startActivity(intent);
                break;
            case R.id.recommend_tv:
                mContext.startActivity(new Intent(mContext,ProductListACtivity.class));
                break;
            case R.id.log_query_tv:
                //物流查询
                mContext.startActivity(new Intent(mContext,LogQueryActivity.class));
                break;
            case R.id.store_tv:
                //附近门店，有时间待做
                break;
            default:
                break;
        }
    }
}
