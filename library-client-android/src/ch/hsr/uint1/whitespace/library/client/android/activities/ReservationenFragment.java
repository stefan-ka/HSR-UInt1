package ch.hsr.uint1.whitespace.library.client.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ch.hsr.uint1.whitespace.library.client.android.R;

public class ReservationenFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_reservationen, container, false);
		
		return rootView;
	}
}
