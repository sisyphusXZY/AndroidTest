package com.example.xzy.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(mContext);
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();

        findViewById(R.id.bt_add).setOnClickListener((View.OnClickListener) mContext);
        findViewById(R.id.bt_del).setOnClickListener((View.OnClickListener) mContext);
        findViewById(R.id.bt_query).setOnClickListener((View.OnClickListener) mContext);
        findViewById(R.id.bt_update).setOnClickListener((View.OnClickListener) mContext);
    }

    @Override
    public void onClick(View v) {
        InfoDao infoDao = new InfoDao(mContext);

        switch (v.getId()){
            case R.id.bt_add:
                InfoBean bean = new InfoBean();
                bean.name = "zhang";
                bean.phone = "110";
                infoDao.add(bean);

                InfoBean bean1 = new InfoBean();
                bean1.name = "li";
                bean1.phone = "120";
                infoDao.add(bean1);
                break;
            case R.id.bt_del:
                infoDao.del("zhang");
                break;
            case R.id.bt_update:
                InfoBean bean2 = new InfoBean();
                bean2.name = "li";
                bean2.phone = "911";
                /*int update = */infoDao.update(bean2);
                Toast.makeText(mContext,"successful a row",0).show();
                break;
            case R.id.bt_query:
                infoDao.query("zhang");
//                infoDao.query("li");
                break;
            default:
                break;
        }
    }
}
