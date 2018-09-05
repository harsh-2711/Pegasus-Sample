package com.example.harsh.homeautomation.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    public ApplianceAdapter adapter;
    public ArrayList<String> roomAppliances;
    private Room room;
    WebView internet;
    public String IPAddress;
    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);
        internet = (WebView) findViewById(R.id.internet);

        room = (Room) getIntent().getSerializableExtra("Room");
        IPAddress = (String) getIntent().getStringExtra("IPAddress");
        roomAppliances = room.getAppliances();
        listView = findViewById(R.id.listview);
        appliances = new ArrayList<>();

        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;

        // **********Web View Setting************


        // **********Setting Ends Here***********

        adapter = new ApplianceAdapter(this, appliances, new ApplianceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Appliance item) {


                // Web View*****

                internet.loadUrl("http://" + IPAddress);
                internet.getSettings().setJavaScriptEnabled(true);
                internet.getSettings().setDomStorageEnabled(true);
                internet.addJavascriptInterface(new WebAppInterface(getApplication()), "Android");
                internet.setWebChromeClient(new WebChromeClient());
                internet.setWebViewClient(new WebViewClient() {

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {

                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                // Data needs to be specified or else for loop need to be run

                                int onoff;
                        if(item.getStatus())
                            onoff = 1;
                        else
                            onoff = 0;
                                data = "$A$c" + item.getClientID() + "$" + String.valueOf(onoff) +"$0$";
                                String javascript = "javascript:var x = document.getElementById('txbuff').value ='" + data + "'; var y = document.getElementById('txbuff1').click();";
                                internet.loadUrl(javascript);
                                data="";
                            }
                        }, 1000);
                    }

                });

                // Stop*********
            }
        });

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
            for (int i = 0; i < roomAppliances.size(); i++) {
                if (room.getRoomID() == 0) {
                    appliances.add(new Appliance(roomAppliances.get(i), false, String.valueOf(room.getRoomID() + i + 1), "0", 0));
                } else {
                    appliances.add(new Appliance(roomAppliances.get(i), false, String.valueOf(room.getRoomID() + i + 3), "0", 0));
                }
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

    public class WebAppInterface {
        Context mContext;
        String data;

        WebAppInterface(Context ctx) {
            this.mContext = ctx;
        }


        @JavascriptInterface
        public void sendData(String data) {
            //Get the string value to process
            this.data = data;
            String st = "";
            for(int i = 0; i < data.length(); i++){
                if(i == 8 || i == 9 || i == 10 || i ==11)
                    st += data.charAt(i);
            }
//          data = data.substring(0,4);
            //        String s = "Current: " + data + "Amp";
            List<Character> clientID = new ArrayList<>();
            int count = 0;
            for (int i = 3; i < data.length(); i++) {
                if (data.charAt(i) == '$') {
                    count = i;
                    break;
                } else
                    clientID.add(data.charAt(i));
            }

            List<Character> status = new ArrayList<>();
            for (int i = count + 1; i < data.length(); i++) {
                if (data.charAt(i) == '$') {
                    count = i;
                    break;
                } else
                    status.add(data.charAt(i));
            }

            List<Character> powerConsumption = new ArrayList<>();
            for (int i = count + 1; i < data.length(); i++) {
                if (data.charAt(i) == '$') {
                    count = i;
                    break;
                } else
                    powerConsumption.add(data.charAt(i));
            }

            String temp1 = clientID.toString();

            String s1 = String.valueOf(temp1.charAt(1)) + String.valueOf(temp1.charAt(4));

            String s2 = status.get(0).toString();

            String temp2 = powerConsumption.toString();

            String temp3 = String.valueOf(temp2.charAt(1)) + String.valueOf(temp2.charAt(4)) + String.valueOf(temp2.charAt(7)) + String.valueOf(temp2.charAt(10));

            String s3 = temp3.toString();

            // Setting status and power consumed of the device having received client ID
            for (int i = 0; i < roomAppliances.size(); i++) {
                String s = "c" + appliances.get(i).getClientID();
                if(s.equals(s1)) {
                    if(appliances.get(i).getStatus()) {
                        if(st != "0.00") {
                            ArrayList<String> abc = new ArrayList<>();
                            abc.add(st);
                            new Append().execute(abc);
                        }

                    }
                    else {
                        appliances.get(i).setStatus(false);
                        appliances.get(i).setPowerConsumed("0");
                    }
                    }
                else {
                    appliances.get(i).setStatus(false);
                    appliances.get(i).setPowerConsumed("0");
                }

            }


        }

    }

    public class Append extends AsyncTask<ArrayList<String>,Void,Void> {


        @Override
        protected Void doInBackground(ArrayList<String>... arrayLists) {
            appliances.remove(1);
            appliances.add(1, new Appliance(roomAppliances.get(1), true, String.valueOf(2), arrayLists[0].get(0).toString(), 0));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
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
