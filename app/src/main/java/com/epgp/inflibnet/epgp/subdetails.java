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
import android.widget.TextView;
import android.widget.Toast;

import com.epgp.inflibnet.epgp.model.SubDetails;
import com.epgp.inflibnet.epgp.model.SubDetailsStaticModule;
import com.epgp.inflibnet.epgp.parsers.SubDetailsJSONParser;
import com.epgp.inflibnet.epgp.parsers.SubDetailsStaticModuleJSONParser;

import java.util.List;


public class subdetails extends Activity {

    String subjectName;
    String csrno;

    TextView tv;
    TextView pi_name;
    TextView designation_affilation;
    TextView anchor_institute;
    TextView emailid;
    TextView phone_number;

    TextView static_module;

    List<SubDetails> subDetailsList;
    List<SubDetailsStaticModule> subDetailsStaticModuleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subdetails);

        Intent geti=getIntent();


         tv=(TextView)findViewById(R.id.textView2);
         pi_name=(TextView)findViewById(R.id.pi_name);
         designation_affilation=(TextView)findViewById(R.id.designation_affilation);
         anchor_institute=(TextView)findViewById(R.id.anchor_institute);
         emailid=(TextView)findViewById(R.id.emailid);
         phone_number=(TextView)findViewById(R.id.phone_number);

        static_module = (TextView) findViewById(R.id.textView_third);

        subjectName = geti.getExtras().getString("subname");
        csrno = geti.getExtras().getString("csrno");

        tv.setText(subjectName);

//        tv.setText(geti.getExtras().getString("subname"));
//        pi_name.setText(geti.getExtras().getString("pi_name"));
//        designation_affilation.setText(geti.getExtras().getString("designation_affilation"));
//        anchor_institute.setText(geti.getExtras().getString("anchor_institute"));
//        emailid.setText(geti.getExtras().getString("emailid"));
//        phone_number.setText(geti.getExtras().getString("phone_number"));



        if (isOnline()) {
            requestData("http://epgp.inflibnet.ac.in/beta/jpcteam.php");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }

    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {

        if (subDetailsList != null) {
            for (SubDetails subDetails : subDetailsList) {
                if (csrno.equals(subDetails.getR_subject())){


                    pi_name.setText(subDetails.getFname()+ " "+ subDetails.getMname()+" "+subDetails.getLname());
                    designation_affilation.setText(subDetails.getDesignation());
                    anchor_institute.setText(subDetails.getOrganization());
                    emailid.setText(subDetails.getEmailid());
                    phone_number.setText(subDetails.getContactno());
                }

            }
        }

        // Adding items to listview
//        adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.product_name, subName);
//        lv.setAdapter(adapter);



//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
//                String itemValue = (String) lv.getItemAtPosition(pos);
//                String str = lv.getItemAtPosition(pos).toString();
//                //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
//
//                for(Subject subject : subjectList){
//                    if(str.equals(subject.getCat_name())){
//                        Intent i = new Intent(Subjects.this, subdetails.class);
////						i.putExtra("subname", str);
////						i.putExtra("pi_name", subject.getPi_name());
////						i.putExtra("designation_affilation", subject.getDesignation_affilation());
////						i.putExtra("anchor_institut", subject.getAnchor_institute());
////						i.putExtra("emailid",subject.getEmailid());
////						i.putExtra("phone_number",subject.getPhone_number());
////						i.putExtra("static_module", subject.getStatic_module());
////						i.putExtra("four_module", subject.getFour_module());
//
//                        i.putExtra("subname", str);
//                        i.putExtra("csrno", subject.getCsrno());
//                        startActivity(i);
//
//                    }
//                }
//            }
//        });



    }

    private void requestDataAgain(String uri) {

        MyTaskAgain task = new MyTaskAgain();
        task.execute(uri);
    }

    private void updateDisplayAgain() {

        if (subDetailsStaticModuleList!= null){
            static_module.setText("harsh");
            for (SubDetailsStaticModule staticModule : subDetailsStaticModuleList){

                if (csrno.equals(staticModule.getSubject_id())){


                    String s = new String("(Static) Module "+staticModule.getTotal());
                    static_module.setText(s);
                }
            }
        }
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

            subDetailsList = SubDetailsJSONParser.parseFeed(result);
            updateDisplay();

//            if (isOnline()) {
//                requestDataAgain("http://epgp.inflibnet.ac.in/beta/jstatic.php");
//            } else {
//                Toast.makeText(subdetails.this, "Network isn't available", Toast.LENGTH_LONG).show();
//            }








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

            subDetailsStaticModuleList = SubDetailsStaticModuleJSONParser.parseFeed(result);
            updateDisplayAgain();
        }

        @Override
        protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subdetails, menu);
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
    public void team(View view){
        Intent i = new Intent(this, team.class);
        i.putExtra("subjectName", subjectName);
        i.putExtra("csrno", csrno);
        startActivity(i);
    }

    public void syllabus(View view){
        Intent i = new Intent(this, Syllabus.class);
        i.putExtra("subjectName", subjectName);
        i.putExtra("csrno", csrno);
        startActivity(i);
    }

    public void staticModule(View view){
        Intent i = new Intent(this, StaticModuleDetails.class);
        i.putExtra("subjectName", subjectName);
        i.putExtra("csrno", csrno);
        startActivity(i);
    }

    public void fourModule(View view){
        Intent i = new Intent(this, FourModuleDetails.class);
        i.putExtra("subjectName", subjectName);
        i.putExtra("csrno", csrno);
        startActivity(i);
    }

    public void statistics(View view){
        Intent i = new Intent(this, Statistics.class);
        i.putExtra("subjectName", subjectName);
        i.putExtra("csrno", csrno);
        startActivity(i);
    }
}
