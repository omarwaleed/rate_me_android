package com.example.menrit.rate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menrit.rate.model.Post;
import com.example.menrit.rate.util.ApiRouter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewPostActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
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
    }

    public void submitNewPost(View view)
    {
        TextView postName = (TextView) findViewById(R.id.newPostName);
        TextView postGenre = (TextView) findViewById(R.id.newPostGenre);
        ApiRouter.withoutToken().newPost(postName.getText().toString(), postGenre.getText().toString(), new Callback<Post>() {
            @Override
            public void success(Post post, Response response) {
                System.out.println("=============== Created Post");
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Post Created Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("=============== Post Failed");
                Toast.makeText(context, "Post Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
