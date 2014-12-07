package ch.hsr.uint1.whitespace.library.client.android.adapters;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import ch.hsr.uint1.whitespace.library.client.android.activities.AusleihenFragment;
import ch.hsr.uint1.whitespace.library.client.android.activities.ReservationenFragment;
import ch.hsr.uint1.whitespace.library.client.android.activities.GadgetsFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter implements OnPageChangeListener {

	private ActionBar actionBar;

	public TabsPagerAdapter(ActionBar actionbar, FragmentManager fragmentManager) {
		super(fragmentManager);
		this.actionBar = actionbar;
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new GadgetsFragment();
		case 1:
			return new AusleihenFragment();
		case 2:
			return new ReservationenFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// nothing to do here
	}

	@Override
	public void onPageSelected(int position) {
		actionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// nothing to do here
	}

}
