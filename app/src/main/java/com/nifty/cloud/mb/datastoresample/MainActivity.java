package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FetchCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Intent intent;
    Intent intent_register_page;

    static final String INTENT_RESULT = "result";
    static NCMBObject obj = new NCMBObject("regist_information");

    static final String applicationKey = "3ac4ddad0f5e3d848603a2f47f1816f6bed96b4707d9fa97b217e2d3f64f648b";
    static final String clientKey = "33cbabc27cc9e8ea679639a67ebbd93ace2627538a9e8477166a0cef886d1fc1";


    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初期化
        NCMB.initialize(this, applicationKey, clientKey);

        intent = new Intent(this,ResultActivity.class);
        intent_register_page = new Intent(this, RegisterActivity.class);
    }


    public void onNewObjectClicked(View v) {

        startActivity(intent_register_page);

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

