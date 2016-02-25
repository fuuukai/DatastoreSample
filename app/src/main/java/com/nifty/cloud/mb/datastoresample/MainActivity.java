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

    Intent intent_register;
    Intent intent_reuslt;
    Intent intent;
    static final String INTENT_RESULT = "result";
    static NCMBObject obj = new NCMBObject("TestClass");

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
        intent_register = new Intent(this, RegisterActivity.class);
        //intent_reuslt = new Intent(this,ResultActivity.class);

//        final NCMBObject tameshi = new NCMBObject("SaveObjectTest");
//        tameshi.put("key", 4);
//        tameshi.put("key2", "value5");
//        tameshi.saveInBackground(new DoneCallback() {
//            @Override
//            public void done(NCMBException e) {
//                if (e != null) {
//
//                    //エラー発生時の処理
//                } else {
//                    //id = NCMBException.getObjectId();
//                    //成功時の処理
//                    id = tameshi.getObjectId();
//                    Log.d("id名", "id," + id);
//
//                    //NCMBObject tameshi2 = new NCMBObject("GetObjectTest");
//                    tameshi.setObjectId(id);
//                    tameshi.fetchInBackground(new FetchCallback<NCMBObject>() {
//
//                        @Override
//                        public void done(NCMBObject object, NCMBException e) {
//                            if (e != null) {
//                                //エラー時の処理
//                                Toast.makeText(getApplicationContext(), "エラー", Toast.LENGTH_SHORT).show();
//                            } else {
//                                //取得成功時の処理
//                                String tmp = object.getString("key2");
//                                Log.d("done", "value = " + tmp);
//                                Toast.makeText(getApplicationContext(), "succeeded," + tmp, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * on new object button clicked
     *
     * @param v view
     */
//    public void onNewObjectClicked(View v) {
//
//        String result;
//        try {
//            obj = new NCMBObject("TestClass");
//            obj.put("key", "value");
//            result = createSuccessString(obj);
//            Toast.makeText(this, "空のオブジェクトを生成", Toast.LENGTH_SHORT).show();
//        } catch (NCMBException error) {
//            Toast.makeText(this, "空のオブジェクトを生成", Toast.LENGTH_SHORT).show();
//            result = createFailedString(error);
//        }
//
//        obj.saveInBackground(new DoneCallback() {
//            @Override
//            public void done(NCMBException e) {
//                if (e != null) {
//
//                    //エラー発生時の処理
//                    Toast.makeText(getApplicationContext(), "エラー", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    //成功時の処理
//                    Toast.makeText(getApplicationContext(), "succeeded", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        intent.putExtra(INTENT_RESULT, result);
//
//        intent.putExtra(INTENT_RESULT, result);
//        startActivityForResult(intent, 0);
//    }


    public void onNewObjectClicked(View v) {

        String result;
        startActivity(intent_register);

        try {
            obj = new NCMBObject("TestClass");
            obj.put("key", "value");
            result = createSuccessString(obj);
            Toast.makeText(this, "空のオブジェクトを生成", Toast.LENGTH_SHORT).show();
        } catch (NCMBException error) {
            Toast.makeText(this, "空のオブジェクトを生成", Toast.LENGTH_SHORT).show();
            result = createFailedString(error);
        }

        obj.saveInBackground(new DoneCallback() {
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



        intent.putExtra(INTENT_RESULT, result);

        intent.putExtra(INTENT_RESULT, result);
        startActivityForResult(intent, 0);
    }




    /**
     * on put button clicked
     *
     * @param v view
     */
    public void onPutClicked(View v) throws NCMBException {
        obj.put("Score", 1);
        obj.put("Color", Arrays.asList("red", "blue"));
        Toast.makeText(this, "Score=1,Color=[red,blue]をputしました", Toast.LENGTH_SHORT).show();
    }

    /**
     * on add button clicked
     *
     * @param v view
     */
    public void onAddClicked(View v) throws NCMBException {
        List list = Arrays.asList("white","yellow");
        obj.addToList("Color", list);
        Toast.makeText(this, "Color=[white,yellow]をAddしました", Toast.LENGTH_SHORT).show();
    }

    /**
     * on addUnique button clicked
     *
     * @param v view
     */
    public void onAddUniqueClicked(View v) throws NCMBException {
        List list = Arrays.asList("red", "blue", "white", "yellow","black");
        obj.addUniqueToList("Color", list);
        Toast.makeText(this, "Color=[red,blue,white,yellow,black]をaddUniqueしました", Toast.LENGTH_SHORT).show();
    }

    /**
     * on increment button clicked
     *
     * @param v view
     */
    public void onIncrementClicked(View v) throws NCMBException {
        obj.increment("Score", 1);
        Toast.makeText(this, "Scoreを'+1'Incrementしました", Toast.LENGTH_SHORT).show();
    }

    /**
     * on remove button clicked
     *
     * @param v view
     */
    public void onRemoveClicked(View v) throws NCMBException {
        List list = Arrays.asList("red", "blue", "white", "yellow");
        obj.removeFromList("Color", list);
        Toast.makeText(this, "Color=[red,blue,white,yellow]をremoveしました", Toast.LENGTH_SHORT).show();
    }

    /**
     * on save button clicked
     *
     * @param v view
     */
    public void onSaveClicked(View v) throws NCMBException{
        String result;
        String objectId = obj.getObjectId();
        try {
            obj.save();
            result = createSuccessString(obj);
            if(objectId==null){
                Toast.makeText(this, "新規保存成功 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "更新成功 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception error) {
            if(objectId==null){
                Toast.makeText(this, "新規保存失敗 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "更新失敗 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
            }
            result = createFailedString(new NCMBException(error));
        }

        intent.putExtra(INTENT_RESULT, result);
        startActivityForResult(intent, 0);
    }

    /**
     * on fetch button clicked
     *
     * @param v view
     */
    public void onFetchClicked(View v) {
        String result;
        try {
            obj.fetch();
            result = createSuccessString(obj);
            Toast.makeText(this, "取得成功 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
        } catch (Exception error) {
            Toast.makeText(this, "取得失敗", Toast.LENGTH_SHORT).show();
            result = createFailedString(new NCMBException(error));
        }
        intent.putExtra(INTENT_RESULT, result);
        startActivityForResult(intent, 0);
    }

    /**
     * on delete button clicked
     *
     * @param v view
     */
    public void onDeleteClicked(View v) {
        String result;
        try {
        obj.deleteObject();
            result = createSuccessString(obj);
            Toast.makeText(this, "削除成功 : "+obj.getObjectId(), Toast.LENGTH_SHORT).show();
        } catch (Exception error) {
            Toast.makeText(this, "削除失敗", Toast.LENGTH_SHORT).show();
            result = createFailedString(new NCMBException(error));
        }
        intent.putExtra(INTENT_RESULT, result);
        startActivityForResult(intent, 0);
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
