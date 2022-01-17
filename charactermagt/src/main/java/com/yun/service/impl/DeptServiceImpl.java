package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.DeptDao;
import com.yun.entity.Dept;
import com.yun.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author makejava
 * @since 2021-12-03 10:13:58
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}

