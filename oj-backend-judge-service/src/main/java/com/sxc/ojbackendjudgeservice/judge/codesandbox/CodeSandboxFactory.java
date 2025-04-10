package com.sxc.ojbackendjudgeservice.judge.codesandbox;


import com.sxc.ojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandbox;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandbox;
import com.sxc.ojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂(根据字符串参数沙箱类别创建指定的代码沙箱实例)
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type){
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            case "example":
                return new ExampleCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }

    }
}
