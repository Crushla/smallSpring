package org.smallSpring.mvc;

import com.crush.controller.frontend.MainPageController;
import com.crush.controller.superadmin.HeadLineOperationController;
import org.smallSpring.aop.AspectWeaver;
import org.smallSpring.core.BeanContainer;
import org.smallSpring.inject.DependencyInjector;
import org.smallSpring.mvc.processor.Impl.ControllerRequestProcessor;
import org.smallSpring.mvc.processor.Impl.JspRequestProcessor;
import org.smallSpring.mvc.processor.Impl.PreRequestProcessor;
import org.smallSpring.mvc.processor.Impl.StaticResourceRequestProcessor;
import org.smallSpring.mvc.processor.RequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    List<RequestProcessor>PROCESSOR= new ArrayList<>();
    @Override
    public void init(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.crush");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();

        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor());
        PROCESSOR.add(new JspRequestProcessor());
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSOR.iterator(), req, resp);
        requestProcessorChain.doRequestProcessorChain();
        requestProcessorChain.doRender();
    }
}
