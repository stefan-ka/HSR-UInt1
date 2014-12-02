package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.text.DateFormat;
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
import ch.hsr.uint1.whitespace.library.client.android.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class ReservationenFragment extends Fragment {
	private ReservationAdapter reservationAdapter;
	private ListView listview;

	@Override
	public void onStart() {
		super.onStart();
		loadReservations();
	}

	private void loadReservations() {
		LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {

			@Override
			public void notfiy(List<Reservation> input) {
				reservationAdapter.clear();
				reservationAdapter.addAll(input);
				reservationAdapter.notifyDataSetChanged();
			}

		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_reservationen, container, false);
		reservationAdapter = new ReservationAdapter(container.getContext(), R.layout.list_item, new ArrayList<Reservation>());
		listview = (ListView) rootView.findViewById(R.id.listView_reservieren_tab);
		listview.setAdapter(reservationAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Reservation reservation = reservationAdapter.getItem(position);
				System.out.println("Gadget reserviert: " + reservation.getGadget().getName());
			}
		});
		return rootView;
	}

	private class ReservationAdapter extends ArrayAdapter<Reservation> {
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
}
