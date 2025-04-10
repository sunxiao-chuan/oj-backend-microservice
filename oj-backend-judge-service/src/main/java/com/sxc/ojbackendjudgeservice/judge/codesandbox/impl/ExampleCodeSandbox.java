package com.sxc.ojbackendjudgeservice.judge.codesandbox.impl;



import com.ojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.enums.JudgeInfoEnum;
import com.ojbackendmodel.enums.QuestionSubmitStatusEnum;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.CodeSandbox;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String language = executeCodeRequest.getLanguage();
        String code = executeCodeRequest.getCode();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo =new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
