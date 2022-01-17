package com.yun.controller;



import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yun.entity.Question;
import com.yun.entity.ReturnBean;
import com.yun.service.QuestionService;
import com.yun.utils.MyConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 问题表(Question)表控制层
 * @author makejava
 * @since 2021-12-02 14:37:47
 */
@RestController//等于@Controller+@ResponseBody
@RequestMapping("question")
@Slf4j
public class QuestionController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;

    @GetMapping("toQuestion")
    public ModelAndView toMenu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tester/showQuestion");
        return modelAndView;
    }
    /**
     * 分页查询所有数据
     * @param page     分页对象
     * @param question 查询实体
     * @return 所有数据
     */
    //@RequiresPermissions("character:question:search")
    @RequestMapping("selectAllPage")
    public ReturnBean selectAllPage(Long page, Long limit, Question question) {
        //重新构建分页对象
        if(page==null){
            page= MyConstants.page;
            limit= MyConstants.limit;
        }
        Page<Question> pageObj = new Page<>(page, limit);
        //问题条件查询对象
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(question.getQuestion())){
            questionQueryWrapper.like("question",question.getQuestion());
        }
        if(ObjectUtil.isNotEmpty(question.getCreator())){
            questionQueryWrapper.like("creator",question.getCreator());
        }
        Page<Question> questionPage = this.questionService.page(pageObj,questionQueryWrapper );
        return super.success(questionPage.getRecords(),questionPage.getTotal());
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ReturnBean selectOne( Integer id) {
        Question question = this.questionService.getById(id);
        return super.success(question);

    }

    /**
     * 新增数据
     * @param question 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public ReturnBean insert( @RequestBody  Question question) {
        question.setCreateTime(new Date());
        boolean save = this.questionService.save(question);
        if (save) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 修改数据
     * @param question 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public ReturnBean update( @RequestBody  Question question) {
        question.setUpdateTime(new Date());
        if(question.getStatus()==null || 0!=question.getStatus()){
            question.setStatus(1);
        }
        boolean update = this.questionService.updateById(question);
        if (update) {
            return super.success(question);
        } else {
            return super.fail(question);
        }
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public ReturnBean delete(@RequestParam(value = "idList[]",required = false) List<Long> idList) {
        boolean delete = this.questionService.removeByIds(idList);
        if (delete) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }
}

