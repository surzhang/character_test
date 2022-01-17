package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.CharacterA;
import com.yun.entity.Month;
import com.yun.entity.Tester;
import com.yun.entity.TesterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 性格测试者(Tester)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-02 14:38:21
 */
public interface TesterDao extends BaseMapper<Tester> {
    /**
     * @ author: zyk
     * @ description:删除无用的结果
     * @ date: 2021/12/9 15:47
     * @ param: []
     * @ return: void
     */
    void deleteNoResult();
    /**
     * @ author: zyk
     * @ description:保存测试者
     * @ date: 2021/12/9 15:48
     * @ param: [tester]
     * @ return: int
     */
    int saveTester(Tester tester);
    /**
     * @ author: zyk
     * @ description:校验手机号是否重复
     * @ date: 2021/12/9 15:48
     * @ param: [phone]
     * @ return: com.yun.entity.Tester
     */
    Tester checkPhone(String phone);
    int deleteTester(Integer id);
    int updateTester(Tester tester);
    Tester selectTesterById(Integer id);
    /**
     * @ author: zyk
     * @ description:展示表查询所有数据
     * @ date: 2021/12/9 15:47
     * @ param: [page, limit, tester]
     * @ return: java.util.List<com.yun.entity.TesterVo>
     */
    List<TesterVo> selectAllTester(@Param("page") Long page  , @Param("limit") Long limit, @Param("tester") Tester tester);
    /**
     * @ author: zyk
     * @ description:获取测试者总人数
     * @ date: 2021/12/9 15:47
     * @ param: [tester]
     * @ return: java.lang.Long
     */
    Long getCount(@Param("tester")Tester tester);
    /**
     * @ author: zyk
     * @ description:批量删除测试用户
     * @ date: 2021/12/9 15:46
     * @ param: [idList]
     * @ return: int
     */
    int deleteTesterByIds(@Param("idList") List<Long> idList);
    /**
     * @ author: zyk
     * @ description:删除中间表数据
     * @ date: 2021/12/9 15:46
     * @ param: [myList]
     * @ return: int
     */
    int deleteResult(@Param("myList") List<Long> myList);


    /**
     * @author: mxt
     * @description 根据testerId查询性格分析结果
     * @param: [testerId]
     * @return: com.yun.entity.TesterVo
     * @date: 2021/12/7
     */
    TesterVo selectTesterVoByTesterId(Integer testerId);
    /**
     * @ author: zyk
     * @ description:查找所有的测试用户及结果
     * @ date: 2021/12/9 15:45
     * @ param: []
     * @ return: java.util.List<com.yun.entity.TesterVo>
     */
    List<TesterVo> findAllTesterResult();
    /**
     * @ author: zyk
     * @ description:查询测试总人数
     * @ date: 2021/12/9 15:45
     * @ param: []
     * @ return: java.lang.Long
     */
    Long findAllCount();
    /**
     * @author: 李心雨
     * @description: 查找所有月份测试人数
     * @date: 2021/12/8 16:18
     * @return
     */
    Long getTodayCount();
    /**
     * @author: 李心雨
     * @description: 查找今天测试人数
     * @date: 2021/12/8 16:19
     * @return
     */
    Month getMonthCount();
    /**
     * @author: 李心雨
     * @description: 查找每种颜色的数量
     * @date: 2021/12/8 18:07
     * @return
     */
    CharacterA findCharacter();
}

