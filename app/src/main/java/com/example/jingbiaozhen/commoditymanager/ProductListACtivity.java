package com.example.jingbiaozhen.commoditymanager;
/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表页面
 */
public class ProductListACtivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener, android.widget.SearchView.OnCloseListener {
    @BindView(R.id.product_search_view)
    android.widget.SearchView mProductSearchView;

    @BindView(R.id.product_lv)
    ListView mProductLv;

    private List<Product> mProducts;
    private ProductAdapter mProductAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        initListData();
        initSearchView();
        // 设置监听器
        mProductAdapter=new ProductAdapter(ProductListACtivity.this, mProducts);
        mProductLv.setAdapter(mProductAdapter);
    }

    private void initSearchView() {
        // 设置该SearchView默认是否自动缩小为图标
        mProductSearchView.setIconifiedByDefault(false);
        // 为该搜索组件设置监听事件
        mProductSearchView.setOnQueryTextListener(this);// 查询的监听
        // 设置该SearchView显示搜索按钮
        mProductSearchView.setSubmitButtonEnabled(true);// 同意的按钮
        // 设置该SearchView内默认显示的提示文本
        mProductSearchView.setQueryHint("查找");// 查询默认的额字
    }

    /**
     * 自己添加商品列表的数据
     */
    private void initListData() {
        mProducts = new ArrayList<>();
        mProducts.add(new Product(R.drawable.culture, "abcabc....."));
        mProducts.add(new Product(R.drawable.banner3, "你好你好....."));
        mProducts.add(new Product(R.drawable.feedback, "哈哈哈哈哈....."));
        mProducts.add(new Product(R.drawable.home, "这是商品四....."));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mProductAdapter.getFilter().filter("");
            mProductAdapter.notifyDataSetChanged();
            Log.i("Nomad", "onQueryTextChange Empty String");
            mProductLv.clearTextFilter();
        } else {
            Log.i("Nomad", "onQueryTextChange " + newText.toString());
            mProductAdapter.getFilter().filter(newText.toString());
        }
        return true;
    }
    @Override
    public boolean onClose() {
        Log.i("Nomad", "onClose");
        return false;
    }




}
