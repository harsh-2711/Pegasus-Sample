package com.example.harsh.homeautomation.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.harsh.homeautomation.Adapter.FloorAdapter;
import com.example.harsh.homeautomation.Models.Room;
import com.example.harsh.homeautomation.R;

import java.util.ArrayList;

public class FloorActivity extends AppCompatActivity {

    private Button logOut;
    private EditText ipAddress;
    private Button enter;
    WebView browser;

    RecyclerView listView;
    private ArrayList<Room> defaultRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        logOut = (Button) findViewById(R.id.log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.setLogInStatus(false);
                Intent intent = new Intent(FloorActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ipAddress = (EditText) findViewById(R.id.ipAddress);

        browser = (WebView) findViewById(R.id.webView);
        enter = (Button) findViewById(R.id.enter);

        // Not needed
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.loadUrl("http://" + ipAddress.getText().toString());
            }
        });

        listView = (RecyclerView) findViewById(R.id.listviewFloor);
    }
}
