package com.example.menrit.rate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.menrit.rate.model.*;
import com.example.menrit.rate.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static Button button_search;
    private static Button button_Post;

    Context context = this;

//    ArrayAdapter<Post> postsAdapter;

    static ListAdapter postsAdapter;

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

        ListView all_posts = (ListView) findViewById(R.id.postsList);
//        postsAdapter = new ArrayAdapter<Post>(this, android.R.layout.simple_expandable_list_item_1, posts);

//        startProgress();
        ApiRouter.withoutToken().getPosts(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                System.out.println("-------------");
                System.out.println(posts.get(0).getName());
                System.out.println(getPostNames(posts));
                System.out.println("-------------");
//                postsAdapter.addAll(posts);
//                stopProgress();
//                postsAdapter.addAll(posts);
                postsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, getPostNames(posts));
            }


            @Override
            public void failure(RetrofitError e) {
                //displayError(e);
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println("=========== post adapter is "+(postsAdapter == null));
        all_posts.setAdapter(postsAdapter);
    }
    public static ArrayList<String> getPostNames(List<Post> theList)
    {
        ArrayList<String> toReturn = new ArrayList<String>();

        for (int i = 0; i < theList.size(); i++)
        {
            toReturn.add(theList.get(i).getName());
        }

        return toReturn;
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
