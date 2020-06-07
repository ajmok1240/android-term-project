package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button btn_register;
    EditText et_id, et_pass1, et_pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("회원가입");

        btn_register = (Button)findViewById(R.id.btn_register2);
        et_id = (EditText)findViewById(R.id.register_id);
        et_pass1 = (EditText)findViewById(R.id.register_pass1);
        et_pass2 = (EditText)findViewById(R.id.register_pass2);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result;
                    String id = et_id.getText().toString();
                    String pass1 = et_pass1.getText().toString();
                    String pass2 = et_pass2.getText().toString();

                    connectDB task = new connectDB();

                    if(id.equals("") || pass1.equals("") || pass2.equals("")) {
                        Toast.makeText(Register.this,"공백을 채워주세요",Toast.LENGTH_SHORT).show();
                    }else {
                        if (pass1.equals(pass2)) {
                            result = task.execute(id, pass1, "register").get();
                            if (result.equals("exist")) {
                                Toast.makeText(Register.this, "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                            } else if (result.equals("success")){
                                Toast.makeText(Register.this, "가입에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(Register.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.i("DB", "register error");
                }
            }
        });
    }
}
