package com.example.blu_main_test1.main_before;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.blu_main_test1.Main_page.MainActivity;
import com.example.blu_main_test1.Main_page.Main_view_pager;
import com.example.blu_main_test1.R;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class join_main extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "join_main";

    EditText name, year, month, day, phone_number;
    Button man, woman;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_main);

        mAuth = FirebaseAuth.getInstance();

        //성명
        name=(EditText)findViewById(R.id.name);

        //성별
        man=(Button)findViewById(R.id.man);
        woman=(Button)findViewById(R.id.woman);

        //생년월일
        year=(EditText) findViewById(R.id.year);
        month=(EditText) findViewById(R.id.month);
        day=(EditText) findViewById(R.id.day);

        //전화번호
        phone_number=(EditText) findViewById(R.id.phone_number);

        findViewById(R.id.next).setOnClickListener(onClickListener);

        //툴바 기능
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("회원가입");
    }

    /*//뒤로가기 - xml undone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }*/


    //활동 초기화 시 사용자가 현재 로그인되어 있는지 확인함.
    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.next:
                    signUp();

                    break;
            }
        }
    };

    //Sign up new users
    private void signUp() {
        String email = ((EditText)findViewById(R.id.useridid)).getText().toString();
        String password = ((EditText)findViewById(R.id.userpwpw)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.userpwdck)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if(password.equals(passwordCheck)){
              //  final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
            //    loaderLayout.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                  //              loaderLayout.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    //Sign in success, update UI with the signed-in user's information
                                    Log.w(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(join_main.this, "회원가입에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(join_main.this, Main_view_pager.class));
                                    //UI
                                    //updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    //UI
                                    //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    //Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                    if(task.getException() != null){
                                        Toast.makeText(join_main.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }else{
                Toast.makeText(join_main.this, "비밀번호가 일정하기 않습니다.",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(join_main.this, "이메일 또는 비밀번호를 입력해 주세요.",Toast.LENGTH_SHORT).show();

        }
    }

    //보류
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

    /*public void next(View v){
        if(v.getId()==R.id.next){
            //   if(name.getText().toString().equals("")||year.getText().toString().equals("")||month.getText().toString().equals("")||day.getText().toString().equals("")) {
            //    Toast.makeText(getApplicationContext(),"모든 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            // }
            // else {
            //signUp();
            Intent intent = new Intent(join_main.this, Main_view_pager.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            //   }
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        return true;
    }

}