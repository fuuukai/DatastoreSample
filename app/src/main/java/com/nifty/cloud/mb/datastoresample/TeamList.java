package com.nifty.cloud.mb.datastoresample;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBInstallation;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamList extends AppCompatActivity{

    static final String applicationKey = "3ac4ddad0f5e3d848603a2f47f1816f6bed96b4707d9fa97b217e2d3f64f648b";
    static final String clientKey = "33cbabc27cc9e8ea679639a67ebbd93ace2627538a9e8477166a0cef886d1fc1";

    String id_name [] = new String[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        //初期化
        NCMB.initialize(this, applicationKey, clientKey);

        ListView listView = (ListView) findViewById(R.id.team_list);
        SimpleAdapter adapter = new SimpleAdapter(this, getListData(), R.layout.list,
                new String[] { "no", "name" }, new int[] { R.id.no, R.id.name });

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View header = inflater.inflate(R.layout.list_header_footer, listView, false);
        View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        listView.setAdapter(adapter);

    }

    private List<Map<String, String>> getListData() {

        final List<Map<String, String>> listData = new ArrayList<Map<String, String>>();

        listData.add(getMapData(new String[][]{{"no", "01"}, {"name", "あいうえお"}}));
        listData.add(getMapData(new String[][]{{"no", "02"}, {"name", "かきくけこ"}}));
        listData.add(getMapData(new String[][]{{"no", "03"}, {"name", "さしすせそ"}}));
        listData.add(getMapData(new String[][]{{"no", "04"}, {"name", "たちつてと"}}));
        listData.add(getMapData(new String[][]{{"no", "05"}, {"name", "なにぬねの"}}));
        listData.add(getMapData(new String[][]{{"no", "06"}, {"name", "はひふへほ"}}));
        listData.add(getMapData(new String[][]{{"no", "07"}, {"name", "まみむめも"}}));
        listData.add(getMapData(new String[][]{{"no", "08"}, {"name", "や　ゆ　よ"}}));
        listData.add(getMapData(new String[][]{{"no", "09"}, {"name", "らりるれろ"}}));
        listData.add(getMapData(new String[][]{{"no", "10"}, {"name", "わをん　　"}}));


        NCMBQuery<NCMBObject> query = new NCMBQuery<> ("regist_information");

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> results, NCMBException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                } else {
                    int a = results.size();
                    Log.d("tmp", results.toString());
                    Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();

                    for ( int i = 0; i<results.size(); ++i ) {
                        id_name[i] = results.get(i).getObjectId().toString();
                        listData.add(getMapData(new String[][]{{"no", id_name[i]}, {"name", "あいうえお"}}));

                        Log.d("name", results.get(i).getObjectId().toString());
                    }
                }
            }
        });

        return listData;
    }

    private Map<String, String> getMapData(String[][] values) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < values.length; i++) {
            map.put(values[i][0], values[i][1]);
        }

        return map;
    }


    public void query(View v){
        //QueryTestを検索するクラスを作成
        NCMBQuery<NCMBObject> query = new NCMBQuery<> ("regist_information");
        query.whereEqualTo("team_name", "ふかい");

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> results, NCMBException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();

                } else {
                    int a = results.size();
                    Log.d("tmp", results.toString());
                    Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();

                    for ( int i = 0; i<results.size(); ++i ) {
                        Log.d("name", results.get(i).getObjectId().toString());
                    }
                }
            }
        });
    }

}
