package com.crush.controller.superadmin;

import com.crush.entity.bo.ShopCategory;
import com.crush.entity.dto.Result;
import com.crush.service.solo.ShopCategoryService;
import org.smallSpring.core.annotaion.Controller;
import org.smallSpring.inject.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class ShopCategoryOperationController {
    @Autowired
    ShopCategoryService shopCategoryService;

    public Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        return shopCategoryService.addShopCategory(new ShopCategory());
    }

    public Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        return shopCategoryService.removeShopCategory(1);
    }

    public Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }

    public Result<Boolean> queryShopCategoryById(HttpServletRequest req, HttpServletResponse resp) {
        return shopCategoryService.queryShopCategoryById(1);
    }

    public Result<List<ShopCategory>> queryShopCategory(HttpServletRequest req, HttpServletResponse resp) {
        return shopCategoryService.queryShopCategory(new ShopCategory(),1,1);
    }
}
