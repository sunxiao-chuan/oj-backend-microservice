package com.sxc.ojbackendjudgeservice.judge.strategy;


import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.dto.question.JudgeCase;
import com.ojbackendmodel.entity.Question;
import com.ojbackendmodel.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;


@Data
public class JudgeContext {


    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private JudgeInfo judgeInfo;

    private Question question;

    private QuestionSubmit questionSubmit;

}
