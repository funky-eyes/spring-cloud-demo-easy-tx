package icu.funkye.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import icu.funkye.entity.Test;
import icu.funkye.mapper.TestMapper;
import icu.funkye.service.ITestService;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    @Override
    @Transactional
    public Object Commit(Integer id) {
        update(Wrappers.<Test>lambdaUpdate().eq(Test::getId, id).setSql("two=two+1"));
        return true;
    }

}
