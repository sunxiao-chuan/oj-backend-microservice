package com.sxc.ojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;

import com.ojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.dto.question.JudgeCase;
import com.ojbackendmodel.dto.question.JudgeConfig;
import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.entity.QuestionSubmit;
import com.ojbackendmodel.enums.QuestionSubmitStatusEnum;
import com.sxc.ojbackendcommon.common.ErrorCode;
import com.sxc.ojbackendcommon.exception.BusinessException;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.sxc.ojbackendjudgeservice.judge.strategy.JudgeContext;
import com.sxc.ojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;


    @Resource
    private QuestionFeignClient questionFeignClient;

    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        //1.获取题目提交信息
        QuestionSubmit questionSubmit= questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if(questionSubmit==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        //获取题目提交里面的代码信息语言信息等
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        Long questionId= questionSubmit.getQuestionId();
        //2.获取题目信息
        Question question=questionFeignClient.getQuestionById(questionId);
        if(question==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        //获取题目信息
        String answer = question.getAnswer();
        //获取测试用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList=JSONUtil.toList(judgeCaseStr,JudgeCase.class);

        //获取测试用例的输入列表
        List<String> input=judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        //获取测试用例的输出列表
        List<String> output=judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());


        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfigObj=JSONUtil.toBean(judgeConfigStr,JudgeConfig.class);


        //如果提交题目不为等待状态（前端提交后题目提交进数据库，变成等待状态）
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }

        //3.更新提交题目状态为判题中
        QuestionSubmit questionSubmitUpdate=new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update= questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交题目状态错误");
        }

        //4.调用沙箱，接收沙箱运行结果
        CodeSandbox codeSandbox= CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy=new CodeSandboxProxy(codeSandbox);
        ExecuteCodeRequest executeCodeRequest= ExecuteCodeRequest.builder()
                .code(code)
                .inputList(input)
                .language(language)
                .build();

        //接收沙箱运行结果
        ExecuteCodeResponse executeCodeResponse= codeSandboxProxy.executeCode(executeCodeRequest);


        //5.根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext=new JudgeContext();
        judgeContext.setInputList(input);
        judgeContext.setOutputList(executeCodeResponse.getOutputList());
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        System.out.println("getJudgeInfo"+judgeContext.getJudgeInfo());
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeManager judgeManager=new JudgeManager();
        //调用judgeManager.doJudge判断题目是否通过：比较用例输出与真实输出以及一些其他条件
        JudgeInfo judgeInfo=judgeManager.doJudge(judgeContext);
        //更新题目提交信息
        questionSubmitUpdate=new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean updateResult= questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!updateResult){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交题目状态错误");
        }
        QuestionSubmit result=questionFeignClient.getQuestionSubmitById(questionSubmitId);
        return result;

    }
}
