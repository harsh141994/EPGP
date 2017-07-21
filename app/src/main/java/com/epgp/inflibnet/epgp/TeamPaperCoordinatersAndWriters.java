package com.epgp.inflibnet.epgp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epgp.inflibnet.epgp.model.TeamContentWriters;
import com.epgp.inflibnet.epgp.model.TeamPaperCoordinaters;
import com.epgp.inflibnet.epgp.parsers.TeamContentWritersJSONParser;
import com.epgp.inflibnet.epgp.parsers.TeamPaperCoordinatersJSONParser;

import java.util.ArrayList;
import java.util.List;


public class TeamPaperCoordinatersAndWriters extends ActionBarActivity {

    String syl_id, linkfile, linkfileext, cat_name, mcatid, mpcatid, subjectName, csrno;
    String id;
    ArrayAdapter<String> adapter;
    List<TeamPaperCoordinaters> coordinatersList;
    List<TeamContentWriters> writersList;
    private ListView lv;

    final ArrayList<String> writersName = new ArrayList<String>();

    TextView pc, pc_name, cw, cw_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_paper_coordinaters_and_writers);


        Intent i = getIntent();
        subjectName = i.getExtras().getString("subjectName");
        csrno = i.getExtras().getString("csrno");
        syl_id = i.getExtras().getString("syl_id");
        linkfile = i.getExtras().getString("linkfile");
        linkfileext = i.getExtras().getString("linkfileext");
        cat_name = i.getExtras().getString("cat_name");
        mcatid = i.getExtras().getString("mcatid");
        mpcatid = i.getExtras().getString("mpcatid");

        pc = (TextView) findViewById(R.id.pc);
        pc_name = (TextView) findViewById(R.id.pc_name);
        cw = (TextView) findViewById(R.id.cw);
 //       cw_name = (TextView) findViewById(R.id.cw_names);

        lv = (ListView) findViewById(R.id.cw_list_view);

        if (isOnline()) {
            requestData("http://epgp.inflibnet.ac.in/beta/jpcc.php");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_paper_coordinaters_and_writers, menu);
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


    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {

        if (coordinatersList != null) {
            for (TeamPaperCoordinaters coordinaters : coordinatersList) {
                if(mcatid.equals(coordinaters.getR_paper())) {
                    id=mcatid;
                    String s = new String(coordinaters.getFname()+" "+ coordinaters.getMname()+ " "+ coordinaters.getLname());
                    pc_name.setText(s);

                    dataRequestAgain("http://epgp.inflibnet.ac.in/beta/jcwteam.php");
                }
            }
        }
    }

    private void dataRequestAgain(String uri) {
        MyTaskAgain task = new MyTaskAgain();
        task.execute(uri);
    }

    private void updateDisplayAgain() {
        if(writersList!=null){

            for (TeamContentWriters contentWriters : writersList){
                if(id.equals(contentWriters.getMcatid())) {
                    String name = new String(contentWriters.getFname() + " " + contentWriters.getMname() + " " + contentWriters.getLname());
                    writersName.add(name);
                }
            }
        }


        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.product_name, writersName);
        lv.setAdapter(adapter);
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

            coordinatersList = TeamPaperCoordinatersJSONParser.parseFeed(result);
            updateDisplay();
        }




//            input.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    Subjects.this.adapter.getFilter().filter(s);
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    Subjects.this.adapter.getFilter().filter(s);
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//
//
//            });
//
//        }

            protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
        }

    }


//    public void onBackPressed() {
//        Intent i = new Intent(Subjects.this, First.class);
//        startActivity(i);
//        finish();
//    }





    private class MyTaskAgain extends AsyncTask<String, String, String> {

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

            writersList = TeamContentWritersJSONParser.parseFeed(result);
            updateDisplayAgain();
        }




//            input.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    Subjects.this.adapter.getFilter().filter(s);
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    Subjects.this.adapter.getFilter().filter(s);
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//
//
//            });
//
//        }

        protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
        }

    }



}
