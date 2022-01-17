package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.TesterDao;
import com.yun.entity.CharacterA;
import com.yun.entity.Month;
import com.yun.entity.Tester;
import com.yun.entity.TesterVo;
import com.yun.service.TesterService;
import com.yun.utils.MyConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 性格测试者(Tester)表服务实现类
 *
 * @author makejava
 * @since 2021-12-02 14:38:21
 */
@Service("testerService")
public class TesterServiceImpl extends ServiceImpl<TesterDao, Tester> implements TesterService {
    @Resource
    private TesterDao testerDao;

    @Override
    public boolean saveTester(Tester tester) {
        int i = testerDao.saveTester(tester);
        if (i > 0) {
            return true;
        }
        return false;
    }


    @Override
    public int deleteTester(Integer id) {
        return testerDao.deleteTester(id);
    }
    @Override
    public Tester checkPhone(String phone) {
        return testerDao.checkPhone(phone);
    }
    @Override
    public boolean deleteTesterByIds(List<Long> idList) {
        //删除测试者
        int i = testerDao.deleteTesterByIds(idList);
        //删除测试者的测试记录（预留）

        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public int updateTester(Tester tester) {
        return testerDao.updateTester(tester);
    }

    @Override
    public Tester selectTesterById(Integer id) {
        return testerDao.selectTesterById(id);
    }

    @Override
    public List<TesterVo> selectAllTester(Long page, Long limit, Tester tester) {
        //优化代码，不分页的时候，默认第一页，一页显示10条
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        return testerDao.selectAllTester(page, limit, tester);
    }
    @Override
    public void deleteNoResult() {
        testerDao.deleteNoResult();
    }

    @Override
    public boolean deleteResult(List<Long> myList) {
        int i=testerDao.deleteResult(myList);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public Long getCount(Tester tester) {
        return testerDao.getCount(tester);
    }

    @Override
    public TesterVo selectTesterVoByTesterId(Integer testerId) {
        TesterVo testerVo = testerDao.selectTesterVoByTesterId(testerId);
        return testerVo;
    }
    @Override
    public List<TesterVo> findAllTesterResult() {
        List<TesterVo> allTesterResult = testerDao.findAllTesterResult();
        return allTesterResult;
    }

    @Override
    public Long findAllCount() {
        Long allCount = testerDao.findAllCount();
        return allCount;
    }

    @Override
    public Long getTodayCount() {
        Long todayCount = testerDao.getTodayCount();
        return todayCount;
    }

    @Override
    public Month getMonthCount() {
        Month monthCount = testerDao.getMonthCount();
        return monthCount;
    }

    @Override
    public CharacterA findCharacter() {
        CharacterA characterA = testerDao.findCharacter();
        return characterA;
    }

}

