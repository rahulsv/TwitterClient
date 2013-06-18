package com.codepath.apps.mytwitterapp;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.back_home:
	      //Toast.makeText(this, "Compose Tweet selected", Toast.LENGTH_SHORT).show();
	    	Intent i = new Intent(this,TimelineActivity.class);
	    	startActivity(i);
	    	//this.finish();
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 	
	
    public void cancelTweet(View view) {
    	Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
    	this.finish();
    }
	
	public void postTweet(View view) {
	   	EditText et = (EditText) findViewById(R.id.txtTweet);
	   	String sTweet = et.getText().toString();
	   	
		MyTwitterApp.getRestClient().postTweet(sTweet, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Log.d("DEBUG",jsonTweets.toString());
			}
		});
		this.finish();
		//Toast.makeText(this, "Tweeting: " + sTweet, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void finish() {
	  // Prepare data intent 
	  Intent data = new Intent();
	  // Activity finished ok, return the data
	  setResult(RESULT_OK, data);
	  super.finish();
	} 
	
}
