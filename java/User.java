package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class User extends AppCompatActivity {
    TextView tv_id, tv_name, tv_age;
    Button btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setTitle("유저 정보");

        tv_id = (TextView)findViewById(R.id.tv_id);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_age = (TextView)findViewById(R.id.tv_age);
        btn_edit = (Button)findViewById(R.id.btn_store);

        Intent intent = getIntent();
        String user_id = intent.getExtras().getString("id");
        tv_id.setText(user_id);

        try{ // get name
            String result;
            String id = tv_id.getText().toString();
            getDB task = new getDB();
            result = task.execute("name", id, "", "").get();
            tv_name.setText(result);
        } catch (Exception e) {
            Log.i("DB", "get name error");
        }

        try{ // get age
            String result;
            String id = tv_id.getText().toString();
            getDB task = new getDB();
            result = task.execute("age", id, "", "").get();
            tv_age.setText(result);
        } catch (Exception e) {
            Log.i("DB", "get age error");
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this, Edit.class);
                intent.putExtra("id", tv_id.getText().toString());
                startActivity(intent);
            }
        });

    }
}
