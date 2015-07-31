package net.masonliu.dagger2plus.other;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestManager {

    @Inject
    public TestManager() {
    }

    public void show(Context context) {
        Toast.makeText(context, "TestManager not null", Toast.LENGTH_SHORT).show();
    }
}
