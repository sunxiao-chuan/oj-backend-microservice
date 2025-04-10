package com.ojbackendmodel.vo;

import cn.hutool.json.JSONUtil;

import com.ojbackendmodel.codesandbox.JudgeInfo;
import com.ojbackendmodel.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目视图对象
 *
 */
@Data
public class QuestionSubmitVO implements Serializable {

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
    private JudgeInfo judgeInfo;

    /**
     * 更新时间
     */
    private Date updateTime;



    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     *  用户 vo
     */
    private UserVO userVO;

    /**
     * 题目信息
     */
    private QuestionVO questionVO;

    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param questionSubmitVO
     * @return QuestionSubmit
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(questionSubmitVO.getJudgeInfo()));
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return QuestionSubmitVO
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        JudgeInfo judgeInfo = JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class);
        questionSubmitVO.setJudgeInfo(judgeInfo);
        return questionSubmitVO;
    }


}
