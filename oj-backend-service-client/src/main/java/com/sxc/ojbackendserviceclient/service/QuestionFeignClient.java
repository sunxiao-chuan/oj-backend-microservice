package com.sxc.ojbackendserviceclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ojbackendmodel.dto.question.QuestionQueryRequest;
import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.entity.QuestionSubmit;
import com.ojbackendmodel.vo.QuestionSubmitVO;
import com.ojbackendmodel.vo.QuestionVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

/**
* @author sxc
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2025-03-22 10:17:17
*/
@FeignClient(name="oj-backend-question-service",path = "/api/question/inner")
public interface QuestionFeignClient{

    /**
     * 根据id获取题目
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question  getQuestionById(@RequestParam("questionId") long questionId);

    /**
     * 根据id获取题目提交
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit  getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);

    /**
     * 更新提交题目
     * @param questionSubmit
     * @return
     */
    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);




}
