package com.yun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.entity.CharacterA;
import com.yun.entity.Month;
import com.yun.entity.Tester;
import com.yun.entity.TesterVo;

import java.util.List;

/**
 * 性格测试者(Tester)表服务接口
 *
 * @author makejava
 * @since 2021-12-02 14:38:21
 */
public interface TesterService extends IService<Tester> {
   /**
    * @ author: zyk
    * @ description:保存测试者信息
    * @ date: 2021/12/2 15:04
    * @ param: [tester]
    * @ return: boolean
    */
    boolean saveTester(Tester tester);
   /**
    * @ author: zyk
    * @ description:按照主键删除
    * @ date: 2021/12/2 15:05
    * @ param: [id]
    * @ return: int
    */
   int deleteTester(Integer id);
   /**
    * @ author: zyk
    * @ description:删除无测试结果的记录
    * @ date: 2021/12/2 16:37
    * @ param: []
    * @ return: void
    */
   void deleteNoResult();
   /**
    * @ author: zyk
    * @ description:检测手机号是否存在
    * @ date: 2021/12/2 16:38
    * @ param: [phone]
    * @ return: com.yun.entity.Tester
    */
   Tester checkPhone(String phone);
   /**
    * @ author: zyk
    * @ description:根据id进行批量删除
    * @ date: 2021/12/2 16:38
    * @ param: [idList]
    * @ return: boolean
    */
    boolean deleteTesterByIds(List<Long> idList);
    int updateTester(Tester tester);
    Tester selectTesterById(Integer id);
    List<TesterVo> selectAllTester(Long page  , Long limit, Tester tester);
    Long getCount( Tester tester);
    boolean deleteResult( List<Long> myList);

    /**
     * @author: mxt
     * @description 根据testerId查询性格分析结果
     * @param: [testerId]
     * @return: com.yun.entity.TesterVo
     * @date: 2021/12/7
     */
    TesterVo selectTesterVoByTesterId(Integer testerId);
    List<TesterVo> findAllTesterResult();
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

