package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;

import org.json.JSONException;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    Intent ok;
    String team_name;
    String age;
    String locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        team_name = intent.getStringExtra("チーム名");

        Intent intent2 = getIntent();
        age = intent2.getStringExtra("年齢");

        Intent intent3 = getIntent();
        locate = intent3.getStringExtra("地域");

        TextView resultView = (TextView)findViewById(R.id.result);
        resultView.setText("チーム名: " + team_name + "\n年齢: " + age + "\n地域: " + locate);

        ok = new Intent(this, MainActivity.class);

        Log.d("登録情報 ", team_name + "\n" + age + "\n" +locate);
    }

    //okボタンを押した時の動作
    public void confirm(View v){

        startActivity(ok);

        String result;

        MainActivity.obj.put("team_name", team_name);
        MainActivity.obj.put("age", age);
        MainActivity.obj.put("location", locate);

        //値をクラウド上に送信
        MainActivity.obj.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {

                    //エラー発生時の処理
                    Toast.makeText(getApplicationContext(), "エラー", Toast.LENGTH_SHORT).show();
                } else {

                    //成功時の処理
                    Toast.makeText(getApplicationContext(), "succeeded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    String createSuccessString(NCMBObject obj) throws NCMBException {
        String successString = "【Success】\n";
        successString += "ID : " + obj.getObjectId() + "\n";
        successString += "ClassName : " + obj.getClassName() + "\n";
        successString += "Color : " + obj.getString("Color") + "\n";
        try {
            successString += "ACL : " + obj.getAcl().toJson() + "\n";
        } catch (JSONException | NullPointerException error) {
            successString += "ACL : " + null + "\n";
        }
        successString += "Score : " + obj.getString("Score") + "\n";
        successString += "CreateDate : " + obj.getCreateDate() + "\n";
        successString += "UpdateDate : " + obj.getUpdateDate() + "\n";
        return successString;
    }

    String createFailedString(NCMBException error) {
        String errorString = "【Failed】\n";
        if(error.getCode()!=null){
            errorString += "StatusCode : " + error.getCode() + "\n";
        }
        if(error.getMessage() !=null){
            errorString += "Message : " + error.getMessage() + "\n";
        }

        return errorString;
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
