package com.example.harsh.homeautomation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String data = "";
    Network network;
    WebView browser;
    EditText editIp;
    Button btnOn, btnOff;
    Button enter;
    String onoff = "0";
    boolean isStarted = false;
    public TextView textInfo1, textInfo2, textInfo3, textInfo4;
    View.OnClickListener btnOnOffClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnOn) {
                onoff = "1";
                isStarted = true;
            } else {
                onoff = "0";
                isStarted = false;
                textInfo2.setText("Client ID:");
                textInfo3.setText("Status:");
                textInfo4.setText("Power Consumption:");
            }


            browser.loadUrl("http://" + editIp.getText().toString());
            browser.getSettings().setJavaScriptEnabled(true);
            browser.getSettings().setDomStorageEnabled(true);
            browser.addJavascriptInterface(new WebAppInterface(getApplication()), "Android");
            browser.setWebChromeClient(new WebChromeClient());

            browser.setWebViewClient(new WebViewClient() {

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
                            data = "$A$c2$"+onoff+"$0$";
                            String javascript = "javascript:var x = document.getElementById('txbuff').value ='" + data + "'; var y = document.getElementById('txbuff1').click();";
                            browser.loadUrl(javascript);
                            data="";
                        }
                    }, 1000);
                }

            });

            //Change
          //  btnOn.setEnabled(false);
          //  btnOff.setEnabled(false);
/*
            String serverIP = editIp.getText().toString();

            Task taskEsp = new Task(serverIP);
            taskEsp.execute(onoff);*/


        }
    };

    @SuppressLint("JavascriptInterface")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Change
        editIp = (EditText) findViewById(R.id.ip);
        btnOn = (Button) findViewById(R.id.bon);
        btnOff = (Button) findViewById(R.id.boff);
        textInfo1 = (TextView) findViewById(R.id.info1);
        textInfo2 = (TextView) findViewById(R.id.info2);
        textInfo3 = (TextView) findViewById(R.id.info3);
        textInfo4 = (TextView) findViewById(R.id.info4);

        btnOn.setOnClickListener(btnOnOffClickListener);
        btnOff.setOnClickListener(btnOnOffClickListener);

        browser = (WebView) findViewById(R.id.webView);

        enter = (Button) findViewById(R.id.enter);

        // Not needed
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //New
                browser.loadUrl("http://" + editIp.getText().toString());
                browser.getSettings().setJavaScriptEnabled(true);
                browser.addJavascriptInterface(new WebAppInterface(getApplication()), "Android");

                // Till here
               // browser.loadUrl("http://" + editIp.getText().toString());   // 192.168.43.79   Online server - 14.139.122.116

               //Set Client

               /* browser.setWebViewClient(new WebViewClient(){
                    public void onPageFinished(WebView browser, String url) {
                        super.onPageFinished(browser, url);
                        try {
                            String data = "$a$012$0$0$0";
                            String javascript = "javascript:var x = document.getElementById('txbuff').value ='" + data + "';";
                            browser.loadUrl(javascript);
                            finish();
                        }
                        catch(Error e) {
                            //Do nothing
                        }
                        }
                });*/
                //

             //   String data = "$a$012$0$0$0";
             //   String javascript = "javascript:var x = document.getElementById('txbuff').value ='"+data+"';";
             //   browser.loadUrl(javascript);
            }
        });

        /* Changed
        browser = (WebView) findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
        browser.addJavascriptInterface(new WebAppInterface(getApplication()), "Android");
        browser.setWebChromeClient(new WebChromeClient());

        Till here */

        String data = "$a$012$0$0$0";
        //   browser.addJavascriptInterface(new StringGetter(), "stringGetter");
     //   browser.setWebViewClient(new WebViewClient() {});
        /*
         Change from here
         */
     //   browser.addJavascriptInterface(new WebAppInterfaceSend(getApplication()), "Android");
        /*
        Need to implement btnSend.onClick(function(){var x = Android.getData()}) in loop
        Till here
         */
       // browser.loadUrl("http://" + editIp.getText().toString());   // 192.168.43.79
       // network = new Network();
       // network.execute();
    }

    /*private class StringGetter {
        public String getString() {
            return "$a$012$0$0$0";
        }
    }*/

    public class WebAppInterface {
        Context mContext;
        String data;

        WebAppInterface(Context ctx){
            this.mContext=ctx;
        }


        @JavascriptInterface
        public void sendData(String data) {
            //Get the string value to process
            this.data=data;
//          data = data.substring(0,4);
  //        String s = "Current: " + data + "Amp";
            List<Character> clientID = new ArrayList<>();
            int count = 0;
            for(int i = 3; i < data.length(); i++) {
                if(data.charAt(i) == '$') {
                    count = i;
                    break;
                }
                else
                    clientID.add(data.charAt(i));
            }

            List<Character> status = new ArrayList<>();
            for(int i = count+1; i < data.length(); i++) {
                if(data.charAt(i) == '$') {
                    count = i;
                    break;
                }
                else
                    status.add(data.charAt(i));
            }

            List<Character> powerConsumption = new ArrayList<>();
            for(int i = count+1; i < data.length(); i++) {
                if(data.charAt(i) == '$') {
                    count = i;
                    break;
                }
                else
                    powerConsumption.add(data.charAt(i));
            }

            String temp1 = clientID.toString();

            String s1 = "Client ID: " + temp1.charAt(1) + temp1.charAt(4);

            String s2 = "Status: " + status.get(0).toString();

            String temp2 = powerConsumption.toString();

            String temp3 = String.valueOf(temp2.charAt(1)) + String.valueOf(temp2.charAt(4)) + String.valueOf(temp2.charAt(7)) + String.valueOf(temp2.charAt(10));

            String s3 = "Power Consumption: " + temp3.toString();

            if(!isStarted) {
                textInfo2.setText("Client ID:");
                textInfo3.setText("Status:");
                textInfo4.setText("Power Consumption:");
            }
            else {
                textInfo2.setText(s1);
                textInfo3.setText(s2);
                textInfo4.setText(s3);

            }
        }

    }

    public class WebAppInterfaceSend {
        Context mContext;
        String data;

        WebAppInterfaceSend(Context ctx) {
            this.mContext = ctx;
        }

        @JavascriptInterface
        public String getData() {
            return onoff;
        }
    }

    private class Network extends AsyncTask<Void,Void,Void> {

        StringBuilder a;
        @Override
        protected Void doInBackground(Void...voids) {
            URL url = null;
            try {
                url = new URL("http://" + editIp.getText().toString());
                URLConnection urlc = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF-8"));
                String inputLine;
                a = new StringBuilder();
                while((inputLine = in.readLine()) != null) {
                    a.append(inputLine);
                }
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textInfo2.setText(a.toString());
        }
    }

    private class TaskEsp extends AsyncTask<String, Void, String> {

        String server;
        String p;

        TaskEsp(String server) {
            this.server = server;
        }


        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        String data;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            p = "http://"+server;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textInfo1.setText(p);
                }
            });

            String result;
            String inputLine;
            try {
                //Create a URL object holding our ur
                p = "http://192.168.43.79";
                URL myUrl = new URL(p);
                //Create a connection
                /*HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                BufferedReader reader
                        = new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
                //Create a new buffered reader and String Builder
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                //Set our result equal to our stringBuilder
                Log.i("Info",stringBuilder.toString());
                result = stringBuilder.toString();*/

                URL url = new URL(p);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine1;
                data = "";
                while ((inputLine1 = in.readLine()) != null)
                    data += inputLine1;

                in.close();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return data;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            textInfo2.setText(result);
            btnOn.setEnabled(true);
            btnOff.setEnabled(true);
        }
    }

    private class Task extends AsyncTask<String,Void,String> {

            String server;

            Task(String server) {
                this.server = server;
            }

            @Override
            protected String doInBackground(String... params) {

                String val = params[0];
                final String p = "http://" + server + "?led=" + val;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textInfo1.setText(p);
                    }
                });

                String serverResponse = "";
                HttpClient httpclient = new DefaultHttpClient();
                try {
                    HttpGet httpGet = new HttpGet();
                    httpGet.setURI(new URI(p));
                    HttpResponse httpResponse = httpclient.execute(httpGet);

                    InputStream inputStream = null;
                    inputStream = httpResponse.getEntity().getContent();
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(inputStream));
                    serverResponse = bufferedReader.readLine();

                    inputStream.close();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    serverResponse = e.getMessage();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    serverResponse = e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    serverResponse = e.getMessage();
                }

                return serverResponse;
            }

            @Override
            protected void onPostExecute(String s) {
                //textInfo2.setText(s);
            }
        }

}
