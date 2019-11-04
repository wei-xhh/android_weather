package com.example.xhhweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.xhhweather.gson.Weather;
import com.google.gson.Gson;

public class Utility {
    private static AlertDialog mAlertDialog;

    /**
     * 将放回的json数据解析成Weather 实体类
     */
    public static Weather handleWeatherRsponse(String response){
        Gson gson =new Gson();
        Weather weatherInfo=gson.fromJson(response,Weather.class);
        return weatherInfo;
    }


    /**
     * 弹出耗时对话框
     * @param context
     */
    public static void showProgressDialog(Context context) {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
        }

        View loadView = LayoutInflater.from(context).inflate(R.layout.circle_layout, null);
        mAlertDialog.setView(loadView);
        mAlertDialog.setCanceledOnTouchOutside(false);

        TextView tvTip = loadView.findViewById(R.id.tvTip);
        tvTip.setText("加载中...");

        mAlertDialog.show();
    }

    /**
     * 隐藏耗时对话框
     */
    public static void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }


}