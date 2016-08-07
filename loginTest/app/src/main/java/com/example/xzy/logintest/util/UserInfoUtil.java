package com.example.xzy.logintest.util;

import android.content.Context;
import android.os.Environment;

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
            String userInfo = username + "##" + password;//packaging
//            String path = "/data/data/com.example.xzy.logintest";
//            String path = context.getFilesDir().getPath();
//            String path = "/mnt/sdcard";
            String path = Environment.getExternalStorageDirectory().getPath();
            File file =  new File(path, "userInfo.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(userInfo.getBytes());
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, String> getUserInfo(Context context){
        try{
//            String path = "/data/data/com.example.xzy.logintest";
//            String path = context.getFilesDir().getPath();
            String path = Environment.getExternalStorageDirectory().getPath();

            File file =  new File(path, "userInfo.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("##");
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("username", split[0]);
            hashMap.put("password", split[1]);
            bufferedReader.close();
            fileInputStream.close();
            return hashMap;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
