package com.jinpo.baserecycleradapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_main)
    RecyclerView recyclerView;

    private DemoListAdapter demoListAdapter;
    private List<String> demoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        demoListAdapter=new DemoListAdapter(this,null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(demoListAdapter);
    }


    private void initData() {
        demoList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoList.add("这是列表第"+(i+1)+"项！");
        }
        demoListAdapter.setmData(demoList);
        Log.d("MainActivity","1.1.2的修改");
    }
}
