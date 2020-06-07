package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    Button btn_store;
    EditText et_name, et_age;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("정보 수정");

        Intent intent = getIntent();
        user_id = intent.getExtras().getString("id");

        et_name = (EditText)findViewById(R.id.et_name);
        et_age = (EditText)findViewById(R.id.et_age);
        btn_store = (Button)findViewById(R.id.btn_store);

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result;
                    String id = user_id;
                    getDB task = new getDB();
                    if (et_name.getText().toString().equals("") || et_age.getText().toString().equals("")) {
                        Toast.makeText(Edit.this, "공백을 채워주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        result = task.execute("edit", id, et_name.getText().toString(), et_age.getText().toString()).get();
                        if (result.equals("success")) {
                            Toast.makeText(Edit.this, "정보를 저장했습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Edit.this, Main_Tap.class);
                            intent.putExtra("id", id);
                            intent.putExtra("tab", "tab3");
                            startActivity(intent);
                        } else if (result.equals("fail")) {
                            Toast.makeText(Edit.this, "정보를 저장하지 못했습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e) {
                    Log.i("DB", "get error");
                }
            }
        });
    }
}
