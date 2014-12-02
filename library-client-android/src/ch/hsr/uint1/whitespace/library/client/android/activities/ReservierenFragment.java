package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class ReservierenFragment extends Fragment {

	private GadgetAdapter gadgetAdapter;
	private ListView listview;

	@Override
	public void onStart() {
		super.onStart();
		LibraryService.getGadgets(new Callback<List<Gadget>>() {

			@Override
			public void notfiy(List<Gadget> input) {
				gadgetAdapter.clear();
				gadgetAdapter.addAll(input);
				gadgetAdapter.notifyDataSetChanged();
			}

		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_reservieren, container, false);

		gadgetAdapter = new GadgetAdapter(container.getContext(), R.layout.list_item, new ArrayList<Gadget>());
		listview = (ListView) rootView.findViewById(R.id.listView_reservieren_tab);
		listview.setAdapter(gadgetAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Gadget gadget = gadgetAdapter.getItem(position);
				System.out.println("Gadget reserviert: " + gadget.getName());
			}
		});
		return rootView;
	}

	private class GadgetAdapter extends ArrayAdapter<Gadget> {

		public GadgetAdapter(Context context, int resource, List<Gadget> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Gadget gadget = getItem(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
			}

			TextView gadgetName = (TextView) convertView.findViewById(R.id.gadget_name);
			TextView date = (TextView) convertView.findViewById(R.id.date_line);

			gadgetName.setText(gadget.getName());
			date.setText("");

			return convertView;
		}

	}

}