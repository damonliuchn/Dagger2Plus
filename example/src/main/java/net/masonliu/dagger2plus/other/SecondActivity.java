package net.masonliu.dagger2plus.other;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import net.masonliu.dagger2plus.MyApplication;
import net.masonliu.dagger2plus.R;

import javax.inject.Inject;


public class SecondActivity extends Activity {

    @Inject
    SharedPreferences networkStateManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getComponent().inject(this);

        MyApplication.getComponent().provideTestManager().show(this);

    }
}
