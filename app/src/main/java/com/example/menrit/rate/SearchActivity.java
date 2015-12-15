package com.example.menrit.rate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.menrit.rate.model.Post;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    Context context = this;

    ArrayList<Post> results;

    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView lv = (ListView) findViewById(R.id.searchResults);
        results = (ArrayList<Post>) getIntent().getExtras().get("results");

        ArrayList<String> resultNames = new ArrayList<String>();

        for (int i = 0; i < results.size(); i++)
        {
            resultNames.add(results.get(i).getName());
        }
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, resultNames);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toPost = new Intent(context, PostActivity.class);
                toPost.putExtra("name", results.get(position).getName());
                toPost.putExtra("genre", results.get(position).getGenre());
                toPost.putExtra("picture", results.get(position).getPicture());
                startActivity(toPost);
            }
        });
    }

    public void searchGoBack(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
