package com.example.menrit.rate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    private static Button reviewbtn;
    private static Button commentbtn;

    TextView nameText;
    TextView genreText;
    ImageView postImage;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        nameText = (TextView) findViewById(R.id.postName);
        genreText = (TextView) findViewById(R.id.postGenre);
        postImage = (ImageView) findViewById(R.id.postImage);

        nameText.setText(getIntent().getExtras().getString("name"));
        genreText.setText(getIntent().getExtras().getString("genre"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        OnClickButtonComment();
        OnClickButtonReview();
    }

    public void OnClickButtonComment() {
        commentbtn = (Button) findViewById(R.id.commentbtn);
        commentbtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.menrit.rate.CommentsActivity");
                        startActivity(intent);
                    }

                }
        );
    }

    public void OnClickButtonReview() {
        reviewbtn = (Button) findViewById(R.id.reviewbtn);
        reviewbtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.menrit.rate.ReviewActivity");
                        startActivity(intent);
                    }

                }
        );
    }


}

