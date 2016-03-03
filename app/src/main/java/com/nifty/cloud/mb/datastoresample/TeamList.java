package com.nifty.cloud.mb.datastoresample;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.nifty.cloud.mb.core.FetchCallback;
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
import java.util.Objects;

public class TeamList extends AppCompatActivity{

    static final String applicationKey = "3ac4ddad0f5e3d848603a2f47f1816f6bed96b4707d9fa97b217e2d3f64f648b";
    static final String clientKey = "33cbabc27cc9e8ea679639a67ebbd93ace2627538a9e8477166a0cef886d1fc1";


    //List<Map<String, String>> listData = new ArrayList<Map<String, String>>();

    String tmp;

    int add_list_length;

    String id_name [] = new String[100];

    String team_name, location, sex, level;

    Handler h;

    TeamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        //初期化
        NCMB.initialize(this, applicationKey, clientKey);

        ListView listView = (ListView) findViewById(R.id.team_list);

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View header = inflater.inflate(R.layout.list_header_footer, listView, false);
        View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        adapter = new TeamListAdapter(this);
        listView.setAdapter(adapter);

        h = new Handler();

        getListData();
    }

    //adapterにチーム情報を追加
    private void getListData() {

        final NCMBQuery<NCMBObject> query = new NCMBQuery<> ("regist_information");

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(final List<NCMBObject> results, final NCMBException e) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                        } else {
                            add_list_length = results.size();

                            for (int i = 0; i < results.size(); i++) {

                                id_name[i] = results.get(i).getObjectId();

                                final int index = i;
                                RegisterActivity.obj.setObjectId(id_name[index]);
                                RegisterActivity.obj.fetchInBackground(new FetchCallback<NCMBObject>() {
                                    @Override
                                    public void done(NCMBObject object, NCMBException e) {
                                        if (e != null) {
                                            //エラー時の処理
                                        } else {

                                            //取得成功時の処理
                                            team_name = RegisterActivity.obj.getString("team_name");
                                            location = RegisterActivity.obj.getString("location");
                                            sex = RegisterActivity.obj.getString("sex");
                                            level = RegisterActivity.obj.getString("team_exp");
                                            adapter.add(new Team(team_name, location, sex,level));
                                            Log.d("name", id_name[index]);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
            }
        });
    }


    //QueryTestを検索するクラスを作成
    public void query(View v){

        NCMBQuery<NCMBObject> query = new NCMBQuery<> ("regist_information");

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(final List<NCMBObject> results, final NCMBException e) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d("tmp", results.toString());
                            Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < results.size(); ++i) {
                                Log.d("name", results.get(i).getObjectId().toString());
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
