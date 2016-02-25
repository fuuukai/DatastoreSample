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

        intent = new Intent(this,ResultActivity.class);
    }


    public void register(View v){

        EditText editText1 = (EditText)findViewById(R.id.editText1);
        String team_name = editText1.getText().toString().trim();

//        EditText regist_info [] = new EditText[3];
//
//        String regist_info[] ={
//        };

        //Log.d("登録情報", "登録情報", regist_info[1]);

        intent.putExtra("登録情報", team_name);
        startActivity(intent);
    }


}
