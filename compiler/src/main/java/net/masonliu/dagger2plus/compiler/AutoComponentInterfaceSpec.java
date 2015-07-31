package net.masonliu.dagger2plus.compiler;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.type.TypeMirror;

/**
 * Created by MasonLiu on 7/30/15.
 */
public class AutoComponentInterfaceSpec {

    private String className;
    private String packName;
    private Set<TypeMirror> injectMethods = new HashSet<>();
    private Set<TypeMirror> provideMethods = new HashSet<>();

    public AutoComponentInterfaceSpec() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Set<TypeMirror> getInjectMethods() {
        return injectMethods;
    }

    public void setInjectMethod(TypeMirror injectMethod) {
        this.injectMethods.add(injectMethod);
    }

    public Set<TypeMirror> getProvideMethods() {
        return provideMethods;
    }

    public void setProvideMethod(TypeMirror provideMethod) {
        this.provideMethods.add(provideMethod);
    }
}
