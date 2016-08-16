package com.example.xzy.transaction;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void transaction(View v){
        BankOpenHelper bankOpenHelper = new BankOpenHelper(this);
        SQLiteDatabase db = bankOpenHelper.getReadableDatabase();
        db.beginTransaction();
        try{
            db.execSQL("update account set money= money-200 where name=?",new String[]{"李四"});
            int i = 100/0;//模拟一个异常
            db.execSQL("update account set money= money+200 where name=?",new String[]{"张三"});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }
}
