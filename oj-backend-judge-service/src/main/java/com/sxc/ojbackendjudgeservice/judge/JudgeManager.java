package com.sxc.ojbackendjudgeservice.judge;

import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.entity.QuestionSubmit;
import com.sxc.ojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.sxc.ojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.sxc.ojbackendjudgeservice.judge.strategy.JudgeContext;
import com.sxc.ojbackendjudgeservice.judge.strategy.JudgeStrategy;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit=judgeContext.getQuestionSubmit();
        // 根据编程语言选择不同的判题策略
        String language=questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy=new DefaultJudgeStrategy();
        if(language.equals("java")){
            judgeStrategy=new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);

    }
}
