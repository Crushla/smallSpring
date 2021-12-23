package org.smallSpring.mvc.processor;

import org.smallSpring.mvc.RequestProcessorChain;

public interface RequestProcessor {
    boolean process(RequestProcessorChain requestProcessorChain);
}
