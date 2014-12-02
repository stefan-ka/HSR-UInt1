package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.adapters.ReservationAdapter;
import ch.hsr.uint1.whitespace.library.client.android.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class ReservationenFragment extends RoboFragment {

	private ReservationAdapter reservationAdapter;

	@InjectView(R.id.listView_reservieren_tab)
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
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listview.setAdapter(reservationAdapter);
	}

}
