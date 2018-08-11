package com.example.harsh.homeautomation.Activity;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harsh.homeautomation.Adapter.ApplianceAdapter;
import com.example.harsh.homeautomation.Models.Appliance;
import com.example.harsh.homeautomation.R;

import java.util.ArrayList;
import java.util.List;

public class AppliancesActivity extends AppCompatActivity {

    List<Appliance> appliances;
    RecyclerView listView;
    private ApplianceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        listView = findViewById(R.id.listview);
        appliances = new ArrayList<>();
        adapter = new ApplianceAdapter(this,appliances);

        
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
            appliances.add(new Appliance("Light", false,"0","0",0));
            appliances.add(new Appliance("Fan", true,"0","0",0));
            appliances.add(new Appliance("AC", false,"0","0",0));
            appliances.add(new Appliance("Geyser", true,"0","0",0));
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
