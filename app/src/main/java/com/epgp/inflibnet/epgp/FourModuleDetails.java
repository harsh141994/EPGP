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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epgp.inflibnet.epgp.model.FourModuleDetail;
import com.epgp.inflibnet.epgp.parsers.FourModuleDetailJSONParser;

import java.util.ArrayList;
import java.util.List;


public class FourModuleDetails extends ActionBarActivity {

    private ListView lv;
    ArrayAdapter<String> adapter;

    EditText input;
    String subjectName, csrno;



    List<MyTask> tasks;

    final ArrayList<String> paperName = new ArrayList<String>();

    List<FourModuleDetail> fourModuleDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_module_details);

        Intent i = getIntent();

        subjectName = i.getExtras().getString("subjectName");
        csrno = i.getExtras().getString("csrno");

        lv = (ListView) findViewById(R.id.four_listView);

        //   input = (EditText) findViewById(R.id.searchinput);

        if (isOnline()) {
            requestData("http://epgp.inflibnet.ac.in/beta/jpapernosl.php");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {

        if (fourModuleDetailList != null) {
            for (FourModuleDetail fmodule : fourModuleDetailList) {
                if(csrno.equals(fmodule.getCat_parent())){
                    String s = new String(fmodule.getCat_name()+" ("+fmodule.getTotal()+")");
                    paperName.add(s);
                }


            }
        }




        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.product_name, paperName);
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                String itemValue = (String) lv.getItemAtPosition(pos);
                String str = lv.getItemAtPosition(pos).toString();
                //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                for (FourModuleDetail fModule : fourModuleDetailList) {
                    String s = new String(fModule.getCat_name()+" ("+fModule.getTotal()+")");

                    if (str.equals(s)) {
                        Intent i = new Intent(FourModuleDetails.this, fourModuleLoad.class);
//						i.putExtra("subname", str);
//						i.putExtra("pi_name", subject.getPi_name());
//						i.putExtra("designation_affilation", subject.getDesignation_affilation());
//						i.putExtra("anchor_institut", subject.getAnchor_institute());
//						i.putExtra("emailid",subject.getEmailid());
//						i.putExtra("phone_number",subject.getPhone_number());
//						i.putExtra("static_module", subject.getStatic_module());
//						i.putExtra("four_module", subject.getFour_module());

                        i.putExtra("subname", str);
                        i.putExtra("csrno", fModule.getCat_parent());
                        i.putExtra("mcatid",fModule.getMcatid() );
                        startActivity(i);

                    }
                }


            }
        });

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

            fourModuleDetailList = FourModuleDetailJSONParser.parseFeed(result);
            updateDisplay();




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

        }

        @Override
        protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_four_module_details, menu);
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
