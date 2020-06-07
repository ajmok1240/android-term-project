package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main_Tap extends AppCompatActivity {
    TextView tv_id, tv_name, tv_age;
    ListView list;
    Button btn_edit;
    String user_id;
    EditText et_search;
    ArrayList<info> infoList;
    List<info> searchList;
    ListView my_comment_list;
    List<MyComment> MyCommentList;
    String[] MySplitComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__tap);

        final TabHost host = (TabHost) findViewById(R.id.host);

        ////////////// setting tab //////////////
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_age = (TextView) findViewById(R.id.tv_age);
        btn_edit = (Button) findViewById(R.id.btn_store);

        Intent intent = getIntent();
        user_id = intent.getExtras().getString("id");
        tv_id.setText("아이디 : " + user_id);

        try { // get name
            String result;
            String id = user_id;
            getDB task = new getDB();
            result = task.execute("name", id, "", "").get();
            tv_name.setText("이름 : " + result);
        } catch (Exception e) {
            Log.i("DB", "get name error");
        }

        try { // get age
            String result;
            String id = user_id;
            getDB task = new getDB();
            result = task.execute("age", id, "", "").get();
            tv_age.setText("나이 : " + result);
        } catch (Exception e) {
            Log.i("DB", "get age error");
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Tap.this, Edit.class);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
        });

        ////////////// setting tab //////////////

        ////////////// list tab //////////////

        et_search = (EditText)findViewById(R.id.search);
        et_search.setText(null);
        list = (ListView) findViewById(R.id.list);

        searchList = new ArrayList<info>();
        InitializeData();

        infoList = new ArrayList<info>();
        infoList.addAll(searchList);

        final adapter myAdapter = new adapter(this, searchList);

        list.setAdapter(myAdapter);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et_search.getText().toString();
                ListSearch(text, myAdapter);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Main_Tap.this, mountain.class);
                intent.putExtra("name", myAdapter.getItem(position).getName());
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
        });

        ////////////// list tab //////////////


        ////////////// my comment list tab //////////////

        my_comment_list = (ListView)findViewById(R.id.my_comment_list);

        try { // 댓글 가져오기
            String result;
            String id = user_id;
            getDB task = new getDB();
            result = task.execute("my_comment", id, "", "").get();
            MySplitComment = result.split("ஹ");
        } catch (Exception e) {
            Log.i("DB", "get comment error");
        }

        MyCommentList = new ArrayList<MyComment>();

        //댓글 정보를 담은 SplitComment 배열이 비어있지 않다면, 리스트뷰에 댓글 정보 add
        if(!(MySplitComment[0].equals(""))) {
            for(int i=0;i<MySplitComment.length;i+=3) {
                MyCommentList.add(new MyComment(MySplitComment[i], MySplitComment[i+1], MySplitComment[i+2]));
            }
        }

        final MyCommentAdapter MyCommentAdapter = new MyCommentAdapter(this, MyCommentList);

        my_comment_list.setAdapter(MyCommentAdapter);

        my_comment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        MyCommentAdapter.getItem(position).getMountain(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Main_Tap.this, mountain.class);
                intent.putExtra("name", MyCommentAdapter.getItem(position).getMountain());
                startActivity(intent);
            }
        });

        my_comment_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String mountain = MyCommentAdapter.getItem(position).getMountain();
                String user_id = MyCommentAdapter.getItem(position).getName();
                String content = MyCommentAdapter.getItem(position).getContent();

                try { // 댓글 삭제하기
                    String result;
                    getDB task = new getDB();
                    result = task.execute("delete_comment", user_id, mountain, content).get();
                    if(result.equals("success")) {
                        Intent intent = getIntent();
                        intent.putExtra("tab", "tab2");
                        finish();
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.i("DB", "get comment error");
                }
                return true;
            }
        });

        ////////////// my comment list tab //////////////


        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator("검색");
        spec.setContent(R.id.tab_list);
        host.addTab(spec);

        TabHost.TabSpec spec2 = host.newTabSpec("tab2");
        spec2.setIndicator("내 댓글");
        spec2.setContent(R.id.tab_content2);
        host.addTab(spec2);

        TabHost.TabSpec spec3 = host.newTabSpec("tab3");
        spec3.setIndicator("내 정보");
        spec3.setContent(R.id.tab_setting);
        host.addTab(spec3);

        String current_tab = intent.getExtras().getString("tab");
        host.setCurrentTabByTag(current_tab);

        host.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("tab", "tab2");
                finish();
                startActivity(intent);
            }
        });

    }

    public void ListSearch(String key, adapter ad) {
        searchList.clear();

        if(key.length() == 0) {
            searchList.addAll(infoList);
        }
        else {
            for(int i=0; i<infoList.size(); i++)
            {
                if(infoList.get(i).getName().toLowerCase().contains(key)) {
                    searchList.add(infoList.get(i));
                }
            }
        }
        ad.notifyDataSetChanged();
    }

    public void InitializeData() {
        searchList.add(new info("금정산", "경남 양산시 동면 가산리 산1-1"));
        searchList.add(new info("설악산", "강원 인제군 북면 한계리"));
        searchList.add(new info("소백산", "충북 단양군 가곡면 어의곡리"));
        searchList.add(new info("지리산", "경남 함양군 마천면 추성리"));
        searchList.add(new info("팔공산", "경북 군위군 부계면 동산리"));
        searchList.add(new info("한라산", "제주 서귀포시 상효동 산220-1"));
    }
}