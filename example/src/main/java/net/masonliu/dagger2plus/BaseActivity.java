package net.masonliu.dagger2plus;

import android.app.Activity;
import android.os.Bundle;

import net.masonliu.dagger2plus.library.DaggerInjector;

public class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyApplication.getComponent().inject(this);
        DaggerInjector.getInstance().inject(this);
    }
}
