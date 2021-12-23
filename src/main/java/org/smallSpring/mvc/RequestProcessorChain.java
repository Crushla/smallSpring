package org.smallSpring.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.smallSpring.mvc.processor.RequestProcessor;
import org.smallSpring.mvc.render.DefaultResultRender;
import org.smallSpring.mvc.render.InternalErrorResultRender;
import org.smallSpring.mvc.render.ResultRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
@Data
@Slf4j
public class RequestProcessorChain {
    private Iterator<RequestProcessor>requestProcessorIterator;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String requestMethod;
    private String requestPath;
    private int responseCode;
    private ResultRender resultRender;
    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest req, HttpServletResponse resp) {
        requestProcessorIterator = iterator;
        request = req;
        response = resp;
        requestMethod = req.getMethod();
        requestPath = req.getPathInfo();
        responseCode = HttpServletResponse.SC_OK;
    }

    public void doRequestProcessorChain() {
        try {
            while (requestProcessorIterator.hasNext()) {
                if (!requestProcessorIterator.next().process(this)) {
                    break;
                }
            }
        }catch (Exception e){
            this.resultRender = new InternalErrorResultRender();
            log.error("doRequestProcessorChain error:",e);
        }
    }

    public void doRender() {
        if (resultRender == null) {
            resultRender = new DefaultResultRender();
        }
        try {
            resultRender.render(this);
        }catch (Exception e){
            log.error("doRender error:",e);
            throw new RuntimeException(e);
        }
    }
}
