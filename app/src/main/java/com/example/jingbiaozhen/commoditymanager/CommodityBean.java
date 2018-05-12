package com.example.jingbiaozhen.commoditymanager;
/*
 * Created by jingbiaozhen on 2018/5/10.
 * 商品类javabean
 **/

import java.io.Serializable;

public class CommodityBean implements Serializable
{
    public String username;// 二维码数据

    public String password;// 验证码

    public String process1;// 卷包流程

    public String process2;// 制丝流程

    public String name;// 名称

    public String date;// 生产日期

    public String location;// 生产地点

    @Override
    public String toString()
    {
        return "CommodityBean{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", process1='"
                + process1 + '\'' + ", process2='" + process2 + '\'' + ", name='" + name + '\'' + ", date='" + date
                + '\'' + ", location='" + location + '\'' + '}';
    }
}
