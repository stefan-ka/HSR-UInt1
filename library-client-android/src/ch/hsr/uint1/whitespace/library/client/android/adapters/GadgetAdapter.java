package ch.hsr.uint1.whitespace.library.client.android.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Gadget;

public class GadgetAdapter extends ArrayAdapter<Gadget> {

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