package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.entity.User;

import net.sinlo.vchat.service.impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@RestController
@RequestMapping("api/friend")
@Api("朋友")
public class FriendController {
    @Autowired
    private FriendServiceImpl friendService;
    @ApiOperation("获取朋友列表")
    @GetMapping("list")
    @UserLoginToken
    public List myFriends(@ParamUser User user) {
        return this.friendService.getFriends(user.getId());
    }
    @ApiOperation("删除朋友")
    @PostMapping("deleteFriend")
    @UserLoginToken
    public boolean deleteFriend(@ParamUser User user,@RequestBody() int FriendId) {
        return this.friendService.deleteFriend(user.getId(),FriendId);
    }

}

