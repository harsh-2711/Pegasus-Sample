package com.example.harsh.homeautomation.Activity;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.harsh.homeautomation.Adapter.ApplianceAdapter;
import com.example.harsh.homeautomation.Models.Appliance;
import com.example.harsh.homeautomation.Models.Room;
import com.example.harsh.homeautomation.R;

import java.util.ArrayList;
import java.util.List;

public class AppliancesActivity extends AppCompatActivity {

    List<Appliance> appliances;
    RecyclerView listView;
    private ApplianceAdapter adapter;
    private String[] roomAppliances;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Room room = (Room) getIntent().getSerializableExtra("Room");
        roomAppliances = room.getAppliances();
        listView = findViewById(R.id.listview);
        appliances = new ArrayList<>();
        adapter = new ApplianceAdapter(this, appliances, new ApplianceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Appliance item) {
                if(item.getName().equals("Light")){
                    Toast.makeText(AppliancesActivity.this, "Light", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AppliancesActivity.this, "Else", Toast.LENGTH_SHORT).show();
                }
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
            for (int i = 0; i < roomAppliances.length; i++) {
                appliances.add(new Appliance(roomAppliances[i],false,"0","0",0));
            }
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

    /*    String[] values = new String[]{"Air Conditioner", "Fan"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_item, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();

            }

        });*/
