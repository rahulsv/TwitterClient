package com.codepath.apps.mytwitterapp;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	private static final int REQUEST_CODE = 102;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		loadProfileInfo();
	}

	private void loadProfileInfo() {
		MyTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				User u = User.fromJson(json);
				getActionBar().setTitle("@" + u.getScreenName());
				populateProfileView(u);
			}

			private void populateProfileView(User u) {
				TextView tvName = (TextView)findViewById(R.id.tvName);
				TextView tvTagLine = (TextView)findViewById(R.id.tvTagLine);
				TextView tvFollowers = (TextView)findViewById(R.id.tvFollowers);
				TextView tvFollowing = (TextView)findViewById(R.id.tvFollowing);
				ImageView tvProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
				
				tvName.setText(u.getName());
				tvTagLine.setText(u.getTagLine());
				tvFollowers.setText(u.getFollowersCount() + " followers");
				tvFollowing.setText(u.getFriendsCount() + " following");
				ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), tvProfileImage);
				
				
			}
		});
	}
	
	private void refresh() {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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

	    case R.id.home:
		      Toast.makeText(this, "Profile Activity selected", Toast.LENGTH_SHORT).show();
		      Intent i2 = new Intent(this,TimelineActivity.class);
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
}
