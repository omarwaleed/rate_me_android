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
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

    ListAdapter postsAdapter;
    List<Post> retrieved = new ArrayList<Post>();
    ListView all_posts;
    TextView searchText;

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

        searchText = (TextView) findViewById(R.id.searchText);

        all_posts = (ListView) findViewById(R.id.postsList);
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
                retrieved.addAll(posts);
                System.out.println(getPostNames(retrieved));
                System.out.println("-------------====");
//                postsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, getPostNames(posts));
                postsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, getPostNames(retrieved));
                all_posts.setAdapter(postsAdapter);

                all_posts.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent toPost = new Intent(context, PostActivity.class);
                        toPost.putExtra("name", retrieved.get(position).getName());
                        toPost.putExtra("genre", retrieved.get(position).getGenre());
                        toPost.putExtra("picture", retrieved.get(position).getPicture());
                        startActivity(toPost);
//                        finish();
                    }
                });
            }

            @Override
            public void failure(RetrofitError e) {
                //displayError(e);
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
//        postsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, getPostNames(retrieved));
//        System.out.println("=========== post adapter is "+(postsAdapter == null));
//        all_posts.setAdapter(postsAdapter);
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
//                        Intent intent = new Intent("com.example.menrit.rate.PostActivity");
//                        startActivity(intent);
                        ArrayList<Post> results = new ArrayList<Post>();
                        String content = searchText.getText().toString();
                        for (int i = 0; i < retrieved.size(); i++)
                        {
                            if (retrieved.get(i).getName().toLowerCase().contains(content.toLowerCase()))
                            {
                                results.add(retrieved.get(i));
                            }
                        }

                        Intent toSearch = new Intent(context, SearchActivity.class);
                        toSearch.putExtra("results", results);
                        startActivity(toSearch);
                        finish();
                    }

                }
        );
}
    public void OnClickButtonPost(){

        button_Post=(Button)findViewById(R.id.postbtn);
        button_Post.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
//                        Intent intent=new Intent("com.example.menrit.rate.PostActivity");
                        Intent intent = new Intent(context, NewPostActivity.class);
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
