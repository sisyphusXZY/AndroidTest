package com.example.xzy.logintest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XZY on 2016/8/6.
 */
public class UserInfoUtil {
    public static boolean saveUserInfo(Context context, String username, String password) {
        //save username and password
        try {
           //1.create a sharedpreferences object by context
            SharedPreferences sharedpreferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, String> getUserInfo(Context context){
        try{
            //another way to replace the below three step:
//            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");

            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("username", username);
            hashMap.put("password", password);
            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
