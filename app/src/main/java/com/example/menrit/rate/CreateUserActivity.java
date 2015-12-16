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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menrit.rate.model.User;
import com.example.menrit.rate.util.ApiRouter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateUserActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
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

        Spinner gender = (Spinner) findViewById(R.id.gender);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Male");
        options.add("Female");
        ArrayAdapter<String> spinnerOptions = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, options);
        gender.setAdapter(spinnerOptions);
    }

    public void createAccount(View view)
    {
        EditText username = (EditText) findViewById(R.id.newUsername);
        EditText email = (EditText) findViewById(R.id.newEmail);
        EditText password = (EditText) findViewById(R.id.newPassword);
        Spinner gender = (Spinner) findViewById(R.id.gender);
//        DatePicker date_of_birth = (DatePicker) findViewById(R.id.birthday);

        ApiRouter.withoutToken().newUser(username.getText().toString(), email.getText().toString(), password.getText().toString(),
                gender.getSelectedItem().toString(),
                new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Registeration Successful. Now sign in", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(context, "Failed to register", Toast.LENGTH_SHORT).show();
                    System.out.println();
                    }
                });
    }

}
