package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;

import org.json.JSONException;

import javax.xml.transform.Result;

public class RegisterActivity extends AppCompatActivity {


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intent = new Intent(this,MainActivity.class);
    }


    //プレビュー画面へ
    public void register(View v){

        EditText editText1 = (EditText)findViewById(R.id.editText1);
        String team_name = editText1.getText().toString().trim();

        EditText editText2 = (EditText)findViewById(R.id.editText2);
        String age = editText2.getText().toString().trim();

        EditText editText3 = (EditText)findViewById(R.id.editText3);
        String locate = editText3.getText().toString().trim();

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

//        intent.putExtra("チーム名", team_name);
//        intent.putExtra("年齢", age);
//        intent.putExtra("地域", locate);
        startActivity(intent);
    }


}
