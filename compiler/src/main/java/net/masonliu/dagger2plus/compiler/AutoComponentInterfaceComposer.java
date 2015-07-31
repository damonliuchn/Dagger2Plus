package net.masonliu.dagger2plus.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

import processorworkflow.AbstractComposer;

/**
 * Created by MasonLiu on 7/30/15.
 */
public class AutoComponentInterfaceComposer extends AbstractComposer<AutoComponentInterfaceSpec> {

    public AutoComponentInterfaceComposer(List<AutoComponentInterfaceSpec> specs) {
        super(specs);
    }

    @Override
    protected JavaFile compose(AutoComponentInterfaceSpec componentSpec) {
        TypeSpec.Builder helloWorld = TypeSpec.interfaceBuilder("Dagger" + componentSpec.getClassName() + "Interface")
                .addModifiers(Modifier.PUBLIC);

        for (TypeMirror injectMethod : componentSpec.getInjectMethods()) {
            MethodSpec main = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .returns(void.class)
                    .addParameter(TypeName.get(injectMethod), "arg")
                    .build();
            helloWorld.addMethod(main);
        }

        for (TypeMirror provideMethod : componentSpec.getProvideMethods()) {
            ClassName className = (ClassName) TypeName.get(provideMethod);
            MethodSpec main = MethodSpec.methodBuilder("provide" + className.simpleName())
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                    .returns(TypeName.get(provideMethod))
                    .build();
            helloWorld.addMethod(main);
        }

        return JavaFile.builder(componentSpec.getPackName(), helloWorld.build()).build();
    }
}
