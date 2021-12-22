package com.crush.entity.dto;

import com.crush.entity.bo.HeadLine;
import com.crush.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;
@Data
public class MainPageInfoDTO {
    private List<HeadLine>headLineList;
    private List<ShopCategory>shopCategoryList;
}
