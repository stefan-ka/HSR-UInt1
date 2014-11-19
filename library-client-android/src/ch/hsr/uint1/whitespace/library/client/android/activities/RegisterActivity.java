package ch.hsr.uint1.whitespace.library.client.android.activities;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ch.hsr.uint1.whitespace.library.client.android.R;
import ch.hsr.uint1.whitespace.library.client.android.library.Callback;
import ch.hsr.uint1.whitespace.library.client.android.library.LibraryService;
import ch.hsr.uint1.whitespace.library.client.android.listeners.CommonSubmitFormListener;

import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

public class RegisterActivity extends CommonActivity {

	@Required(order = 1, message = "register_name_required")
	@InjectView(R.id.name_register_textEdit)
	private EditText nameField;

	@Required(order = 2, message = "register_email_required")
	@Email(order = 3, message = "register_email_correct")
	@InjectView(R.id.email_register_textEdit)
	private EditText emailField;

	@Required(order = 4, message = "register_matrikel_required")
	@InjectView(R.id.matrikel_register_textEdit)
	private EditText matrikelField;

	@Password(order = 6, message = "register_password_required")
	@TextRule(order = 7, minLength = 8, message = "register_password_min_lenght_8")
	@InjectView(R.id.password1_register_textEdit)
	private EditText passwort1Field;

	@ConfirmPassword(order = 8, message = "password_mismatch")
	@InjectView(R.id.password2_register_textEdit)
	private EditText password2Field;

	@InjectView(R.id.register_button2)
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		registerButton.setOnClickListener(new RegisterListener(this));
	}

	private class RegisterListener extends CommonSubmitFormListener {

		public RegisterListener(CommonActivity context) {
			super(context);
		}

		@Override
		public void onValidationSucceeded() {
			LibraryService.register(emailField.getText().toString(), passwort1Field.getText().toString(), nameField.getText().toString(), matrikelField.getText().toString(),
					new Callback<Boolean>() {
						@Override
						public void notfiy(Boolean success) {
							if (success) {
								Toast.makeText(context, R.string.registration_successfull, Toast.LENGTH_LONG).show();
								startActivity(new Intent(context, LoginActivity.class));
							} else {
								Toast.makeText(context, R.string.registration_not_successfull, Toast.LENGTH_LONG).show();
							}
						}
					});
		}

	}
}
