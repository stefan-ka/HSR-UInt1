package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.hsr.uint1.whitespace.library.client.android.R;

public class ListViewActivity extends Activity {
	protected static final String EXTRA_MESSAGE = "com.cuervo.myfirstapp.MESSAGE";

	private String[] values = new String[] { "Android", "iPhone",
			"WindowsMobile" };

	private final ArrayList<String> list = new ArrayList<String>();
	private StableArrayAdapter adapter;
	private ListView listview;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_activity);
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		intent = new Intent(this, DisplayListItemActivity.class);
		adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				System.out.println("Clicked item: " + item);
				intent.putExtra(EXTRA_MESSAGE, item);
				startActivity(intent);

				// view.animate().setDuration(2000).alpha(0)
				// .withEndAction(new Runnable() {
				// @Override
				// public void run() {
				// list.remove(item);
				// adapter.notifyDataSetChanged();
				// view.setAlpha(1);
				// }
				// });
			}

		});
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
