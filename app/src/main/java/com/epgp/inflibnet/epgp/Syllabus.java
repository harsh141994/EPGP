package com.epgp.inflibnet.epgp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.epgp.inflibnet.epgp.model.SyllabusforJSON;
import com.epgp.inflibnet.epgp.parsers.SyllabusJSONParser;

import java.util.ArrayList;
import java.util.List;


public class Syllabus extends Activity {
    List<MyTask> tasks;
    List<SyllabusforJSON> syllabusList;
    String subjectName;
    String csrno;

    ArrayAdapter<String> adapter;
//    private WebView webView;
    final ArrayList<String> sylName = new ArrayList<String>();

    private ListView syll_listView;

//    final String SYLLABUS_BASE_URL =
//            "http://epgp.inflibnet.ac.in/ecpm/syllabus/syllabus_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

//        webView = (WebView) findViewById(R.id.webView);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        syll_listView = (ListView) findViewById(R.id.syll_listView);

        Intent i = getIntent();
        subjectName = i.getExtras().getString("subjectName");
        csrno = i.getExtras().getString("csrno");


        tasks = new ArrayList<MyTask>();

        if (isOnline()) {
            requestData("http://epgp.inflibnet.ac.in/beta/jsyllabus.php");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }

    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {

        if (syllabusList!= null){
            for(SyllabusforJSON sylla : syllabusList){
                if(csrno.equals(sylla.getMpcatid())){
                    sylName.add(sylla.getCat_name());
                }
            }
        }


        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.product_name, sylName);
        syll_listView.setAdapter(adapter);


        syll_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = syll_listView.getItemAtPosition(position).toString();


                for (SyllabusforJSON syllab : syllabusList){
                    if(str.equals(syllab.getCat_name())) {
                        Intent i = new Intent(Syllabus.this, syllDetails.class);

                        i.putExtra("syl_id", syllab.getSyl_id());
                        i.putExtra("linkfile", syllab.getLinkfile());
                        i.putExtra("linkfileext", syllab.getLinkfileext());
                        i.putExtra("cat_name", syllab.getCat_name());
                        i.putExtra("mcatid", syllab.getMcatid());
                        i.putExtra("mpcatid", syllab.getMpcatid());

                        startActivity(i);
                    }
                }
            }
        });


//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://epgp.inflibnet.ac.in/ecpm/syllabus/syllabus_96_biochemistry.pdf"));
//        startActivity(browserIntent);
       // webView.loadUrl("http://epgp.inflibnet.ac.in/ecpm/syllabus/syllabus_96_biochemistry.pdf");

//        if (syllabusList != null) {
//            for (SyllabusforJSON syll : syllabusList) {
//              //  if (subjectName.equalsIgnoreCase(syll.getCat_name())){
//
//                    String syl_id = syll.getSyl_id();
//                    String linkfile = syll.getLinkfile();
//                    String linkfileext = syll.getLinkfileext();
//                    String cat_name = syll.getCat_name();
//
//                    //                URL url = new URL(SYLLABUS_BASE_URL + syl_id + "_" + linkfile + "." + linkfileext);
//                    //               webView.loadUrl(String.valueOf(url));
//                    webView.loadUrl("http://epgp.inflibnet.ac.in/ecpm/syllabus/syllabus_96_biochemistry.pdf");
//            //    }
//            }
//        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
//			updateDisplay("Starting task");


        }

        @Override
        protected String doInBackground(String... params) {

            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            syllabusList = SyllabusJSONParser.parseFeed(result);
            updateDisplay();






        }

        @Override
        protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_syllabus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
