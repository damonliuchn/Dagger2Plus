package net.masonliu.dagger2plus.library;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumeng on 7/30/15.
 */
public class DaggerInjector {
    private Map<Class<?>, Method> methodMap;
    private Object component;

    private static volatile DaggerInjector instance;

    public static DaggerInjector getInstance() {
        if (instance == null) {
            synchronized (DaggerInjector.class) {
                if (instance == null) {
                    instance = new DaggerInjector();
                }
            }
        }
        return instance;
    }

    private DaggerInjector() {

    }

    public void init(Object component) {
        this.component = component;
        methodMap = new HashMap<>();
        Method[] methods = component.getClass().getMethods();
        for (Method m : methods) {
            Class[] types = m.getParameterTypes();
            if (types.length == 1) {
                methodMap.put(types[0], m);
            }
        }
    }

    public void inject(Object object) {
        if (methodMap == null) {
            throw new RuntimeException("DaggerInjector not init");
        }
        try {
            Method method = methodMap.get(object.getClass());
            method.invoke(component, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
