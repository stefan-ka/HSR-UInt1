package ch.hsr.uint1.whitespace.library.client.android.adapters;

import java.text.DateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.domain.Loan;

public class AusleihenAdapter extends ArrayAdapter<Loan> {

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
		TextView additionalInformation = (TextView) convertView.findViewById(R.id.additional_line);
		gadgetName.setText(loan.getGadget().getName());
		DateFormat dataFormat = DateFormat.getDateInstance();
		additionalInformation.setText(getContext().getString(R.string.loan_ueberfaellig_am) + " " + dataFormat.format(loan.overDueDate()));
		return convertView;
	}
}