package com.crush.service.solo;

import com.crush.entity.bo.ShopCategory;
import com.crush.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategoryId);
    Result<Boolean> queryShopCategoryById(int shopCategoryId);
    Result<List<ShopCategory>>queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
