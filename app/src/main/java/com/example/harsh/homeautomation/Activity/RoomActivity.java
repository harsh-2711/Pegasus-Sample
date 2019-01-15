package com.example.harsh.homeautomation.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harsh.homeautomation.Models.Room;
import com.example.harsh.homeautomation.R;
import com.example.harsh.homeautomation.Adapter.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    List<Room> room;
    RecyclerView listView;
    private RoomAdapter adapter;
    private ArrayList<String> defaultAppliances;
    private ArrayList<String> room2Appliances, room3Appliances, room4Appliances, room5Appliances;
    private int roomCounter = 0;
    private EditText ipAddress;
    private Button enter;
    WebView browser;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        logOut = (Button) findViewById(R.id.log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.setLogInStatus(false);
                Intent intent = new Intent(RoomActivity.this, LoginActivity.class);
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

        listView = findViewById(R.id.listview1);
        defaultAppliances = new ArrayList<>();
        defaultAppliances.add("Light");
        defaultAppliances.add("Fan");
        defaultAppliances.add("AC");
        defaultAppliances.add("Heater");

        room2Appliances = new ArrayList<>();
        room2Appliances.add("Light");
        room2Appliances.add("Fan");
        room2Appliances.add("AC");
        room2Appliances.add("Heater");

        room3Appliances = new ArrayList<>();
        room3Appliances.add("Light");
        room3Appliances.add("Fan");
        room3Appliances.add("AC");
        room3Appliances.add("Heater");

        room4Appliances = new ArrayList<>();
        room4Appliances.add("Light");
        room4Appliances.add("Fan");
        room4Appliances.add("AC");
        room4Appliances.add("Heater");

        room5Appliances = new ArrayList<>();
        room5Appliances.add("Light");
        room5Appliances.add("Fan");
        room5Appliances.add("AC");
        room5Appliances.add("Heater");

        room = new ArrayList<>();

        adapter = new RoomAdapter(this, room, new RoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Room item) {
                Intent i = new Intent(RoomActivity.this, AppliancesActivity.class);
                i.putExtra("Room",item);
                i.putExtra("IPAddress",ipAddress.getText().toString());
                startActivity(i);
            }
        });
        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);
        new loadList().execute();
    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listview1);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private class loadList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            room.add(new Room("Room1", roomCounter, R.drawable.ic_bed, defaultAppliances));
            roomCounter++;
            room.add(new Room("Room2", roomCounter, R.drawable.study_room, room2Appliances));
            roomCounter++;
            room.add(new Room("Room3", roomCounter, R.drawable.ic_bed, room3Appliances));
            roomCounter++;
            room.add(new Room("Room4", roomCounter, R.drawable.study_room, room4Appliances));
            roomCounter++;
            room.add(new Room("Room5", roomCounter, R.drawable.ic_bed, room5Appliances));
            roomCounter++;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
    }
}
