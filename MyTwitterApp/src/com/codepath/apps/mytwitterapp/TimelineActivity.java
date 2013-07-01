package com.codepath.apps.mytwitterapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.fragments.HomeTimelineFragment;
import com.codepath.apps.mytwitterapp.fragments.MentionsFragment;

public class TimelineActivity extends FragmentActivity implements TabListener {

	private static final int REQUEST_CODE = 101;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab()
				.setText("Home")
				.setTag("HomeTimelineFragment")
				.setIcon(R.drawable.ic_home)
				.setTabListener(this);
		Tab tabMentions = actionBar.newTab()
				.setText("Mentions")
				.setTag("MentionsTimelineFragment")
				.setIcon(R.drawable.ic_mention)
				.setTabListener(this);
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	private void refresh() {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.compose_tweet:
	      //Toast.makeText(this, "Compose Tweet selected", Toast.LENGTH_SHORT).show();
	    	Intent i = new Intent(this,ComposeActivity.class);
	    	startActivityForResult(i,REQUEST_CODE);
	      break;

	    case R.id.profile:
		      Toast.makeText(this, "Profile Activity selected", Toast.LENGTH_SHORT).show();
		      Intent i2 = new Intent(this,ProfileActivity.class);
		      startActivityForResult(i2,REQUEST_CODE);
		      break;

	    case R.id.timeline_refresh:
		      this.refresh();
		      break;

	    default:
	      break;
	    }

	    return true;
	  } 	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == RESULT_OK) { // && requestCode == REQUEST_CODE) 
	      this.refresh();
	  }

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = fm.beginTransaction();
		if(tab.getTag() == "HomeTimelineFragment" ) {
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
		} else {
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	} 	
}
