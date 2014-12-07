package ch.hsr.uint1.whitespace.library.client.android.activities;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.adapters.TabsPagerAdapter;

public class MainViewActivity extends RoboFragmentActivity implements TabListener {

	@InjectView(R.id.pager)
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private static final int[] TAB_STRING_IDS = { R.string.tab_gadgets, R.string.tab_ausleihen, R.string.tab_reservationen };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview_activity);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getActionBar(), getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int tabStringId : TAB_STRING_IDS) {
			actionBar.addTab(actionBar.newTab().setText(getString(tabStringId)).setTabListener(this));
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
}
