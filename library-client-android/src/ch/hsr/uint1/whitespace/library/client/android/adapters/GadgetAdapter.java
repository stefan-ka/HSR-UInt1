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
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}
		TextView gadgetName = (TextView) convertView.findViewById(R.id.gadget_name);
		TextView additionalInformation = (TextView) convertView.findViewById(R.id.additional_line);
		gadgetName.setText(gadget.getName());
		additionalInformation.setText("");
		return convertView;
	}

}