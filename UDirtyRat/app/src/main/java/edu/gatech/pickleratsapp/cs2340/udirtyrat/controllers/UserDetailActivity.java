package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String userId = this.getIntent().getExtras().getString("user_id");
        DataBaseHelper mDataBaseHelper = new DataBaseHelper(this);
        User user = mDataBaseHelper.getUserById(userId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView userName = (TextView)findViewById(R.id.usr_name);
        TextView userIden = (TextView)findViewById(R.id.usr_id_name);
        TextView userPass = (TextView) findViewById(R.id.password);
        String userNameString = "User Name: " + user.get_name();
        String userIdString = "User Id: " + user.get_userID();
        String userPassString = "User Password: " + user.get_passWord();
        userName.setText(userNameString);
        userIden.setText(userIdString);
        userPass.setText(userPassString);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
