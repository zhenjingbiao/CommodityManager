package com.example.jingbiaozhen.commoditymanager;
/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity
{
    @BindView(R.id.code_invalid_et)
    EditText mCodeInvalidEt;

    @BindView(R.id.login_btn)
    Button mLoginBtn;

    private CommodityBean mCommodityBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        Intent intent = getIntent();
        mCommodityBean = (CommodityBean) intent.getSerializableExtra("commodity");
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked()
    {
        String password = mCodeInvalidEt.getText().toString();
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "请填写验证码", Toast.LENGTH_SHORT).show();
        }
        if (mCommodityBean != null)
        {
            if (password.equals(mCommodityBean.password))
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("commodity", mCommodityBean);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "验证码错误，请重新填写", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
