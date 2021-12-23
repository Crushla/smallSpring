package org.smallSpring.mvc.processor.Impl;

import org.smallSpring.mvc.RequestProcessorChain;
import org.smallSpring.mvc.processor.RequestProcessor;

public class JspRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) {
        return false;
    }
}
