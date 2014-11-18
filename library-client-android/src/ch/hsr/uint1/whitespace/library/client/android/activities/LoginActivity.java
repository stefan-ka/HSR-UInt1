package ch.hsr.uint1.whitespace.library.client.android.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ch.hsr.uint1.whitespace.library.client.android.R;

public class LoginActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void createRegisterActivity(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
    }

    public void createMainViewActivity(View view) {
    		Intent intent = new Intent(this, MainViewActivity.class);
    		startActivity(intent);
        }
    
}
