package ch.hsr.uint1.whitespace.library.client.android.activities;

import android.view.Menu;
import roboguice.activity.RoboActivity;

public abstract class CommonActivity extends RoboActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
}
