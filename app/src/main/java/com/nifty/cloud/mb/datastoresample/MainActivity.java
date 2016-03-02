package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

    Intent intent_register_page;
    Intent intent_team_list;

    static final String INTENT_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        intent_register_page = new Intent(this, RegisterActivity.class);
        intent_team_list = new Intent(this, TeamList.class);


        //imageView = (ImageView)findViewById(getResources().getIdentifier("imageView", "id", getPackageName()));

    }


    public void onNewObjectClicked(View v) {

        startActivity(intent_register_page);

    }

    public void onTeamListClicked(View v){

        startActivity(intent_team_list);

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

