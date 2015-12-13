package com.example.menrit.rate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static Button button_search;
    private static Button button_Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        OnClickButtonSearch();
        OnClickButtonPost();
    }

    public void OnClickButtonSearch() {

        button_search = (Button) findViewById(R.id.searchbtn);
        button_search.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.menrit.rate.PostActivity");
                        startActivity(intent);
                    }

                }
        );
}
    public void OnClickButtonPost(){

        button_Post=(Button)findViewById(R.id.postbtn);
        button_Post.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent=new Intent("com.example.menrit.rate.PostActivity");
                        startActivity(intent);
                    }

                }
        );
}

    public void viewFollow(View view)
    {
        startActivity(new Intent(this, FollowActivity.class));
    }


}
