package com.sxc.ojbackendjudgeservice.judge.codesandbox;


import com.ojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ojbackendmodel.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口
 */
public interface CodeSandbox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
