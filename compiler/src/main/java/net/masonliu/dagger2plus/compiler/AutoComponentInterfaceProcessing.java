package net.masonliu.dagger2plus.compiler;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.collect.ImmutableSet;

import net.masonliu.dagger2plus.library.AutoComponentInterface;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.inject.Inject;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import dagger.Provides;
import processorworkflow.AbstractComposer;
import processorworkflow.AbstractProcessing;
import processorworkflow.AbstractState;
import processorworkflow.Errors;

/**
 * Created by MasonLiu on 7/30/15.
 */
public class AutoComponentInterfaceProcessing extends AbstractProcessing<AutoComponentInterfaceSpec, AbstractState> {


    private AutoComponentInterfaceSpec mainComponentSpec;

    public AutoComponentInterfaceProcessing(Elements elements, Types types, Errors errors, AbstractState state) {
        super(elements, types, errors, state);
        mainComponentSpec = new AutoComponentInterfaceSpec();
    }

    @Override
    public Set<Class<? extends Annotation>> supportedAnnotations() {
        Set set = ImmutableSet.of(AutoComponentInterface.class, Inject.class, Provides.class);
        return set;
    }

    @Override
    public boolean processElement(Element element, Errors.ElementErrors elementErrors) {
        if (ElementKind.INTERFACE.equals(element.getKind()) && processedAnnotation.equals(AutoComponentInterface.class)) {
            PackageElement packageElement = MoreElements.getPackage(element);
            mainComponentSpec.setClassName(element.getSimpleName().toString());
            mainComponentSpec.setPackName(packageElement.getQualifiedName().toString());
        } else if (ElementKind.FIELD.equals(element.getKind()) && processedAnnotation.equals(Inject.class)) {
            mainComponentSpec.setInjectMethod(element.getEnclosingElement().asType());
        } else if (ElementKind.METHOD.equals(element.getKind()) && processedAnnotation.equals(Provides.class)) {
            ExecutableElement executableElement = MoreElements.asExecutable(element);
            Element returnElement = MoreTypes.asElement(executableElement.getReturnType());
            mainComponentSpec.setProvideMethod(returnElement.asType());
        } else if ((ElementKind.CONSTRUCTOR.equals(element.getKind()) || ElementKind.METHOD.equals(element.getKind()))
                && processedAnnotation.equals(Inject.class)) {
            mainComponentSpec.setProvideMethod(element.getEnclosingElement().asType());
        }
        return true;
    }

    @Override
    public AbstractComposer<AutoComponentInterfaceSpec> createComposer() {
        specs.add(mainComponentSpec);
        return new AutoComponentInterfaceComposer(specs);
    }

}
