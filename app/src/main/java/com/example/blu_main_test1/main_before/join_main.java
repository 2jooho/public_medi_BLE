package com.example.blu_main_test1.main_before;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blu_main_test1.Main_page.Main_view_pager;
import com.example.blu_main_test1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class join_main extends AppCompatActivity {
    EditText name,year, month, day;
    Button man, woman,sk,kt,lg,chip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_main);

        name=(EditText)findViewById(R.id.name);
        man=(Button)findViewById(R.id.man);
        woman=(Button)findViewById(R.id.woman);
        year=(EditText) findViewById(R.id.year);
        month=(EditText) findViewById(R.id.month);
        day=(EditText) findViewById(R.id.day);
        sk=(Button)findViewById(R.id.sk);
        kt=(Button)findViewById(R.id.kt);
        lg=(Button)findViewById(R.id.lg);
        chip=(Button)findViewById(R.id.chip);

        //툴바 기능
        Toolbar toolbar;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setTitle("회원가입");


    }

//사용자 개인정보를 서버에 보냄
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://~~.jsp");//보낼 jsp 주소를 ""안에 작성합니다.
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1]+"&type="+strings[2];;//보낼 정보인데요. GET방식으로 작성합니다. ex) "id=rain483&pwd=1234";
                //회원가입처럼 보낼 데이터가 여러 개일 경우 &로 구분하여 작성합니다.
                osw.write(sendMsg);//OutputStreamWriter에 담아 전송합니다.
                osw.flush();
                //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    //jsp에서 보낸 값을 받겠죠?
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                    // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //jsp로부터 받은 리턴 값입니다.
            return receiveMsg;
        }
    }


    @SuppressLint("ResourceType")
    public void sex(View v){
        switch (v.getId()){
            case R.id.man :
                man.setBackgroundResource(R.xml.join_main_sex);
                man.setTextColor(R.color.white);
                woman.setBackgroundResource(R.xml.join_main_sex2);
                woman.setTextColor(Color.parseColor("#a606234E"));
                break;
            case R.id.woman :
                man.setBackgroundResource(R.xml.join_main_sex2);
                man.setTextColor(Color.parseColor("#a606234E"));
                woman.setBackgroundResource(R.xml.join_main_sex);
                woman.setTextColor(R.color.white);
        }
    }

    @SuppressLint("ResourceType")
    public void tel(View v){
        switch (v.getId()){
            case R.id.sk :
                sk.setBackgroundResource(R.xml.join_main_sex);
                sk.setTextColor(Color.parseColor("#ffffff"));
                kt.setBackgroundColor(Color.parseColor("#00ffffff"));
                kt.setTextColor(Color.parseColor("#06234E"));
                lg.setBackgroundColor(Color.parseColor("#00ffffff"));
                lg.setTextColor(Color.parseColor("#06234E"));
                chip.setBackgroundColor(Color.parseColor("#00ffffff"));
                chip.setTextColor(Color.parseColor("#06234E"));
                break;

            case R.id.kt :
                sk.setBackgroundColor(Color.parseColor("#00ffffff"));
                sk.setTextColor(Color.parseColor("#06234E"));
                kt.setBackgroundResource(R.xml.join_main_sex);
                kt.setTextColor(Color.parseColor("#ffffff"));
                lg.setBackgroundColor(Color.parseColor("#00ffffff"));
                lg.setTextColor(Color.parseColor("#06234E"));
                chip.setBackgroundColor(Color.parseColor("#00ffffff"));
                chip.setTextColor(Color.parseColor("#06234E"));
                break;

            case R.id.lg :
                sk.setBackgroundColor(Color.parseColor("#00ffffff"));
                sk.setTextColor(Color.parseColor("#06234E"));
                kt.setBackgroundColor(Color.parseColor("#00ffffff"));
                kt.setTextColor(Color.parseColor("#06234E"));
                lg.setBackgroundResource(R.xml.join_main_sex);
                lg.setTextColor(Color.parseColor("#ffffff"));
                chip.setBackgroundColor(Color.parseColor("#00ffffff"));
                chip.setTextColor(Color.parseColor("#06234E"));
                break;

            case R.id.chip :
                sk.setBackgroundColor(Color.parseColor("#00ffffff"));
                sk.setTextColor(Color.parseColor("#06234E"));
                kt.setBackgroundColor(Color.parseColor("#00ffffff"));
                kt.setTextColor(Color.parseColor("#06234E"));
                lg.setBackgroundColor(Color.parseColor("#00ffffff"));
                lg.setTextColor(Color.parseColor("#06234E"));
                chip.setBackgroundResource(R.xml.join_main_sex);
                chip.setTextColor(Color.parseColor("#ffffff"));

                break;
        }
    }

    public void next(View v){
        if(v.getId()==R.id.next){
     //   if(name.getText().toString().equals("")||year.getText().toString().equals("")||month.getText().toString().equals("")||day.getText().toString().equals("")) {
     //    Toast.makeText(getApplicationContext(),"모든 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
       // }
       // else {
            Intent intent = new Intent(join_main.this, Main_view_pager.class);
            startActivity(intent);
            finish();
     //   }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        return true;
    }

}
