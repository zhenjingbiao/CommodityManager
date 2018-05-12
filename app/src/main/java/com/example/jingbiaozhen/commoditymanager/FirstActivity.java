package com.example.jingbiaozhen.commoditymanager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by jingbiaozhen on 2018/5/10.
 **/

public class FirstActivity extends AppCompatActivity
{

    private static final String TAG = "FirstActivity";
    private static final int REQUEST_CODE_SCAN = 111;


    @BindView(R.id.scan_iv)
    ImageView mScanIv;

    @BindView(R.id.instruction_tv)
    TextView mInstructionTv;

    @BindView(R.id.history_tv)
    TextView mHistoryTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        boolean isFirstLogin = getSharedPreferences("firstLogin", MODE_PRIVATE).getBoolean("isFirstLogin", true);
        if (isFirstLogin)
        {
            initDB();
            SharedPreferences.Editor editor = getSharedPreferences("firstLogin", MODE_PRIVATE).edit();
            editor.putBoolean("isFirstLogin", false);
            editor.apply();
        }
    }

    @OnClick({R.id.scan_iv, R.id.instruction_tv, R.id.history_tv})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.scan_iv:
                scanQRCode();
                break;
            case R.id.instruction_tv:
                showInstruction();
                break;
            case R.id.history_tv:
                showHistory();
                break;
            default:
                break;
        }
    }

    //展示历史
    private void showHistory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //弹框图片你可以定义
        builder.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        builder.setTitle("扫描历史");

        //设置提示消息
        builder.setMessage("扫描历史1啊，扫描历史2");

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

    //展示使用说明
    private void showInstruction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //弹框图片你可以定义
        builder.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        builder.setTitle("使用说明");

        //设置提示消息
        builder.setMessage("使用说明内容说明，使用说明内容说明，使用说明内容说明");

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

    /**
     * 第一次进来的时候创建数据库,添加数据
     */
    private void initDB()
    {
        CommodityDBHelper mDBHelper = new CommodityDBHelper(this, "Commodity.db", null, 1);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", "毕设实例一");
        values.put("password", "982104");
        values.put("process1", "8000005批次 甲班");
        values.put("process2", "201801#011班次 甲班");
        values.put("name", "牡丹");
        values.put("date", "2018-01-28");
        values.put("location", "湖北A卷烟厂");
        db.insert("Commodity", null, values);
        values.clear();
        values.put("username", "毕设实例二");
        values.put("password", "549638");
        values.put("process1", "8000008批次 乙班");
        values.put("process2", "201805#018班次 乙班");
        values.put("name", "玉泉");
        values.put("date", "2018-05-25");
        values.put("location", "湖北B卷烟厂");
        db.insert("Commodity", null, values);
        values.clear();
        values.put("username", "毕设实例三");
        values.put("password", "289470");
        values.put("process1", "8000006批次 甲班");
        values.put("process2", "201803#014班次 甲班");
        values.put("name", "凤凰");
        values.put("date", "2018-03-21");
        values.put("location", "湖北C卷烟厂");
        db.insert("Commodity", null, values);

    }

    /**
     * 扫描二维码
     */
    private void scanQRCode()
    {
        /*
         * ZxingConfig是配置类 可以设置是否显示底部布局，闪光灯，相册，是否播放提示音 震动等动能 也可以不传这个参数 不传的话 默认都为默认不震动
         * 其他都为true
         */
        ZxingConfig config = new ZxingConfig();
        config.setShowbottomLayout(true);// 底部布局（包括闪光灯和相册）
        config.setPlayBeep(true);// 是否播放提示音
        config.setShake(true);// 是否震动
        config.setShowAlbum(true);// 是否显示相册
        config.setShowFlashLight(true);// 是否显示闪光灯

        // 如果不传 ZxingConfig的话，两行代码就能搞定了
        Intent intent = new Intent(FirstActivity.this, CaptureActivity.class);
        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK)
        {
            if (data != null)
            {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                queryCodeContent(content);
            }
        }
    }

    private void queryCodeContent(String content) {
        if(!TextUtils.isEmpty(content)){
            // 查询数据库 查询二维码信息对应的商品信息
            CommodityDBHelper mDBHelper = new CommodityDBHelper(this, "Commodity.db", null, 1);
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query("Commodity", null, "username=?", new String[] {content}, null, null, null);
            if(cursor==null){
                Toast.makeText(this, "不存在该商品", Toast.LENGTH_SHORT).show();
            }else {
                CommodityBean commodityBean = new CommodityBean();
                // 只有唯一的数据，所以遍历一次就行了
                if (cursor.moveToNext())
                {
                    for (int i = 0; i < cursor.getCount(); i++)
                    {
                        cursor.move(i);
                        commodityBean.username = cursor.getString(cursor.getColumnIndex("username"));
                        commodityBean.password = cursor.getString(cursor.getColumnIndex("password"));
                        commodityBean.process1 = cursor.getString(cursor.getColumnIndex("process1"));
                        commodityBean.process2 = cursor.getString(cursor.getColumnIndex("date"));
                        commodityBean.name = cursor.getString(cursor.getColumnIndex("name"));
                        commodityBean.date = cursor.getString(cursor.getColumnIndex("username"));
                        commodityBean.location = cursor.getString(cursor.getColumnIndex("location"));
                        Log.d(TAG, "loginIn: " + commodityBean.toString());
                    }
                }
                cursor.close();
                Intent intent=new Intent(FirstActivity.this,LoginActivity.class);
                intent.putExtra("commodity", commodityBean);
                startActivity(intent);
            }

        }

    }
}
