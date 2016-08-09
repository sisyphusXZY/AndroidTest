package com.example.xzy.messagebackups_xmltest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        Button bt_backup = (Button) findViewById(R.id.bt_backup);
        Button bt_restore = (Button) findViewById(R.id.bt_restore);

        bt_backup.setOnClickListener(this);
        bt_restore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_backup:
                if(SmsUtils.backupSms_xmlSerializer(mContext)){
                    Toast.makeText(mContext, "successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "false", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.bt_restore:

                break;
            default:

                break;
        }
    }
}
