package com.ojbackendmodel.entity;

import lombok.Data;

import java.util.Date;


@Data
public class QuestionSubmit {
    /**
     * id
     */
    private Long id;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 编程代码
     */
    private String code;

    /**
     * 提交状态(0-待判题 1-判题中 2-成功 3-失败)
     */
    private Integer status;

    /**
     * 判题结果(json对象)
     */
    private String judgeInfo;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;


}