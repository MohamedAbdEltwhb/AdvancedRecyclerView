package com.example.mm.advancedrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler_vw = (RecyclerView)findViewById(R.id.recycler_vw);
        ArrayList<RecycleBen> arrayList = new ArrayList<>();

        for (int i = 0;i<=25;i++) {
            RecycleBen bean = new RecycleBen();

            if(i%2==0) {
                bean.setType_row("1");
                bean.setName("First element");
                bean.setImage_url("http://www.androhub.com/wp-content/uploads/2015/09/staggeredrecyclerview_banner.jpg");

            } else if(i%3==0) {
                bean.setType_row("2");
                bean.setName("Second element");
                bean.setImage_url("http://i.stack.imgur.com/snB84.png");

            } else {
                bean.setType_row("3");
                bean.setName("Third element");
                bean.setImage_url("http://inducesmile.com/wp-content/uploads/2015/05/gridbanner.jpg");
            }

            arrayList.add(bean);

        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycler_vw.setLayoutManager(mLayoutManager);
        recycler_vw.setItemAnimator(new DefaultItemAnimator());
        recycler_vw.setAdapter(new RecycleDataAdapter(this, arrayList));

    }
}
