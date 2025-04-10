package com.ojbackendmodel.dto.question;


import lombok.Data;

/**
 * 题目配置
 **/
@Data
public class JudgeConfig {

    /**
     * 时间限制
     * 单位：ms
     */
    private Long timeLimit;

    /**
     * 内存限制
     * 单位：kb
     */
    private Long memoryLimit;
    /**
     * 堆栈限制
     * 单位：kb
     */
    private Long stackLimit;

}
