package com.epgp.inflibnet.epgp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class syllDetails extends ActionBarActivity {

    String syl_id;
            String linkfile;
    String linkfileext;
            String cat_name;
    String mcatid;
            String mpcatid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syll_details);

        Intent i = getIntent();
        syl_id = i.getExtras().getString("syl_id");
        linkfile = i.getExtras().getString("linkfile");
        linkfileext = i.getExtras().getString("linkfileext");
        cat_name = i.getExtras().getString("cat_name");
        mcatid = i.getExtras().getString("mcatid");
        mpcatid = i.getExtras().getString("mpcatid");




        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://epgp.inflibnet.ac.in/ecpm/syllabus/syllabus_"+ syl_id +"_"+ linkfile +"."+linkfileext));
      startActivity(browserIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_syll_details, menu);
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
