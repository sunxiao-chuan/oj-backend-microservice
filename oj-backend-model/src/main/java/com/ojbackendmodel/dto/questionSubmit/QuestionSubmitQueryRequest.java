package com.ojbackendmodel.dto.questionSubmit;


import com.sxc.ojbackendcommon.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 提交题目
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户id
    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交状态
     */
    private Integer status;


    private static final long serialVersionUID = 1L;
}