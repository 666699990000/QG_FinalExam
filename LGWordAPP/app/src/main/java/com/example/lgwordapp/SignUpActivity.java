package com.example.lgwordapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lgwordapp.db.MyDatabaseHelper;

public class SignUpActivity extends AppCompatActivity {
    private MyDatabaseHelper dbOpenHelper;
    private EditText userName;
    private EditText passWord;
    private EditText passWordAgain;
    private Button signUpButton;
    private Button backLoginButton;

    // 调用Actvity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关联activity_register.xml
        setContentView(R.layout.activity_sign_up);
        // 关联用户名、密码、确认密码、注册、返回登录按钮
        userName = (EditText) this.findViewById(R.id.UserNameEdit);
        passWord = (EditText) this.findViewById(R.id.PassWordEdit);
        passWordAgain = (EditText) this.findViewById(R.id.PassWordAgainEdit);
//        EditText email = (EditText) this.findViewById(R.id.EmailEdit);
        signUpButton = findViewById(R.id.SignUpButton);
        backLoginButton = (Button) this.findViewById(R.id.BackLoginButton);
        //实例化数据库变量
//        dbOpenHelper = new MyDatabaseHelper(SignUpActivity.this,"user.db",null,1);
        // 立即注册按钮监听器
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        String strPassWordAgain = passWordAgain.getText().toString().trim();
//                        String strPhoneNumber = email.getText().toString().trim();
                        //注册格式粗检
                        if (strUserName.length() == 0) {
                            Toast.makeText(SignUpActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                        } else if (strUserName.length() > 10) {
                            Toast.makeText(SignUpActivity.this, "用户名长度必须小于10！", Toast.LENGTH_SHORT).show();
                        } else if (strUserName.length() < 4) {
                            Toast.makeText(SignUpActivity.this, "用户名长度必须大于4！", Toast.LENGTH_SHORT).show();
                        }else if (strPassWord.length() == 0) {
                            Toast.makeText(SignUpActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() > 16) {
                            Toast.makeText(SignUpActivity.this, "密码长度必须小于16！", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() < 6) {
                            Toast.makeText(SignUpActivity.this, "密码长度必须大于6！", Toast.LENGTH_SHORT).show();
                        } else if (!strPassWord.equals(strPassWordAgain)) {
                            Toast.makeText(SignUpActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                        } else {
                            dbOpenHelper.add(strUserName,strPassWord);
//                            //存储注册信息
//                            insertData(dbOpenHelper.getReadableDatabase(),strUserName,strPassWord);
                            //消息提示
                            Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            // 跳转到登录界面
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
//                            finish();
                        }
                    }
                }
        );
        // 返回登录按钮监听器
        backLoginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到登录界面
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
//                        finish();
                    }
                }
        );
        dbOpenHelper = new MyDatabaseHelper(SignUpActivity.this);
    }
}



