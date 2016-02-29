package com.nifty.cloud.mb.datastoresample;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class TeamList extends AppCompatActivity {

    ListView listView;
    Bundle addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);

        CardListAdapter adapter = new CardListAdapter(getApplicationContext());

        if (packageInfoList != null) {
            for (PackageInfo info : packageInfoList) {
                adapter.add(info);
            }
        }

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        ListView listView = (ListView) findViewById(R.id.team_list);
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

}
