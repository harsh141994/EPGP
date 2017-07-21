package com.epgp.inflibnet.epgp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Dell on 02-04-2015.
 */


public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle harsh) {
        super.onCreate(harsh);
        setContentView(R.layout.splash);



        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent first_starting_main_activity = new Intent(Splash.this,First.class);
                    startActivity(first_starting_main_activity);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }


}
