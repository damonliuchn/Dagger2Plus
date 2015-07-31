package net.masonliu.dagger2plus.compiler;

import com.google.auto.service.AutoService;

import java.util.LinkedList;

import javax.annotation.processing.Processor;

import processorworkflow.AbstractProcessing;
import processorworkflow.AbstractProcessor;
import processorworkflow.AbstractState;

/**
 * Created by MasonLiu on 7/30/15.
 */
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor<AbstractState> {

    @Override
    protected AbstractState processingState() {
        return null;
    }

    @Override
    protected LinkedList<AbstractProcessing> processings() {
        LinkedList<AbstractProcessing> processings = new LinkedList<>();
        processings.add(new AutoComponentInterfaceProcessing(elements, types, errors, state));
        return processings;
    }

    public AnnotationProcessor() {

    }

}
