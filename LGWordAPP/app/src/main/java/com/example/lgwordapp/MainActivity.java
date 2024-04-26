package com.example.lgwordapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lgwordapp.db.MyDatabaseHelper;
import com.example.lgwordapp.info.User;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /*定义数据库所需成员变量 */
    private MyDatabaseHelper dbOpenHelper;
    private EditText userName;
    private EditText passWord;
    private Button loginButton;
    private Button signUpButton;
//    private User view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联activity.xml
        setContentView(R.layout.activity_main);
        // 关联用户名、密码和登录、注册按钮
        userName = (EditText) this.findViewById(R.id.UserNameEdit);
        passWord = (EditText) this.findViewById(R.id.PassWordEdit);
        loginButton = (Button) this.findViewById(R.id.LoginButton);
        signUpButton = (Button) this.findViewById(R.id.SignUpButton);
        // 登录按钮监听器
        loginButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();

                        if (!TextUtils.isEmpty(strUserName) && !TextUtils.isEmpty(strPassWord)) {
                            ArrayList<User> data = dbOpenHelper.getAllDATA();
                            boolean userFound = false;
                            for (int i = 0; i < data.size(); i++) {
                                User userdata= data.get(i);   //可存储账号数量
                                if (strUserName.equals(userdata.getName()) && strPassWord.equals(userdata.getPassword())) {
                                    userFound = true;
                                    break;
                                }
                            }

                            if (userFound == true) {
                                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Viewpager.class);
//                                intent.putExtra("username",name);
//                                intent.putExtra("password",password);  //展示账号密码功能
                                startActivity(intent);
//                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        dbOpenHelper = new MyDatabaseHelper(MainActivity.this);


        // 注册按钮监听器
        signUpButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到注册界面
                        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                        startActivity(intent);
//                        finish();
                    }
                }
        );

    }
}
