package com.sxc.ojbackendjudgeservice.judge.strategy;


import com.ojbackendmodel.codesandbox.JudgeInfo;

public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
