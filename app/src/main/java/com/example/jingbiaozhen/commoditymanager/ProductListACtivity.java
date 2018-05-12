package com.example.jingbiaozhen.commoditymanager;
/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表页面
 */
public class ProductListACtivity extends AppCompatActivity
{
    @BindView(R.id.product_search_view)
    SearchView mProductSearchView;

    @BindView(R.id.product_lv)
    ListView mProductLv;

    private List<Product> mProducts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        initData();

    }

    private void initData()
    {
        initListData();
        mProductLv.setAdapter(new ProductAdapter());
    }

    /**
     * 自己添加商品列表的数据
     */
    private void initListData()
    {
        mProducts = new ArrayList<>();
        mProducts.add(new Product(R.drawable.culture, "这是商品一....."));
        mProducts.add(new Product(R.drawable.banner3, "这是商品二....."));
        mProducts.add(new Product(R.drawable.feedback, "这是商品三....."));
        mProducts.add(new Product(R.drawable.home, "这是商品四....."));
    }

    class ProductAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mProducts.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mProducts.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;
            if (convertView == null)
            {
                convertView = View.inflate(ProductListACtivity.this, R.layout.item_product, null);
                holder=new ViewHolder();
                holder.imageView=convertView.findViewById(R.id.product_iv);
                holder.textView=convertView.findViewById(R.id.product_desc_tv);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }
            Product product=mProducts.get(position);
            holder.imageView.setImageResource(product.image);
            holder.textView.setText(product.desc);

            return convertView;
        }
    }

    class ViewHolder
    {
        ImageView imageView;

        TextView textView;

    }

    class Product
    {
        Integer image;// 图片资源

        String desc;// 商品描述

        Product(Integer image, String desc)
        {
            this.image = image;
            this.desc = desc;
        }
    }
}
