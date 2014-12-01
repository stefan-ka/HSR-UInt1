package ch.hsr.uint1.whitespace.library.client.android.activities;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;

public class AusleihenFragment extends Fragment {
	private AusleihenAdapter ausleihenAdapter;
	private ListView listview;

	@Override
	public void onStart() {
		super.onStart();
		loadReservations();
	}

	private void loadReservations() {
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View rootView = inflater.inflate(R.layout.frament_ausleihen, container, false);

		ausleihenAdapter = new AusleihenAdapter(container.getContext(), R.layout.list_item, new ArrayList<Loan>());
		listview = (ListView) rootView.findViewById(R.id.listView_ausleihen_tab);
		listview.setAdapter(ausleihenAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Loan loan = ausleihenAdapter.getItem(position);
				System.out.println("Loan loaned: " + loan.getGadget().getName());
			}
		});
		return rootView;
	}

	private class AusleihenAdapter extends ArrayAdapter<Loan> {
		private final String UEBERFEAELLIG_AM = "Überfällig am: ";

		public AusleihenAdapter(Context context, int resource, List<Loan> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Loan loan = getItem(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
			}

			TextView gadgetName = (TextView) convertView.findViewById(R.id.gadget_name);
			TextView date = (TextView) convertView.findViewById(R.id.date_line);
			gadgetName.setText(loan.getGadget().getName());
			DateFormat dataFormat = DateFormat.getDateInstance();
			date.setText(UEBERFEAELLIG_AM + dataFormat.format(loan.overDueDate()));

			return convertView;
		}
	}
}