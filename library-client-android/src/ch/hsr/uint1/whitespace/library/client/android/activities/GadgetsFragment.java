package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.adapters.GadgetAdapter;
import ch.hsr.uint1.whitespace.library.client.android.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.android.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class GadgetsFragment extends CommonFragment {

	private GadgetAdapter gadgetAdapter;

	@InjectView(R.id.listView_gadgets_tab)
	private ListView listview;

	@InjectView(R.id.button_reservieren_gadgets_tab)
	private Button reservierenButton;

	@InjectView(R.id.gadgets_swipe)
	private SwipeRefreshLayout refreshLayout;

	@Override
	public void onStart() {
		super.onStart();
		loadGadgets();
	}

	private void loadGadgets() {
		LibraryService.getGadgets(new Callback<List<Gadget>>() {
			@Override
			public void notfiy(List<Gadget> input) {
				gadgetAdapter.clear();
				gadgetAdapter.addAll(input);
				gadgetAdapter.notifyDataSetChanged();
				refreshLayout.setRefreshing(false);
			}
		});
	}

	@Override
	public void onFragmentVisible() {
		super.onFragmentVisible();
		loadGadgets();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_gadgets, container, false);
		gadgetAdapter = new GadgetAdapter(container.getContext(), R.layout.list_item, new ArrayList<Gadget>());
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listview.setAdapter(gadgetAdapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setItemsCanFocus(false);
		refreshLayout.setOnRefreshListener(new OnRefreshListener());
		reservierenButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reserveSelectedGadgets();
			}
		});

	}

	private List<Gadget> getSelectedGadgets() {
		List<Gadget> list = new ArrayList<Gadget>();
		SparseBooleanArray checked = listview.getCheckedItemPositions();
		for (int i = 0; i < listview.getCount(); i++) {
			if (checked.get(i)) {
				list.add(gadgetAdapter.getItem(i));
			}
		}
		return list;
	}

	private void reserveSelectedGadgets() {
		List<Gadget> gadgets = getSelectedGadgets();
		if (gadgets.size() > 3) {
			Toast.makeText(getActivity(), R.string.max_3_reservations_message, Toast.LENGTH_LONG).show();
			return;
		}
		for (int i = 0; i < gadgets.size(); i++) {
			Gadget gadget = gadgets.get(i);
			if (i == (gadgets.size() - 1)) {
				LibraryService.reserveGadget(gadget, new Callback<List<Loan>>() {
					@Override
					public void notfiy(List<Loan> loans) {
						listview.clearChoices();
						ActionBar actionBar = getActivity().getActionBar();
						actionBar.selectTab(actionBar.getTabAt(2));
					}
				});
			} else {
				LibraryService.reserveGadget(gadget, createEmptyCallBack());
			}
		}
	}

	private Callback<List<Loan>> createEmptyCallBack() {
		Callback<List<Loan>> callback = new Callback<List<Loan>>() {
			@Override
			public void notfiy(List<Loan> input) {
				// do nothing
			}
		};
		return callback;
	}

	private class OnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
		public void onRefresh() {
			loadGadgets();
		}
	}

}