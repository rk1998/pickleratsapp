package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String userId = this.getIntent().getExtras().getString("user_id");
        DataBaseHelper mDataBaseHelper = new DataBaseHelper(this);
        final User user = mDataBaseHelper.getUserById(userId);
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

        Button lockButton = (Button) findViewById(R.id.lock_user);
        if(user.get_isLocked()) {
            lockButton.setText("Unlock User");
        } else {
            lockButton.setText("Lock User");
        }
        Button changePassword = (Button) findViewById(R.id.change_pass);
        Button backButton = (Button) findViewById(R.id.back_to_usr_list);
        //final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        lockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Model model = Model.get_instance();
                Context context = getApplicationContext();
                model.set_user_locked_status(!user.get_isLocked(), user.get_userID(), context);
                Toast.makeText(getApplicationContext(),
                        user.get_userID() + " lock status changed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(UserDetailActivity.this, AdminActivity.class);
                startActivity(myIntent);
            }
        });

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
