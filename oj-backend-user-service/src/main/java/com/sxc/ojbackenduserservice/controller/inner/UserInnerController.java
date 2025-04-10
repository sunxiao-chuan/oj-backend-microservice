package com.sxc.ojbackenduserservice.controller.inner;


import com.ojbackendmodel.entity.User;
import com.sxc.ojbackendserviceclient.service.UserFeignClient;
import com.sxc.ojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 该服务仅内部调用，不是给前端用的
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {


    @Resource
    private UserService userService;

    /**
     * 根据 id 获取用户
     *
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") long userId) {
        return userService.getById(userId);
    }

    /**
     * 根据 id 列表获取用户列表
     *
     * @param idList
     * @return
     */
    @Override
    @GetMapping("/get/ids")
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}
