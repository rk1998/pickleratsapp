package edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.Model;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.RatReport;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.Model.User;
import edu.gatech.pickleratsapp.cs2340.udirtyrat.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView listView = (ListView)findViewById(R.id.user_list);
        Model model = Model.get_instance();
        final List<User> users  = model.get_users();
        List<String> user_strings = new LinkedList<>();
        Button logout = (Button) findViewById(R.id.logout_admin);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

               //go back to home screen
                Intent myIntent = new Intent(AdminActivity.this,
                        edu.gatech.pickleratsapp.cs2340.udirtyrat.controllers.HomeScreen.class);
                startActivity(myIntent);
            }
        });
        for(User u: users) {
            user_strings.add(u.toString());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, user_strings) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(users.get(position).get_isLocked()) {
                    view.setBackgroundColor(Color.RED);
                }
                return view;

            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User u = users.get(position);
                Intent detailIntent = new Intent(AdminActivity.this, UserDetailActivity.class);
                detailIntent.putExtra("user_id",  u.get_userID());
                startActivity(detailIntent);
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
