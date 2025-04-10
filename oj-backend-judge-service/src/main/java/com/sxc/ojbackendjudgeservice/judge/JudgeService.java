package com.sxc.ojbackendjudgeservice.judge;


import com.ojbackendmodel.entity.QuestionSubmit;

public interface JudgeService {

    /**
     * 判题服务
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(Long questionSubmitId);

}
