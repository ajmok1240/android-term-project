package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_register;
    EditText et_id, et_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("로그인");

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register1);
        et_id = (EditText)findViewById(R.id.login_id);
        et_pw = (EditText)findViewById(R.id.login_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String result;
                    String id = et_id.getText().toString();
                    String pw = et_pw.getText().toString();

                    connectDB task = new connectDB();
                    result = task.execute(id, pw, "login").get();
                    if(result.equals("true")) {
                        Toast.makeText(MainActivity.this,"로그인에 성공하였습니다",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main_Tap.class);
                        intent.putExtra("id", id);
                        intent.putExtra("tab", "tab3");
                        startActivity(intent);
                        finish();
                    } else if(result.equals("false")) {
                        Toast.makeText(MainActivity.this,"아이디 또는 비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                        et_id.setText("");
                        et_pw.setText("");
                    }
                } catch (Exception e) {
                    Log.i("DB", "login error");
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
