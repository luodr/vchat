package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.friend.FriendDto;
import net.sinlo.vchat.dto.friend.FriendRemarkDto;
import net.sinlo.vchat.entity.FriendAdd;
import net.sinlo.vchat.entity.User;

import net.sinlo.vchat.service.IFriendService;
import net.sinlo.vchat.service.impl.FriendServiceImpl;
import net.sinlo.vchat.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

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
@UserLoginToken
public class FriendController {
    @Autowired
    private IFriendService friendService;

    @ApiOperation("获取好友列表")
    @GetMapping("list")
    public List myFriends(@ParamUser @ApiIgnore User user) {
        List list =this.friendService.getFriends(user.getId());
        System.out.println(list);
        return list;
    }

    @ApiOperation("删除好友")
    @DeleteMapping("")
    public boolean deleteFriend(@ParamUser @ApiIgnore User user, @RequestBody() FriendDto friendDto) {
        return this.friendService.deleteFriend(user.getId(), friendDto.getFriendId());
    }
    @ApiOperation("修改备注")
    @PostMapping("remark")
    public boolean updateFriendRemark(@ParamUser @ApiIgnore User user, @RequestBody() FriendRemarkDto dto) {
        System.out.println(dto);
        return this.friendService.updateOneRemark(user.getId(), dto);
    }

    @PostMapping("add")
    @ApiOperation("请求添加好友")
    public FriendAdd requestAddFriend(@ParamUser @ApiIgnore User user, @RequestBody() FriendDto friendDto) {
        FriendAdd friendAdd = friendService.requestAddFriend(user.getId(), friendDto.getFriendId());
        friendAdd = friendService.getFriendById(friendAdd.getId());
        WebSocketServer.addFriend(friendAdd);
        return friendAdd;
    }

    @PostMapping("agree")
    @ApiOperation("同意添加好友")
    public boolean agree(@ParamUser @ApiIgnore User user, @RequestBody() FriendDto friendDto) {

        return     this.friendService.addFriend(user.getId(), friendDto.getFriendId());
    }

    @PostMapping("refuse")
    @ApiOperation("拒绝添加好友")
    public boolean refuse(@ParamUser @ApiIgnore User user, @RequestBody() FriendDto friendDto) {
        return friendService.updateState("refuse", user.getId(), friendDto.getFriendId());
    }

    @GetMapping("getFriendAdds")
    @ApiOperation("添加好友列表")
    public List<FriendAdd> getFriendAdds(@ParamUser @ApiIgnore User user) {
        return friendService.getFriendAdds(user.getId());
    }


}

