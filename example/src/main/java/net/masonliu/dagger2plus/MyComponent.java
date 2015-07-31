package net.masonliu.dagger2plus;

import net.masonliu.dagger2plus.library.AutoComponentInterface;

import javax.inject.Singleton;

import dagger.Component;

@AutoComponentInterface
@Singleton
@Component(modules = {SystemServicesModule.class})//这里是provider
public interface MyComponent extends DaggerMyComponentInterface {
    //这里是Injector

}
