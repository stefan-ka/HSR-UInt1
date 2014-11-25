package ch.hsr.uint1.whitespace.library.client.android.activities;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;
import ch.hsr.uint1.whitespace.library.client.android.listeners.CommonSubmitFormListener;
import ch.hsr.uint1.whitespace.library.client.android.util.PropertyReader;

import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;

public class LoginActivity extends CommonActivity {

	@Required(order = 1, message = "login_msg_email_required")
	@InjectView(R.id.email_editText)
	EditText emailField;

	@Password(order = 2, message = "login_msg_password_required")
	@InjectView(R.id.password_editText)
	EditText passwordField;

	@InjectView(R.id.login_button)
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		loginButton.setOnClickListener(new LoginListener(this));
	}

	@Override
	protected void onStart() {
		PropertyReader.initProperties(this);
		super.onStart();
	}

	public void createRegisterActivity(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

	private class LoginListener extends CommonSubmitFormListener {

		public LoginListener(CommonActivity context) {
			super(context);
		}

		@Override
		public void onValidationSucceeded() {
			LibraryService.login(emailField.getText().toString(), passwordField
					.getText().toString(), new Callback<Boolean>() {
				@Override
				public void notfiy(Boolean success) {
					if (success)
						Toast.makeText(context,
								R.string.login_message_successfull,
								Toast.LENGTH_LONG).show();
					else
						Toast.makeText(context,
								R.string.login_message_not_successfull,
								Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}
