package com.example.jingbiaozhen.commoditymanager;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";

    private int[] mTabIcons = {R.drawable.home, R.drawable.culture, R.drawable.member, R.drawable.feedback};

    private List<Fragment> fragmentList;

    private ViewPager mTabViewpager;

    private FragmentTabHost mFragmentTabHost;

    private List<String> mTabTextList;

    private FragmentPagerAdapter adapter;

    private CommodityBean mCommodityBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        initLoginTimes();
    }

    /**
     * 初始化登录次数
     */
    private void initLoginTimes() {
        int times = getSharedPreferences("loginTimes", MODE_PRIVATE).getInt("times", 0);
        times++;
        SharedPreferences.Editor editor = getSharedPreferences("loginTimes", MODE_PRIVATE).edit();
        editor.putInt("times", times);
        editor.apply();
        showLoginTimes(times);
    }

    private void showLoginTimes(int times) {
        //展示使用说明
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //弹框图片你可以定义
        builder.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        builder.setTitle("登陆次数");

        //设置提示消息
        builder.setMessage("您成功登陆的次数为：" + times);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定按钮之后的回调
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void initData() {
        mCommodityBean = (CommodityBean) getIntent().getSerializableExtra("commodity");
        fragmentList = new ArrayList<>();
        mTabTextList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        CultureFragment cultureFragment = new CultureFragment();
        MemberFragment memberFragment = new MemberFragment();
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(cultureFragment);
        fragmentList.add(memberFragment);
        fragmentList.add(feedbackFragment);

        mTabTextList.add("商品活动");
        mTabTextList.add("企业文化");
        mTabTextList.add("会员福利");
        mTabTextList.add("意见反馈");

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };

    }

    private View getTabItemView(int i) {
        View view = View.inflate(this, R.layout.tab_content, null);
        TextView tabTv = view.findViewById(R.id.tab_tv);
        ImageView tabIv = view.findViewById(R.id.tab_iv);
        tabTv.setText(mTabTextList.get(i));
        tabIv.setBackgroundResource(mTabIcons[i]);

        return view;
    }

    private void initView() {
        mTabViewpager = findViewById(R.id.content_tabhost_viewpager);
        mTabViewpager.setAdapter(adapter);
        mFragmentTabHost = findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.content_tabhost_viewpager);
        for (int i = 0; i < mTabTextList.size(); i++) {
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(mTabTextList.get(i)).setIndicator(getTabItemView(i));
            mFragmentTabHost.addTab(tabSpec, fragmentList.get(i).getClass(), null);
            mFragmentTabHost.setTag(i);
        }
        // 设置没有分割线
        mFragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法
            }

            @Override
            public void onPageSelected(int position) {
                mFragmentTabHost.setCurrentTab(position);// 根据位置Postion设置当前的Tab
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // state ==1的时候表示正在滑动，state==2的时候表示滑动完毕了，state==0的时候表示什么都没做，就是停在那。

            }
        });

        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mTabViewpager.setCurrentItem(mFragmentTabHost.getCurrentTab());
            }
        });

    }

}
