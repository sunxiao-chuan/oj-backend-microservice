package com.sxc.ojbackendjudgeservice.controller.inner;


import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.entity.QuestionSubmit;

import com.sxc.ojbackendjudgeservice.judge.JudgeService;
import com.sxc.ojbackendjudgeservice.rabbitmq.MyMessageConsumer;
import com.sxc.ojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该服务仅内部调用，不是给前端用的
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {


    @Resource
    private JudgeService judgeService;



    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(Long questionSubmitId) {
        return judgeService.doJudge(questionSubmitId);
    }
}
