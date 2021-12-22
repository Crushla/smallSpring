package com.crush.service.solo.impl;

import com.crush.entity.bo.HeadLine;
import com.crush.entity.dto.Result;
import com.crush.service.solo.HeadLineService;
import org.smallSpring.core.annotaion.Service;

import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
