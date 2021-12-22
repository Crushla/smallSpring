package com.crush.controller.frontend;

import com.crush.entity.dto.MainPageInfoDTO;
import com.crush.entity.dto.Result;
import com.crush.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.smallSpring.core.annotaion.Controller;
import org.smallSpring.inject.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@Getter
public class MainPageController {
    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
