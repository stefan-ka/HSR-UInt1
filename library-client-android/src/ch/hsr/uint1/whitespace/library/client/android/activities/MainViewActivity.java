package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.android.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class MainViewActivity extends FragmentActivity implements TabListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private String[] tabs = { "Ausleihen", "Reservationen", "Reservieren" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview_activity);
		viewPager = (ViewPager) findViewById(R.id.pager); // Can not be injected, Activity doesn't start
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

		LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {

			@Override
			public void notfiy(List<Reservation> input) {
				// todo fill rows reservation table
				System.out.println(input.toString());
			}
		});

		LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {

			@Override
			public void notfiy(List<Loan> input) {
				System.out.println(input.toString());
				// fill rows loans table

			}
		});

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
