package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.hsr.uint1.whitespace.library.client.android.R;

public class ReservierenActivity extends Fragment {
	private String[] values = new String[] { "test1", "test2", "test3" };

	private final ArrayList<String> list = new ArrayList<String>();
	private StableArrayAdapter adapter;
	private ListView listview;
	private Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_reservieren, container, false);
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}

		intent = new Intent(getActivity(), ListItemActivity.class);
		adapter = new StableArrayAdapter(container.getContext(), R.layout.list_item, list);
		listview = (ListView) rootView.findViewById(R.id.listView_ausleihen_tab);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				System.out.println("Clicked item: " + item);
				// intent.putExtra(EXTRA_MESSAGE, item);
				startActivity(intent);
			}
		});
		return rootView;
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
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