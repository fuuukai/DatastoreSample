package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FetchCallback;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBBase;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.List;

import javax.xml.transform.Result;

public class RegisterActivity extends AppCompatActivity {


    Intent intent;
    static NCMBObject obj = new NCMBObject("regist_information");
    static final String applicationKey = "3ac4ddad0f5e3d848603a2f47f1816f6bed96b4707d9fa97b217e2d3f64f648b";
    static final String clientKey = "33cbabc27cc9e8ea679639a67ebbd93ace2627538a9e8477166a0cef886d1fc1";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intent = new Intent(this,MainActivity.class);

        //初期化
        NCMB.initialize(this, applicationKey, clientKey);
    }


    //登録ボタン、プレビュー画面へ
    public void register(View v){

        EditText editText1 = (EditText)findViewById(R.id.editText1);
        String team_name = editText1.getText().toString().trim();

        EditText editText2 = (EditText)findViewById(R.id.editText2);
        String facebook = editText2.getText().toString().trim();

        EditText editText3 = (EditText)findViewById(R.id.editText3);
        String e_mail = editText3.getText().toString().trim();

        EditText editText4 = (EditText)findViewById(R.id.editText4);
        String location = editText4.getText().toString().trim();

        EditText editText5 = (EditText)findViewById(R.id.editText5);
        String age = editText5.getText().toString().trim();

        EditText editText6 = (EditText)findViewById(R.id.editText6);
        String sex = editText6.getText().toString().trim();

        EditText editText7 = (EditText)findViewById(R.id.editText7);
        String level = editText7.getText().toString().trim();

        EditText editText8 = (EditText)findViewById(R.id.editText8);
        String training_day = editText8.getText().toString().trim();

        EditText editText9 = (EditText)findViewById(R.id.editText9);
        String team_exp = editText9.getText().toString().trim();

        EditText editText10 = (EditText)findViewById(R.id.editText10);
        String administrater = editText10.getText().toString().trim();


        if(team_name == "" || age =="" || location == ""){
            Toast.makeText(getApplicationContext(), "登録情報を全て入力してください", Toast.LENGTH_SHORT).show();
        }else {
            obj.put("team_name", team_name);
            obj.put("facebook", facebook);
            obj.put("e_mail", e_mail);
            obj.put("location", location);
            obj.put("age", age);
            obj.put("sex", sex);
            obj.put("level", level);
            obj.put("training_day", training_day);
            obj.put("team_exp", team_exp);
            obj.put("administrater", administrater);

            //値をクラウド上に送信
            obj.saveInBackground(new DoneCallback() {
                                     @Override
                                     public void done(NCMBException e) {
                                         if (e != null) {

                                             //エラー発生時の処理
                                             Toast.makeText(getApplicationContext(), "エラー", Toast.LENGTH_SHORT).show();
                                         } else {

                                             //成功時の処理
                                             Toast.makeText(getApplicationContext(), "登録完了", Toast.LENGTH_SHORT).show();

                                             id = obj.getObjectId();
                                             Log.d("id_name", id);

                                             obj.setObjectId(id);

                                             obj.fetchInBackground(new FetchCallback<NCMBObject>() {

                                                 @Override
                                                 public void done(NCMBObject object, NCMBException e) {
                                                     if (e != null) {
                                                         //エラー時の処理
                                                         Toast.makeText(getApplicationContext(), "エラー", Toast.LENGTH_SHORT).show();
                                                     } else {
                                                         //取得成功時の処理
                                                         String tmp = object.getString("team_name");
                                                         Log.d("done", "value = " + tmp);
                                                         Toast.makeText(getApplicationContext(), "succeeded," + tmp, Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             });
                                         }
                                     }
                                 }
            );
            startActivity(intent);
        }
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
