package com.sxc.ojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.dto.question.JudgeCase;
import com.ojbackendmodel.dto.question.JudgeConfig;
import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.enums.JudgeInfoEnum;


import java.util.List;

public class DefaultJudgeStrategy implements JudgeStrategy{
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Question question = judgeContext.getQuestion();
        JudgeConfig judgeConfigObj = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);


        JudgeInfoEnum judgeInfoEnum=JudgeInfoEnum.ACCEPTED;
        if(outputList.size()!=inputList.size()){
            judgeInfoEnum=JudgeInfoEnum.WRONG_ANSWER;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }
        //再判断每一个输出是否相等
        for(int i=0;i<judgeCaseList.size();i++){
            JudgeCase judgeCase=judgeCaseList.get(i);
            if(!judgeCase.equals(outputList.get(i))){
                judgeInfoEnum=JudgeInfoEnum.WRONG_ANSWER;
                judgeInfo.setMessage(judgeInfoEnum.getValue());
                return judgeInfo;
            }
        }
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        if(memory>judgeConfigObj.getMemoryLimit()){
            judgeInfoEnum=JudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }
        if(time>judgeConfigObj.getTimeLimit()){
            judgeInfoEnum=JudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoEnum.getValue());
            return judgeInfo;
        }

        judgeInfo.setMessage(judgeInfoEnum.getValue());
        return judgeInfo;
    }
}
