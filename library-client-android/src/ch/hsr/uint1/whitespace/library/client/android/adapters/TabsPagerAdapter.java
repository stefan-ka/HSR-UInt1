package ch.hsr.uint1.whitespace.library.client.android.adapters;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import ch.hsr.uint1.whitespace.library.client.android.activities.AusleihenFragment;
import ch.hsr.uint1.whitespace.library.client.android.activities.CommonFragment;
import ch.hsr.uint1.whitespace.library.client.android.activities.GadgetsFragment;
import ch.hsr.uint1.whitespace.library.client.android.activities.ReservationenFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter implements OnPageChangeListener {

	private ActionBar actionBar;
	private SparseArray<CommonFragment> fragmentsMap;

	public TabsPagerAdapter(ActionBar actionbar, FragmentManager fragmentManager) {
		super(fragmentManager);
		this.actionBar = actionbar;
		fragmentsMap = new SparseArray<CommonFragment>();
		initFragments();
	}

	private void initFragments() {
		fragmentsMap.put(0, new GadgetsFragment());
		fragmentsMap.put(1, new AusleihenFragment());
		fragmentsMap.put(2, new ReservationenFragment());
	}

	@Override
	public Fragment getItem(int index) {
		return fragmentsMap.get(index);
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
		fragmentsMap.get(position).onFragmentVisible();
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// nothing to do here
	}

}
