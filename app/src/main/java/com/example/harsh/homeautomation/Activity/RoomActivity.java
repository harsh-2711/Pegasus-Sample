package com.example.harsh.homeautomation.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harsh.homeautomation.Models.Room;
import com.example.harsh.homeautomation.R;
import com.example.harsh.homeautomation.Adapter.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    List<Room> room;
    RecyclerView listView;
    private RoomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        listView = findViewById(R.id.listview);

        room = new ArrayList<>();

        adapter = new RoomAdapter(this, room, new RoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Room item) {
                startActivity(new Intent(RoomActivity.this, AppliancesActivity.class));
            }
        });
        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);
        new loadList().execute();
    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listview);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private class loadList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            room.add(new Room("Bedroom", R.drawable.ic_bed));
            room.add(new Room("Study Room", R.drawable.study_room));
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
