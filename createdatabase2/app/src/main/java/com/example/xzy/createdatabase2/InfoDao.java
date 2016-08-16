package com.example.xzy.createdatabase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by XZY on 2016/8/16.
 */
public class InfoDao {


    private MySqliteOpenHelper mySqliteOpenHelper;
    public InfoDao(Context context){

        mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    public boolean add(InfoBean bean){

        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
//        db.execSQL("insert into info(name,phone) values(?,?);", new Object[]{bean.name,bean.phone});

        ContentValues values = new ContentValues();
        values.put("name", bean.name);
        values.put("phone", bean.phone);

        long result = db.insert("info", null, values);

        db.close();
        if(result != -1){
            return true;
        }else{
            return false;
        }
    }

    public int del(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

//        db.execSQL("delete from info where name=?;", new Object[]{name});

        int result = db.delete("info", "name = ?", new String[]{name});

        db.close();
        return result;
    }

    public int update(InfoBean bean){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

//        db.execSQL("update info set phone=? where name=?;", new Object[]{bean.phone,bean.name});

        ContentValues values = new ContentValues();
        values.put("name", bean.phone);
        int result = db.update("info", values, "name = ?", new String[]{bean.name});
        db.close();
        return result;
    }

    public void query(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //范例程序中没有分号，但我觉得应该有。
//        Cursor cursor = db.rawQuery("select _id,name,phone from info where name=?", new String[]{name});

        Cursor cursor = db.query("info", new String[]{"_id", "name", "phone"}, "name = ?", new String[]{name}, null, null, "_id desc");

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
