package com.example.xzy.logintest;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xzy.logintest.util.UserInfoUtil;

import java.io.File;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        //获取相应控件
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        Button bt_login = (Button) findViewById(R.id.bt_login);
        //设置按钮点击事件
        bt_login.setOnClickListener(this);
        //回显
        Map<String, String> map = UserInfoUtil.getUserInfo(mContext);
        if(map != null){
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cb_rem.setChecked(true);
        }

    }

    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        boolean isRem = cb_rem.isChecked();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(mContext, "username and password can not be empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        //请求服务器暂时不写，默认连上

        if(isRem){
            //judge the sdcard state
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                Toast.makeText(mContext,"sdcard is unmounted",Toast.LENGTH_LONG).show();
                return ;
            }

            //judge the sdcard usableSpace
            File sdcard_fileDir = Environment.getExternalStorageDirectory();
            long usableSpace = sdcard_fileDir.getUsableSpace();
//            long totalSpace = sdcard_fileDir.getTotalSpace();
            //long to G String
            String usableSpace_str = Formatter.formatFileSize(mContext, usableSpace);
//            String totalSpace_str = Formatter.formatFileSize(mContext, totalSpace);

            if(usableSpace < 1024 * 1024 * 100){
                Toast.makeText(mContext, "sdcard have no usableSpace" + usableSpace_str, Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = UserInfoUtil.saveUserInfo(mContext, username, password);
            if(result){
                Toast.makeText(mContext, "Save Successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext, "Save failure", Toast.LENGTH_SHORT).show();
            }

        }else
            Toast.makeText(mContext, "Do not Save", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
            default:
                break;
        }

    }
}
