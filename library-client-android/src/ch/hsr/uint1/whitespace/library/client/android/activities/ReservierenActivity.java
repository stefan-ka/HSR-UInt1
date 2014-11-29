package ch.hsr.uint1.whitespace.library.client.android.activities;

import ch.hsr.uint1.whitespace.library.client.android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReservierenActivity extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_reservieren, container, false);
		
		return rootView;
	}
}