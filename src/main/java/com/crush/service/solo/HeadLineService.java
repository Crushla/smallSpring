package com.crush.service.solo;

import com.crush.entity.bo.HeadLine;
import com.crush.entity.dto.Result;

import java.util.List;

public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLineId);
    Result<Boolean> queryHeadLineById(int headLineId);
    Result<List<HeadLine>>queryHeadLine(HeadLine headLineCondition,int pageIndex, int pageSize);
}
