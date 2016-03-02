package com.nifty.cloud.mb.datastoresample;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        String age = editText2.getText().toString().trim();

        EditText editText3 = (EditText)findViewById(R.id.editText3);
        String locate = editText3.getText().toString().trim();

        Log.d("show", team_name + "," + age + "," + locate);

        if(team_name == "" || age =="" || locate == ""){
            Toast.makeText(getApplicationContext(), "登録情報を全て入力してください", Toast.LENGTH_SHORT).show();
        }else {
            obj.put("team_name", team_name);
            obj.put("age", age);
            obj.put("location", locate);

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



//    // クエリ、検索ボタン
//    public void query(View v){
//        //QueryTestを検索するクラスを作成
//        NCMBQuery<NCMBObject> query = new NCMBQuery<> ("regist_information");
//        query.whereEqualTo("team_name", "ふかい");
//
//        query.findInBackground(new FindCallback<NCMBObject>() {
//            @Override
//            public void done(List<NCMBObject> results, NCMBException e) {
//                if (e != null) {
//                    Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();
//
//                } else {
//                    int a = results.size();
//                    Log.d("tmp", results.toString());
//                    Toast.makeText(getApplicationContext(), results.toString(), Toast.LENGTH_SHORT).show();
//
//                    String i = results.get(1).toString();
//                    Log.d("二つ目", i);
//                }
//            }
//        });
//
//    }
}
