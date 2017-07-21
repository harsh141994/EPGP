package com.epgp.inflibnet.epgp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epgp.inflibnet.epgp.model.Subject;
import com.epgp.inflibnet.epgp.parsers.SubjectJSONParser;

import java.util.ArrayList;
import java.util.List;

public class Subjects extends Activity {

	private ListView lv;
	ArrayAdapter<String> adapter;

	EditText input;



	List<MyTask> tasks;

	final ArrayList<String> subName = new ArrayList<String>();

	List<Subject> subjectList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjects);

		lv = (ListView) findViewById(R.id.listView1);

		input = (EditText) findViewById(R.id.searchinput);
		
//		Initialize the TextView for vertical scrolling

		

		
		tasks = new ArrayList<MyTask>();

		if (isOnline()) {
			requestData("http://epgp.inflibnet.ac.in/beta/frontpage.php");
		} else {
			Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
		}



		// Adding items to listview


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_subjects, menu);
		return true;
	}



	private void requestData(String uri) {
		MyTask task = new MyTask();
		task.execute(uri);
	}

	protected void updateDisplay() {
		
		if (subjectList != null) {
			for (Subject subject : subjectList) {
				subName.add(subject.getCat_name());

			}
		}




		// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.product_name, subName);
		lv.setAdapter(adapter);



		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				String itemValue = (String) lv.getItemAtPosition(pos);
				String str = lv.getItemAtPosition(pos).toString();
				//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

				for(Subject subject : subjectList){
					if(str.equals(subject.getCat_name())){
						Intent i = new Intent(Subjects.this, subdetails.class);
//						i.putExtra("subname", str);
//						i.putExtra("pi_name", subject.getPi_name());
//						i.putExtra("designation_affilation", subject.getDesignation_affilation());
//						i.putExtra("anchor_institut", subject.getAnchor_institute());
//						i.putExtra("emailid",subject.getEmailid());
//						i.putExtra("phone_number",subject.getPhone_number());
//						i.putExtra("static_module", subject.getStatic_module());
//						i.putExtra("four_module", subject.getFour_module());

						i.putExtra("subname", str);
						i.putExtra("csrno", subject.getCsrno());
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
			
			subjectList = SubjectJSONParser.parseFeed(result);
			updateDisplay();




			input.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					Subjects.this.adapter.getFilter().filter(s);
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					Subjects.this.adapter.getFilter().filter(s);

				}

				@Override
				public void afterTextChanged(Editable s) {

				}


			});

		}
		
		@Override
		protected void onProgressUpdate(String... values) {
//			updateDisplay(values[0]);
		}
		
	}


	public void onBackPressed() {
		Intent i = new Intent(Subjects.this, First.class);
		startActivity(i);
		finish();
	}

}