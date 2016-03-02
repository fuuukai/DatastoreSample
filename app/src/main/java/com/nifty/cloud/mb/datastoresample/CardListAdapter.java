package com.nifty.cloud.mb.datastoresample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fukai on 16/02/29.
 */
public class CardListAdapter extends ArrayAdapter<PackageInfo>{

    LayoutInflater mInflater;
    PackageManager packageManager;

    public CardListAdapter(Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_list_item_card, parent, false);
        }


        PackageInfo info = getItem(position);

        TextView tv1 = (TextView) convertView.findViewById(R.id.title);
        tv1.setText(info.applicationInfo.loadLabel(packageManager));
        Log.d("packagename", info.applicationInfo.loadLabel(packageManager).toString());

        TextView tv2 = (TextView) convertView.findViewById(R.id.sub);
        tv2.setText(info.packageName + "\n" + "versionName : " + info.versionName + "\nversionCode : " + info.versionCode);
        Log.d("packagename", info.packageName + "\n" + "versionName : " + info.versionName + "\nversionCode : " + info.versionCode);

        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        iv.setImageDrawable(info.applicationInfo.loadIcon(packageManager));


        return convertView;
    }

}
