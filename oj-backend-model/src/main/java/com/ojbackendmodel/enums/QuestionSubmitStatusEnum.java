package com.ojbackendmodel.enums;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目状态枚举
 */
public enum QuestionSubmitStatusEnum {

    WAITING("等待中", 0),
    RUNNING("判题中", 1),
    SUCCESS("成功", 2),
    FAIL("失败", 3);


    private final String text;

    private final Integer value;



    QuestionSubmitStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (QuestionSubmitStatusEnum anEnum : QuestionSubmitStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }



}
