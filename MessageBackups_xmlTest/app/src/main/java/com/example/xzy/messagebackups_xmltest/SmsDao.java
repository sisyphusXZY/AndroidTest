package com.example.xzy.messagebackups_xmltest;

import java.util.ArrayList;

/**
 * Created by XZY on 2016/8/8.
 */
public class SmsDao {
    public static ArrayList<SmsBean> getAllSms() {
        ArrayList<SmsBean> arrayList =  new ArrayList<SmsBean>();
        SmsBean smsBean = new SmsBean();
        smsBean.date = "11";
        smsBean.id = 1;
        smsBean.msg = "hhhhhhh";
        smsBean.num= "110";
        arrayList.add(smsBean);

        SmsBean smsBean1 = new SmsBean();
        smsBean1.date = "22";
        smsBean1.id = 2;
        smsBean1.msg = "ttttttt";
        smsBean1.num= "110";
        arrayList.add(smsBean1);

        SmsBean smsBean2 = new SmsBean();
        smsBean2.date = "33";
        smsBean2.id = 3;
        smsBean2.msg = "rrrrrrrr";
        smsBean2.num= "110";
        arrayList.add(smsBean2);
        return arrayList;
    }
}
