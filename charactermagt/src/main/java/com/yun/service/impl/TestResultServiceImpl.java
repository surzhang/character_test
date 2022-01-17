package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.TestResultDao;
import com.yun.entity.TestResult;
import com.yun.service.TestResultService;
import org.springframework.stereotype.Service;

/**
 * (TestResult)表服务实现类
 *
 * @author makejava
 * @since 2021-12-02 14:37:08
 */
@Service("testResultService")
public class TestResultServiceImpl extends ServiceImpl<TestResultDao, TestResult> implements TestResultService {

}

