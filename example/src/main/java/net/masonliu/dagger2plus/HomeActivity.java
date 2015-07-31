package net.masonliu.dagger2plus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

    @Inject
    SharedPreferences networkStateManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (networkStateManager != null) {
            Toast.makeText(this, "Hello world", Toast.LENGTH_LONG).show();
        }

    }
}
