package com.sxc.ojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.dto.question.JudgeCase;
import com.ojbackendmodel.dto.question.JudgeConfig;
import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.enums.JudgeInfoEnum;


import java.util.List;
import java.util.Optional;

public class JavaLanguageJudgeStrategy implements JudgeStrategy{
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        System.out.println("2222judgeInfo"+judgeInfo);
        Question question = judgeContext.getQuestion();
        JudgeConfig judgeConfigObj = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        JudgeInfo newJudgeInfo=new JudgeInfo();
        newJudgeInfo.setTime(judgeInfo.getTime());
        newJudgeInfo.setMemory(judgeInfo.getMemory());

        JudgeInfoEnum judgeInfoEnum=JudgeInfoEnum.ACCEPTED;
        if(outputList.size()!=inputList.size()){
            judgeInfoEnum=JudgeInfoEnum.WRONG_ANSWER;
            newJudgeInfo.setMessage(judgeInfoEnum.getValue());
            return newJudgeInfo;
        }
        System.out.println("newJudgeInfo"+newJudgeInfo);
        //再判断每一个输出是否相等
        for(int i=0;i<judgeCaseList.size();i++){
            JudgeCase judgeCase=judgeCaseList.get(i);
            if(!judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoEnum=JudgeInfoEnum.WRONG_ANSWER;
                newJudgeInfo.setMessage(judgeInfoEnum.getValue());
                return newJudgeInfo;
            }
        }
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);
        if(memory>judgeConfigObj.getMemoryLimit()){
            judgeInfoEnum=JudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            newJudgeInfo.setMessage(judgeInfoEnum.getValue());
            return newJudgeInfo;
        }
        System.out.println("11newJudgeInfo"+newJudgeInfo);
        //java程序本身需要额外执行10秒钟
        long JAVA_PROGRAM_TIME_COST=10000L;
        if(time-JAVA_PROGRAM_TIME_COST > judgeConfigObj.getTimeLimit()){
            judgeInfoEnum=JudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            newJudgeInfo.setMessage(judgeInfoEnum.getValue());
            return newJudgeInfo;
        }
        System.out.println("11newJudgeInfo"+newJudgeInfo);

        newJudgeInfo.setMessage(judgeInfoEnum.getValue());
        return newJudgeInfo;
    }
}
