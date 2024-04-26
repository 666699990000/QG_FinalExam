package com.example.lgwordapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lgwordapp.db.HistoryDbHelper;
import com.example.lgwordapp.info.RespondBean;
import com.example.lgwordapp.other.BaiduTranslateService;
import com.example.lgwordapp.other.MD5Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFragment1 extends Fragment {
    private static final String TAG = "MyFragment1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_translation, container, false);

        Button button = rootView.findViewById(R.id.fanyi);
        EditText editText = rootView.findViewById(R.id.ed_content);
        TextView textView = rootView.findViewById(R.id.tv_result);
        TextView textView1 = rootView.findViewById(R.id.tv_from);
        TextView textView2 = rootView.findViewById(R.id.tv_to);
        ImageView imageView =rootView.findViewById(R.id.iv_clear_tx);

        imageView.setOnClickListener(new View.OnClickListener() {//删除文本
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            String to;
            String word;
//            String result;

            @Override
            public void onClick(View v) {
                word = editText.getText().toString();

                if(word.length()!= 0){//中英文识别
                if (word.length() == word.getBytes().length) {
                    to = "zh";
                    textView1.setText("英文");
                    textView2.setText("中文");
                } else {
                    to = "en";
                    textView1.setText("中文");
                    textView2.setText("英文");
                }}

                String from = "auto";
                String appid = "20240412002022396";
                String salt = (int) (Math.random() * 100 + 1) + "";
                String key = "_4R9wjHMOTDN7PF2z9ee";
                String string1 = appid + word + salt + key;
                String sign = MD5Utils.getMD5Code(string1);
                Log.d(TAG, "string1：" + string1);
                Log.d(TAG, "sign1234: " + sign);
                Retrofit retrofitBaidu = new Retrofit.Builder()
                        .baseUrl("https://fanyi-api.baidu.com/api/trans/vip/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                BaiduTranslateService baiduTranslateService = retrofitBaidu.create(BaiduTranslateService.class);
                Call<RespondBean> call = baiduTranslateService.translate(word, from, to, appid, salt, sign);
                call.enqueue(new Callback<RespondBean>() {
                    @Override
                    public void onResponse(Call<RespondBean> call, Response<RespondBean> response) {//接口响应
                        Log.d(TAG, "onResponse: 请求成功");
                        RespondBean respondBean = response.body();
                        if (respondBean != null) {
                            List<RespondBean.TransResultBean> transResult = respondBean.getTrans_result();
                            if (transResult != null && !transResult.isEmpty()) {
                                RespondBean.TransResultBean firstResult = transResult.get(0);
                                if (firstResult != null) {
                                    String result = firstResult.getDst();
                                    String source = firstResult.getSrc();
                                    if (result != null) {
                                        Log.d(TAG, "翻译结果" + result);
                                        textView.setText(result);
                                        HistoryDbHelper.getInstance(requireActivity()).addHistory(source,result);
                                        return;
                                    }
                                }
                            }
                        }
                        Log.e(TAG, "无法获取翻译结果");
                    }

                    @Override
                    public void onFailure(Call<RespondBean> call, Throwable t) {
                        Toast.makeText(getActivity(), "登录失败！", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: 请求失败 " + t);
                    }
                });
            }
        });

        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: 0000-------MyFragment1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: 0000--------MyFragment1");
    }

    public MyFragment1() {
    }

}
