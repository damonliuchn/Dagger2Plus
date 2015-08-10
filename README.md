# Dagger2Plus

A extension for Dagger2

# Feature

1、Pure Dagger2 code

2、auto generate injecter function for component interface

3、run inject function in a base Activity or Object


# Usage
```java

@AutoComponentInterface
@Singleton
@Component(modules = {SystemServicesModule.class})
public interface MyComponent extends DaggerMyComponentInterface {
    //don't need write any inject function,this library will do it for you
}

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

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //don't need to run inject function in every activity,only run following code
        DaggerInjector.getInstance().inject(this);
    }
}

public class HomeActivity extends BaseActivity {

    @Inject
    SharedPreferences networkStateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //direct use
        Toast.makeText(this, "Hello world"+networkStateManager.toString(), Toast.LENGTH_LONG).show();
        //or get a object programmatically
        TestManager testManager = MyApplication.getComponent().provideTestManager();
    }
}
```

```java
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    compile 'com.github.MasonLiuChn:Dagger2Plus:1.0.0'
}
```


-----
Blog:http://blog.csdn.net/masonblog

Email:MasonLiuChn@gmail.com
