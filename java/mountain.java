package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class mountain extends AppCompatActivity {
    TextView mt_name;
    TextView mt_time;
    TextView mt_level;
    ImageView mt_img;
    EditText et_comment;
    Button btn_comment;
    ListView comment;
    String url, eng_name, name, user_id;
    List<Comment> CommentList;
    String[] SplitComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        user_id = intent.getExtras().getString("id");

        mt_name = (TextView)findViewById(R.id.tv_mt_name);
        mt_time = (TextView)findViewById(R.id.tv_mt_time);
        mt_level = (TextView)findViewById(R.id.tv_mt_level);
        mt_img = (ImageView)findViewById(R.id.img_mt);
        comment = (ListView)findViewById(R.id.comment_list);
        et_comment = (EditText)findViewById(R.id.et_comment);
        btn_comment = (Button)findViewById(R.id.btn_comment);

        if(name.equals("금정산") || name.equals("Geumjeong")) {
            String imageUrl = "http://tour.busan.go.kr/upload_data/manage_img//137083506639101.bmp";
            Glide.with(this).load(imageUrl).into(mt_img);

            eng_name = "Geumjeong";
        }else if(name.equals("설악산") || name.equals("Seolark")) {
            String imageUrl = "http://www.knps.or.kr/upload/contest/12/thumb_20140702014224112.jpg";
            Glide.with(this).load(imageUrl).into(mt_img);
            url = "http://www.knps.or.kr/front/portal/visit/visitCourseSubMain.do?parkId=120400&parkNavGb=guide&menuNo=7020093";
            eng_name = "Seolark";
        }else if(name.equals("소백산") || name.equals("Sobaek")) {
            String imageUrl = "http://www.knps.or.kr/upload/contest/6/thumb_20070309161147.jpg";
            Glide.with(this).load(imageUrl).into(mt_img);
            url = "http://www.knps.or.kr/front/portal/visit/visitCourseSubMain.do?parkId=121600&parkNavGb=guide&menuNo=7020094";
            eng_name = "Sobaek";
        }else if(name.equals("지리산") || name.equals("Jiri")) {
            String imageUrl = "http://www.knps.or.kr/upload/contest/18/thumb_20191028041106080.jpg";
            Glide.with(this).load(imageUrl).into(mt_img);
            url = "http://www.knps.or.kr/front/portal/visit/visitCourseSubMain.do?parkId=120100&parkNavGb=guide&menuNo=7020100";
            eng_name = "Jiri";
        }else if(name.equals("팔공산") || name.equals("Palgong")) {
            String imageUrl = "data:image/jpeg;base64,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";
            Glide.with(this).load(imageUrl).into(mt_img);
            url = "http://www.daegu.go.kr/cts/index.do?menu_id=00000919";
            eng_name = "Palgong";
        }else if(name.equals("한라산") || name.equals("Hanlla")) {
            String imageUrl = "http://www.knps.or.kr/upload/contest/16/thumb_20171210044722852.jpg";
            Glide.with(this).load(imageUrl).into(mt_img);
            url = "http://www.jeju.go.kr/hallasan/info/info/info.htm";
            eng_name = "Hanlla";
        }

        //이미지 클릭 시 설명 페이지로 이동
        mt_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        mt_name.setText(name);

        try { // 소요 시간 가져오기
            String result;
            String mt = eng_name;
            getDB task = new getDB();
            result = task.execute("mt_time", mt, "", "").get();
            mt_time.setText("소요시간 : " + result + "시간");
        } catch (Exception e) {
            Log.i("DB", "get mt_time error");
        }

        try { // 난이도 가져오기
            String result;
            String mt = eng_name;
            getDB task = new getDB();
            result = task.execute("mt_level", mt, "", "").get();
            mt_level.setText("난이도 : " + result);
        } catch (Exception e) {
            Log.i("DB", "get mt_level error");
        }

        try { // 댓글 가져오기
            String result;
            String mt = eng_name;
            getDB task = new getDB();
            result = task.execute("comment", mt, "", "").get();
            SplitComment = result.split("ஹ");
        } catch (Exception e) {
            Log.i("DB", "get comment error");
        }

        CommentList = new ArrayList<Comment>();

        //댓글 정보를 담은 SplitComment 배열이 비어있지 않다면, 리스트뷰에 댓글 정보 add
        if(!(SplitComment[0].equals(""))) {
            for(int i=0;i<SplitComment.length;i+=2) {
                CommentList.add(new Comment(SplitComment[i], SplitComment[i+1]));
            }
        }

        final CommentAdapter myAdapter = new CommentAdapter(this, CommentList);

        comment.setAdapter(myAdapter);

        // 댓글 작성
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {// 댓글 등록
                    String result;
                    String mt = eng_name;
                    String user = user_id;
                    String content = et_comment.getText().toString();
                    getDB task = new getDB();
                    result = task.execute("insert_comment", user, mt, content).get();

                    if(result.equals("success")) {
                        Toast.makeText(mountain.this, "댓글 등록 성공", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mountain.this, "댓글 등록 실패", Toast.LENGTH_SHORT).show();
                    }
                    //댓글 단 후, 새로고침
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } catch (Exception e) {
                    Log.i("DB", "get insert comment error");
                }
            }
        });
    }
}
