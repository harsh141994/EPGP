package com.epgp.inflibnet.epgp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class staticModuleLoad extends ActionBarActivity {

    String subName;
    String csrno;
    String mcatid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_module_load);

        Intent i = getIntent();
        subName = i.getExtras().getString("subname");
        csrno = i.getExtras().getString("csrno");
        mcatid = i.getExtras().getString("mcatid");

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://epgp.inflibnet.ac.in/beta/view.php?&category=" + mcatid ));
        startActivity(browserIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_static_module_load, menu);
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
