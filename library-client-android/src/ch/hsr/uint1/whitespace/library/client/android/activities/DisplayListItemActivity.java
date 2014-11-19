package ch.hsr.uint1.whitespace.library.client.android.activities;

import ch.hsr.uint1.whitespace.library.client.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
//import android.widget.TextView;

public class DisplayListItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		Intent intent = getIntent();
//		TextView textView = new TextView(this);
		String message = intent.getStringExtra(ListViewActivity.EXTRA_MESSAGE);
		
//		textView.setTextSize(40);
//		textView.setText(message);
//		setContentView(textView);
		TextView textView = (TextView) findViewById(R.id.secondLine);
		textView.setText(message);
		setContentView(textView);
		setContentView(R.layout.list_item);
	}

}
