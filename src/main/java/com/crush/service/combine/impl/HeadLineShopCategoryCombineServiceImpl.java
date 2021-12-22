package com.crush.service.combine.impl;

import com.crush.entity.bo.HeadLine;
import com.crush.entity.bo.ShopCategory;
import com.crush.entity.dto.MainPageInfoDTO;
import com.crush.entity.dto.Result;
import com.crush.service.combine.HeadLineShopCategoryCombineService;
import com.crush.service.solo.HeadLineService;
import com.crush.service.solo.ShopCategoryService;
import org.smallSpring.core.annotaion.Service;
import org.smallSpring.inject.Autowired;

import java.util.List;
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);

        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);

        Result<MainPageInfoDTO>result = mergeMainPageInfoResult(headLineResult,shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
