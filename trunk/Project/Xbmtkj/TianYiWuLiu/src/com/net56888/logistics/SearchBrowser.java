package com.net56888.logistics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.InfoEntity;

public class SearchBrowser extends ListActivity {

    private String mCity = "";
    private String mKeywords = "";
    private InfoEntity.Type mType = InfoEntity.Type.All;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_browser);

        View titleBar = findViewById(R.id.title_bar);
        Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
        lbtn.setText(R.string.btn_back);
        lbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
        rbtn.setText(R.string.btn_refresh);
        rbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loadData(SearchBrowser.this.mCity,
                        SearchBrowser.this.mKeywords, SearchBrowser.this.mType);
            }

        });

        TextView tv = (TextView) titleBar.findViewById(R.id.title);
        tv.setText(R.string.title_search);

        Intent intent = getIntent();
        this.mCity = intent.getStringExtra(SearchTab.SEARCH_START_CITY);
        this.mKeywords = intent.getStringExtra(SearchTab.SEARCH_STOP_CITY);
        this.mType = InfoEntity.Type.fromInt(intent.getIntExtra(
                SearchTab.SEARCH_TYPE, 8));

        loadData(this.mCity, this.mKeywords, SearchBrowser.this.mType);

        this.getListView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                    int position, long id) {
                InfoEntity ie = (InfoEntity) adapter
                        .getItemAtPosition(position);

                Intent i = new Intent(SearchBrowser.this, Detail.class);
                i.putExtra("DATA", ie);
                startActivity(i);
            }

        });
    }

    public void loadData(String city, String keywords, InfoEntity.Type type) {
        setListAdapter(new BrowserAdapter(this, city, keywords, type));
    }

}
