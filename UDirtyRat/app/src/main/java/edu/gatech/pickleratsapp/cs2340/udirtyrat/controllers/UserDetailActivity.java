package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.database.DataBaseHelper;

public class UserDetailActivity extends AppCompatActivity {
    private TextView userPass;
    private String newPassword = "";

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
        userPass = (TextView) findViewById(R.id.password);
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
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setTitle("Enter the New Password");
                LinearLayout linearLayout = new LinearLayout(UserDetailActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                final EditText text = new EditText(UserDetailActivity.this);
                text.setText("password");
                linearLayout.addView(text);
                alertDialog.setView(linearLayout);
                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newPassword = text.getText().toString();
                        Model model = Model.get_instance();
                        model.set_user_password(newPassword, user.get_userID(),
                                UserDetailActivity.this);
                        Toast.makeText(getApplicationContext(),
                                user.get_userID() + " password changed",
                                Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });

                alertDialog.show();
                //userPass.setText(newPassword);


            }
        });
    }

}
