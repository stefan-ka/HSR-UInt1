package ch.hsr.uint1.whitespace.library.client.android.adapters;

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

	public ReservationAdapter(Context context, int resource, List<Reservation> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Reservation reservation = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}
		TextView gadgetName = (TextView) convertView.findViewById(R.id.gadget_name);
		TextView additionalInformation = (TextView) convertView.findViewById(R.id.additional_line);
		gadgetName.setText(reservation.getGadget().getName());
		if (reservation.isReady()) {
			additionalInformation.setText(getContext().getString(R.string.reservation_gadget_is_ready));
		} else {
			additionalInformation.setText(getContext().getString(R.string.reservation_gadget_waiting_position) + " " + reservation.getWatingPosition());
		}
		return convertView;
	}

}