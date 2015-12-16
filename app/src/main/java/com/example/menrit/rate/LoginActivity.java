package com.example.menrit.rate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menrit.rate.model.User;
import com.example.menrit.rate.util.ApiRouter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    User current_user;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SignIn(View view)
    {
        if (((EditText)findViewById(R.id.username_email)).getText().toString().equals("") ||
                ((EditText)findViewById(R.id.login_password)).getText().toString().equals(""))
        {
            System.out.println("========================");
            System.out.println("Inside empty");
            System.out.println("========================");
            Toast.makeText(this, "You didn't enter any credentials", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ApiRouter.withoutToken().login(((EditText) findViewById(R.id.username_email)).getText().toString(),
                    ((EditText) findViewById(R.id.login_password)).getText().toString(),
                    new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {
                            System.out.println("========================");
                            System.out.println("Inside New intent");
                            System.out.println("========================");
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT);
                        }
                    }
            );

        }
    }

    public void register(View view)
    {
        startActivity(new Intent(context, CreateUserActivity.class));
    }
}