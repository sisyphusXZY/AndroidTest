package com.example.xzy.messagebackups_xmltest;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by XZY on 2016/8/8.
 */
public class SmsUtils {

    public static boolean backupSms_xmlSerializer(Context context){

        ArrayList<SmsBean> allSms = SmsDao.getAllSms();
        //create a XmlSerializer objects
        try {
            XmlSerializer xs = Xml.newSerializer();

            xs.setOutput(context.openFileOutput("backupSms2.xml", Context.MODE_PRIVATE), "utf-8");
            xs. startDocument("utf-8", true);
            xs.startTag(null,"Smss");

            for (SmsBean smsBean : allSms) {
                xs.startTag(null,"Sms");
                xs.attribute(null,"id", smsBean.id + "");

                xs.startTag(null,"num");
                xs.text(smsBean.num);
                xs.endTag(null,"num");

                xs.startTag(null,"msg");
                xs.text(smsBean.msg);
                xs.endTag(null,"msg");

                xs.startTag(null,"date");
                xs.text(smsBean.date);
                xs.endTag(null,"date");

                xs.endTag(null,"Sms");

            }
            xs.endTag(null,"Smss");

            xs.endDocument();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean backupSms(Context context) {

        //use list to get massage. Connect to DAO(date access objects)
        ArrayList<SmsBean> allSms = SmsDao.getAllSms();

        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes'?>");
        sb.append("<Smss>");

        for(SmsBean smsBean : allSms){
            sb.append("<Sms id = \"" + smsBean.id + "\">");

            sb.append("<num>");
            sb.append(smsBean.num);
            sb.append("</num>");

            sb.append("<msg>");
            sb.append(smsBean.msg);
            sb.append("</msg>");

            sb.append("<date>");
            sb.append(smsBean.date);
            sb.append("</date>");

            sb.append("</Sms");
        }
        sb.append("</Smss>");
        //write stringBuffer to xml
        try {
            FileOutputStream openFileOutput = context.openFileOutput("backupSms.xml",Context.MODE_PRIVATE);
            openFileOutput.write(sb.toString().getBytes());
            openFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

}
