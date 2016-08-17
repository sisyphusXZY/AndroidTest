package com.example.xzy.listview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ListView lv_tiger1 = (ListView) findViewById(R.id.lv_tiger1);
        ListView lv_tiger2 = (ListView) findViewById(R.id.lv_tiger2);
        ListView lv_tiger3 = (ListView) findViewById(R.id.lv_tiger3);

        TigerAdapter tigerAdapter = new TigerAdapter();

        lv_tiger1.setAdapter(tigerAdapter);
        lv_tiger2.setAdapter(tigerAdapter);
        lv_tiger3.setAdapter(tigerAdapter);

    }

    class TigerAdapter extends BaseAdapter{

        private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            TextView view = null;
//复用已用过的TextView，减少内存占用.常用优化手段。
            if(convertView != null){
                view = (TextView) convertView;
            }else{
                view = new TextView(mContext);
            }
//
//            TextView textView = new TextView(mContext);

            Random random = new Random();
            int number = random.nextInt(100);
            if(number < 20){
                view.setTextColor(Color.parseColor("#ff00ff"));
                view.setText("桃");
            }else if (number < 40){
                view.setTextColor(Color.parseColor("#ff00ff"));
                view.setText("梨");
            }else if (number < 60){
                view.setTextColor(Color.parseColor("#ff00ff"));
                view.setText("瓜");
            }else if (number < 80){
                view.setTextColor(Color.parseColor("#ff00ff"));
                view.setText("果");
            }else{
                view.setTextColor(Color.parseColor("#ff00ff"));
                view.setText("蕉");
            }
//            view.setText("i"+i);
            view.setTextSize(58);
//            map.put(view.hashCode(),1);
//            System.out.println("have"+map.size());

            return view;
        }
    }
}
