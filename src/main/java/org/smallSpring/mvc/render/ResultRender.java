package org.smallSpring.mvc.render;

import org.smallSpring.mvc.RequestProcessorChain;

public interface ResultRender {
    //执行渲染
    void render(RequestProcessorChain requestProcessorChain);
}
