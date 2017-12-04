package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ListView list = (ListView) findViewById(R.id.category_list);
        List list1 = new ArrayList();
        list1.add(0,"last news");
        list1.add(1,"sport");
        list1.add(2,"politics");
    }
}
