package com.yun.task;

import com.yun.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ author: zyk
 * @ description:定时任务类
 * @ date: 2021/12/2 16:28
 * @ param:
 * @ return:
 */
@Component
@Slf4j
public class ScheduleTask {
    @Autowired
    private TesterService testerService;

    @Scheduled(fixedRate = 1000*60*60)
    public void showName(){
        //删除无用的测试者1：没有测试结果并且离测试开始时间超过1一个小时的
        log.debug("删除无用的测试者1：没有测试结果并且离测试开始时间超过1一个小时的"+new Date());
        List<Long> idList = new ArrayList<>();
        // delete from tbl_tester where id not in (select tester_id from tbl_test_result) and TIME_TO_SEC(timediff(now(),create_time))>3600;
        testerService.deleteNoResult();

    }
}
