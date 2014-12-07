package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.adapters.ReservationAdapter;
import ch.hsr.uint1.whitespace.library.client.android.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class ReservationenFragment extends CommonFragment {

	private ReservationAdapter reservationAdapter;

	@InjectView(R.id.listView_reservieren_tab)
	private ListView listview;

	@InjectView(R.id.button_reservationen_loeschen)
	private Button loeschenButton;

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onFragmentVisible() {
		super.onFragmentVisible();
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
		loeschenButton.setOnClickListener(new DeleteReservationsListener());
	}

	private class DeleteReservationsListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			SparseBooleanArray checked = listview.getCheckedItemPositions();
			for (int i = 0; i < listview.getCount(); i++) {
				if (checked.get(i)) {
					Reservation reservation = reservationAdapter.getItem(i);
					LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
						@Override
						public void notfiy(Boolean input) {
							loadReservations();
						}
					});
				}
			}
			listview.clearChoices();
		}
	}

}
