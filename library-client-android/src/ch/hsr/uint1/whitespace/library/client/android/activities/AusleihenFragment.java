package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.adapters.AusleihenAdapter;
import ch.hsr.uint1.whitespace.library.client.android.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class AusleihenFragment extends CommonFragment {

	private AusleihenAdapter ausleihenAdapter;

	@InjectView(R.id.listView_ausleihen_tab)
	private ListView listview;

	@Override
	public void onStart() {
		super.onStart();
	}

	private void loadLoans() {
		LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
			@Override
			public void notfiy(List<Loan> input) {
				ausleihenAdapter.clear();
				ausleihenAdapter.addAll(input);
				ausleihenAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onFragmentVisible() {
		super.onFragmentVisible();
		loadLoans();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.frament_ausleihen, container, false);
		ausleihenAdapter = new AusleihenAdapter(container.getContext(), R.layout.list_item, new ArrayList<Loan>());
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listview.setAdapter(ausleihenAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Loan loan = ausleihenAdapter.getItem(position);
				System.out.println("Loan loaned: " + loan.getGadget().getName());
			}
		});
	}
}