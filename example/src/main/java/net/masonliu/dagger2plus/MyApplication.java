package net.masonliu.dagger2plus;

import android.app.Application;

import net.masonliu.dagger2plus.library.DaggerInjector;

public class MyApplication extends Application {

    public MyComponent component;
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    public void initDagger() {
        component = DaggerMyComponent.builder()
                .systemServicesModule(new SystemServicesModule(this))
                .build();
        DaggerInjector.getInstance().init(component);
    }

    public static MyComponent getComponent() {
        return instance.component;
    }
}
