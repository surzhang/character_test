package com.yun.controller;


import cn.hutool.core.util.ObjectUtil;
import com.yun.entity.ReturnBean;
import com.yun.entity.TestResult;
import com.yun.entity.Tester;
import com.yun.entity.TesterVo;
import com.yun.service.MailService;
import com.yun.service.TestResultService;
import com.yun.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TestResult)表控制层
 * @author makejava
 * @since 2021-12-02 14:37:04
 */
@RestController
@RequestMapping("testResult")
public class TestResultController extends BaseController {

    @Resource
    private TestResultService testResultService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TesterService testerService;

    /**
     * @ author: zyk
     * @ description:批量保存测试结果
     * @ date: 2021/12/4 18:52
     * @ param: [resultList]
     * @ return: com.yun.entity.ReturnBean
     */
    @PostMapping("saveBatchTestResult")
    public ReturnBean saveBatchTestResult(@RequestBody List<TestResult> resultList, HttpSession session){
        boolean saveBatch = testResultService.saveBatch(resultList);
        Tester tester = (Tester) session.getAttribute("tester");
        StringBuilder sb=new StringBuilder();
        sb.append(tester.getPhone()).append("@139.com");
        String email = sb.toString();
        if (saveBatch) {
            //单独开辟一个线程发送邮件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TesterVo testerVo = testerService.selectTesterVoByTesterId(tester.getId());
                    Map<String,Integer> map = new HashMap<>();
                    if(ObjectUtil.isNotEmpty(testerVo.getBlueCount())){
                        map.put("蓝色性格",testerVo.getBlueCount());
                    }else{
                        map.put("蓝色性格",0);
                    }
                    if(ObjectUtil.isNotEmpty(testerVo.getRedCount())){
                        map.put("红色性格",testerVo.getRedCount());
                    }else{
                        map.put("红色性格",0);
                    }
                    if(ObjectUtil.isNotEmpty(testerVo.getYellowCount())){
                        map.put("黄色性格",testerVo.getYellowCount());
                    }else{
                        map.put("黄色性格",0);
                    }
                    if(ObjectUtil.isNotEmpty(testerVo.getGreenCount())){
                        map.put("绿色性格",testerVo.getGreenCount());
                    }else{
                        map.put("绿色性格",0);
                    }

                    //3296252248@qq.com
                    mailService.send(email,"模拟",map);
                }
            }).start();
            session.removeAttribute("tester");
            return success(null);
        } else {
            return fail(null);
        }
    }
}
