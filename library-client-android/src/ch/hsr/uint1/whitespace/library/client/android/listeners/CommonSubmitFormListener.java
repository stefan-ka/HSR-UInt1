package ch.hsr.uint1.whitespace.library.client.android.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import ch.hsr.uint1.whitespace.library.client.android.activities.CommonActivity;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

public abstract class CommonSubmitFormListener implements ValidationListener, OnClickListener {

	protected CommonActivity context;
	private Validator validator;

	public CommonSubmitFormListener(CommonActivity context) {
		this.context = context;
		validator = new Validator(context);
		validator.setValidationListener(this);
	}

	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {
		String errorMessage = context.getString(context.getResources().getIdentifier(failedRule.getFailureMessage(), "string", context.getPackageName()));
		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(errorMessage);
		} else {
			Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View view) {
		validator.validate();
	}

}
