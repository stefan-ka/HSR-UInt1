package ch.hsr.uint1.whitespace.library.client.android.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Ausleihen activity
			return new AusleihenFragment();
		case 1:
			// Reservationen activity
			return new ReservationenFragment();
		case 2:
			// Reservieren activity
			return new ReservierenActivity();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
