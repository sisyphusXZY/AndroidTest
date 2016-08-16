package com.example.xzy.createdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.provider.Settings;

/**
 * Created by XZY on 2016/8/16.
 */
public class InfoDao {


    private MySqliteOpenHelper mySqliteOpenHelper;
    public InfoDao(Context context){
        mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }
    public void add(InfoBean bean){

        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        db.execSQL("insert into info(name,phone) values(?,?);", new Object[]{bean.name,bean.phone});
        db.close();
    }

    public void del(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        db.execSQL("delete from info where name=?;", new Object[]{name});
        db.close();
    }

    public void update(InfoBean bean){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        db.execSQL("update info set phone=? where name=?;", new Object[]{bean.phone,bean.name});
        db.close();
//        return result;
    }

    public void query(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //范例程序中没有分号，但我觉得应该有。
        Cursor cursor = db.rawQuery("select _id,name,phone from info where name=?", new String[]{name});

        if(cursor != null && cursor.getCount() > 0){

            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);

                System.out.println("id:"+id+"; name:"+name_str+"; phone:"+phone);
            }
            cursor.close();
        }

        db.close();
    }
}
