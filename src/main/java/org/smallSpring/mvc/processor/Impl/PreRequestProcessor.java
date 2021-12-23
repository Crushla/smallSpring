package org.smallSpring.mvc.processor.Impl;

import org.smallSpring.mvc.RequestProcessorChain;
import org.smallSpring.mvc.processor.RequestProcessor;

public class PreRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) {
        return false;
    }
}
