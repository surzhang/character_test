package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.LogDao;
import com.yun.entity.Log;
import com.yun.service.LogService;
import org.springframework.stereotype.Service;

/**
 * (Log)表服务实现类
 *
 * @author makejava
 * @since 2021-12-05 12:34:56
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements LogService {

}

