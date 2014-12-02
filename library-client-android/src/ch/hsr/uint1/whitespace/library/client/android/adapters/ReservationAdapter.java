package ch.hsr.uint1.whitespace.library.client.android.adapters;

import java.text.DateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Reservation;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
	
	private final String AUSGELIEHEN_BIS = "Ausgeliehen bis: ";

	public ReservationAdapter(Context context, int resource, List<Reservation> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Reservation reservation = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
		}

		TextView gadgetName = (TextView) convertView.findViewById(R.id.gadget_name);
		TextView date = (TextView) convertView.findViewById(R.id.date_line);
		gadgetName.setText(reservation.getGadget().getName());
		DateFormat dataFormat = DateFormat.getDateInstance();
		date.setText(AUSGELIEHEN_BIS + dataFormat.format(reservation.getReservationDate()));

		return convertView;
	}

}