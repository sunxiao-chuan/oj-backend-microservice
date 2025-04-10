package com.ojbackendmodel.dto.questionSubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 提交题目
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {


    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 编程代码
     */
    private String code;


    private static final long serialVersionUID = 1L;
}