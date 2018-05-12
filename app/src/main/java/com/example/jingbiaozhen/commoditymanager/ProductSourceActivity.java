package com.example.jingbiaozhen.commoditymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by jingbiaozhen on 2018/5/9.
 **/

public class ProductSourceActivity extends AppCompatActivity
{

    @BindView(R.id.process1_tv)
    TextView mProcess1Tv;

    @BindView(R.id.process2_tv)
    TextView mProcess2Tv;

    @BindView(R.id.name_tv)
    TextView mNameTv;

    @BindView(R.id.date_tv)
    TextView mDateTv;

    @BindView(R.id.location_tv)
    TextView mLocationTv;

    private CommodityBean mCommodityBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_source);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        Intent intent = getIntent();
        if (intent != null)
        {
            // 获取MainActivity的数据
            mCommodityBean = (CommodityBean) intent.getSerializableExtra("commodity");
            if (mCommodityBean != null)
            {
                mProcess1Tv.setText(mCommodityBean.process1);
                mProcess2Tv.setText(mCommodityBean.process2);
                mNameTv.setText(mCommodityBean.name);
                mDateTv.setText(mCommodityBean.date);
                mLocationTv.setText(mCommodityBean.location);
            }
        }
    }


}
