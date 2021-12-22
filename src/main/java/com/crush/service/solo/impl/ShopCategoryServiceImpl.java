package com.crush.service.solo.impl;

import com.crush.entity.bo.ShopCategory;
import com.crush.entity.dto.Result;
import com.crush.service.solo.ShopCategoryService;
import org.smallSpring.core.annotaion.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
